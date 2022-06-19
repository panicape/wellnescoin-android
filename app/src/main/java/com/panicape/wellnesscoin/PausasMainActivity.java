package com.panicape.wellnesscoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class PausasMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pausas_main);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_pirates:
                if (checked) {
                    Intent video = new Intent(this, PausasMainActivity.class);
                    startActivity(video);
                }
                    break;
            case R.id.radio_ninjas:
                if (checked) {
                    Intent video = new Intent(this, WalkActivity.class);
                    startActivity(video);
                }
                    break;
        }
    }
}