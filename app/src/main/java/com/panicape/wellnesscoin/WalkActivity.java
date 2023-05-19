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

                Toast.makeText(WalkActivity.this,
                        "Finalizó su pausa activa", Toast.LENGTH_SHORT).show();

                stepCounter.getText();
            }
        }.start();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent != null &&
                sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            stepCounter.setText (String.valueOf (counter++));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, stepSensor,
                sensorManager.SENSOR_DELAY_FASTEST);

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
    public boolean onPrepareOptionsMenu(@NonNull Menu menu) {
        MenuItem loginItem = menu.findItem(R.id.action_login);
        MenuItem logoffItem = menu.findItem(R.id.action_logoff);
        MenuItem exitItem = menu.findItem(R.id.action_exit);
        MenuItem pausaStatusItem = menu.findItem(R.id.action_pausa_status);

        MenuItem mainItem = menu.findItem(R.id.action_main);
        MenuItem backItem = menu.findItem(R.id.action_back);
        MenuItem profileItem = menu.findItem(R.id.action_profile);
        MenuItem infoItem = menu.findItem(R.id.action_info);
        MenuItem webItem = menu.findItem(R.id.action_web);
        MenuItem configItem = menu.findItem(R.id.action_settings);
        MenuItem pausaHelpItem = menu.findItem(R.id.action_pausa_help);

        exitItem.setVisible(true);
        webItem.setVisible(true);
        backItem.setVisible(true);
        infoItem.setVisible(true);

        pausaStatusItem.setVisible(false);
        pausaHelpItem.setVisible(false);
        loginItem.setVisible(false);
        mainItem.setVisible(false);
        configItem.setVisible(false);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            profileItem.setVisible(false);
            logoffItem.setVisible(false);
        } else {
            profileItem.setVisible(true);
            logoffItem.setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        boolean response = false;

        switch (item.getItemId()) {
            case  R.id.action_info:
                Intent infoIntent = new Intent(this, HelpMainActivity.class);

                finishAfterTransition();
                startActivity(infoIntent);

                response= true;
                break;

            case R.id.action_web:
                Intent webIntent = new Intent(this, WebActivity.class);

                finishAfterTransition();
                startActivity(webIntent);

                response= true;
                break;

            case R.id.action_exit:
                FirebaseAuth.getInstance().signOut();

                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    mainIntent.putExtra("frag","login");
                } else {
                    mainIntent.putExtra("frag","home");
                }

                finishAfterTransition();
                startActivity(mainIntent);

                response = true;
                break;

            case R.id.action_profile:
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    mainIntent.putExtra("frag", "profile");
                    finishAfterTransition();
                    startActivity(mainIntent);
                } else {
                    Toast.makeText(this,
                            "No se ha encontrado usuario conectado",
                            Toast.LENGTH_SHORT).show();
                }

                response= true;
                break;
        }
        return response;
    }

    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(this, MainActivity.class);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            mainIntent.putExtra("frag","login");
        } else {
            mainIntent.putExtra("frag","home");
        }

        finishAfterTransition();
        startActivity(mainIntent);
    }
}