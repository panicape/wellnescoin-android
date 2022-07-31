package com.panicape.wellnesscoin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class Pausa_help_activity extends AppCompatActivity {

    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pausa_help);
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
        MenuItem logoffItem = menu.findItem(R.id.action_logoff);
        MenuItem exitItem = menu.findItem(R.id.action_exit);
        MenuItem nextItem = menu.findItem(R.id.action_next);

        MenuItem mainItem = menu.findItem(R.id.action_main);
        MenuItem backItem = menu.findItem(R.id.action_back);
        MenuItem profileItem = menu.findItem(R.id.action_profile);
        MenuItem infoItem = menu.findItem(R.id.action_info);
        MenuItem webItem = menu.findItem(R.id.action_web);
        MenuItem configItem = menu.findItem(R.id.action_settings);
        MenuItem pausaHelpItem = menu.findItem(R.id.action_pausa_help);

        pausaHelpItem.setVisible(false);
        infoItem.setVisible(true);
        exitItem.setVisible(true);
        webItem.setVisible(true);

        backItem.setVisible(false);
        nextItem.setVisible(false);
        loginItem.setVisible(false);
        mainItem.setVisible(false);
        configItem.setVisible(false);

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            profileItem.setVisible(false);
            logoffItem.setVisible(false);
        } else {
            profileItem.setVisible(true);
            logoffItem.setVisible(true);
        }

        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // infoItem.setVisible(true);
        // exitItem.setVisible(true);
        // webItem.setVisible(true);
        boolean response = false;

        switch (item.getItemId()) {
            case R.id.action_info:
                Intent intent = new Intent(this, HelpMainActivity.class);
                finishAfterTransition();
                startActivity(intent);

                response = true;
                break;
            case R.id.action_web:
                Intent webIntent = new Intent(this, WebActivity.class);
                finishAfterTransition();
                startActivity(webIntent);

                response = true;
                break;

            case R.id.action_profile:
                if (FirebaseAuth.getInstance().getCurrentUser()!=null) {
                    Intent mainIntent= new Intent(this, MainActivity.class);
                    mainIntent.putExtra("frag", "profile");
                    startActivity(mainIntent);
                } else {
                    Toast.makeText(this, "No se ha encontrado usuario conectado",
                            Toast.LENGTH_LONG);
                }

                response = true;
                break;

            case R.id.action_exit:
                MenuItem mainMenuItem =  findViewById(R.id.action_main);
                MenuItem loginItem = findViewById(R.id.action_login);
                MenuItem backItem = findViewById(R.id.action_back);
                MenuItem profileItem = findViewById(R.id.action_profile);
                MenuItem logoffItem = findViewById(R.id.action_logoff);

                backItem.setVisible(true);
                loginItem.setVisible(false);
                mainMenuItem.setVisible(false);
                logoffItem.setVisible(false);
                profileItem.setVisible(false);

                FirebaseAuth.getInstance().signOut();
                System.exit(1);

                response = true;

                break;
        }

        return response;
    }

    @Override
    public void onBackPressed() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            mainIntent.putExtra("frag","login");
        } else {
            mainIntent.putExtra("frag","home");
        }

        finishAfterTransition();
        startActivity(mainIntent);
    }
}