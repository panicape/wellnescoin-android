package com.panicape.wellnesscoin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class ValidatePausasMain extends AppCompatActivity {

    private List<String> pausas;


    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pausas = new ArrayList<>();
        pausas.add("Pausa caminar");
        pausas.add("Pausa Video");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_validate_pausas_main);
    }
}