package com.panicape.wellnesscoin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class Pausa_help_activity extends AppCompatActivity {

    ImageButton back;


    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pausa_help);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        back = (ImageButton) findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainActIntent = new Intent(v.getContext(), MainActivity.class);
                startActivity(mainActIntent);
            }
        });
    }


}