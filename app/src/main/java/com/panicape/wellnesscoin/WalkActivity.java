package com.panicape.wellnesscoin;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.panicape.wellnesscoin.persistence.model.PausaRegistryDto;
import com.panicape.wellnesscoin.persistence.model.TransactionsDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class WalkActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepSensor;

    private TextView stepCounter;
    private EditText chronoET;

    private int counter;

    private Button startWBtn;

    private DateTimeFormatter myFormatObj;


    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk);

        counter = 0;
        myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        chronoET = (EditText) findViewById(R.id.walk_chronoET);

        stepCounter = (TextView) findViewById(R.id.stepCounter);
        stepCounter.setText(String.valueOf(counter));

        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        startWBtn = (Button) findViewById(R.id.startWBtn);
        startWBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar cuenta atras
                startChronometer();

            }
        });
    }

    private void startChronometer() {
        int min = 5*60*1000;
        int sec = 0;

        long initChrono = min+sec;

        CountDownTimer time = new CountDownTimer(initChrono, 1000) {
            @Override
            public void onTick(long l) {
                long time = l / 1000;
                long min = time / 60;
                long sec = time % 60;

                String timetoShow = String.format("%02d", min);
                String secToShow = String.format("%02d", sec);
                chronoET.setText(timetoShow+":"+secToShow);
            }

            @Override
            public void onFinish() {
                TransactionsDto transactionsDto = new TransactionsDto();
                transactionsDto.setDateCreation(new Date());
                PausaRegistryDto pausaRegistryDto = new PausaRegistryDto();

                LocalDateTime dateObj = LocalDateTime.now();
                String currentDateTime = dateObj.format(myFormatObj);
                pausaRegistryDto.setDateCreation(currentDateTime);

                Toast.makeText(WalkActivity.this, "Finalizó su pausa activa", Toast.LENGTH_SHORT).show();

                stepCounter.getText();
            }
        }.start();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent != null && sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            stepCounter.setText(String.valueOf(counter++));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, stepSensor, sensorManager.SENSOR_DELAY_FASTEST);

        stepCounter.setText(String.valueOf(counter));
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem loginItem = menu.findItem(R.id.action_login);
        MenuItem profileItem = menu.findItem(R.id.action_profile);
        MenuItem logoffItem = menu.findItem(R.id.action_logoff);
        MenuItem mainItem = menu.findItem(R.id.action_main);

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            loginItem.setVisible(true);
            profileItem.setVisible(false);
            logoffItem.setVisible(false);
        } else {
            loginItem.setVisible(false);
            profileItem.setVisible(true);
            logoffItem.setVisible(true);
            mainItem.setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "No esta implementado todavia",
                        Toast.LENGTH_SHORT).show();
                return true;
//            case R.id.action_validar_pausas:
//                Intent intent = new Intent(this, ValidatePausaActivity.class);
//                startActivity(intent);
//                return true;
            case  R.id.action_info:
                Intent infoIntent = new Intent(this, HelpMainActivity.class);
                startActivity(infoIntent);
                return true;
            case R.id.action_web:
                Intent webIntent = new Intent(this, WebActivity.class);
                startActivity(webIntent);
                return true;
            case R.id.action_exit:
                MenuItem mainMenuItem =  findViewById(R.id.action_main);
                MenuItem loginItem = findViewById(R.id.action_login);
                MenuItem profileItem = findViewById(R.id.action_profile);
                MenuItem logoffItem = findViewById(R.id.action_logoff);

                mainMenuItem.setVisible(false);
                logoffItem.setVisible(false);
                loginItem.setVisible(true);
                profileItem.setVisible(false);

                FirebaseAuth.getInstance().signOut();
                finish();
                return true;
            case R.id.action_profile:
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    navController.navigate(R.id.nav_gallery);
                } else {
                    Toast.makeText(this, "No se ha encontrado usuario conectado", Toast.LENGTH_SHORT);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

    }
}