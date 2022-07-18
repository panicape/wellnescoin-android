package com.panicape.wellnesscoin;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class ValidatePausaActivity extends AppCompatActivity {

    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_pausa);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("frag", "home");
        startActivity(intent);
    }

}