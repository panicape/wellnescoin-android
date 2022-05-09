package com.panicape.wellnesscoin;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class WalkActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor stepSensor;

    private TextView stepCounter;
    private EditText chronoET;

    private int counter;

    private Button startWBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        counter = 0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk);

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
                Toast.makeText(WalkActivity.this, "Finalizo", Toast.LENGTH_SHORT).show();
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

}