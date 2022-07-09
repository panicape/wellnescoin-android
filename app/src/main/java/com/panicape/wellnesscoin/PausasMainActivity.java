package com.panicape.wellnesscoin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class PausasMainActivity extends AppCompatActivity {

    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pausas_main);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Redirect to each view according with the selected view
        switch(view.getId()) {
            case R.id.radio_pausa_video:
                if (checked) {
                    Intent video = new Intent(this, PausaHands.class);
                    startActivity(video);
                }
                    break;
            case R.id.radio_pausa_walk:
                if (checked) {
                    Intent video = new Intent(this, WalkActivity.class);
                    startActivity(video);
                }
                    break;
        }
    }

    @Override
    public void onBackPressed() {

    }
}