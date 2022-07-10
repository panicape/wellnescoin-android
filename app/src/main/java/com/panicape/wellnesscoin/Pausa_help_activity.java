package com.panicape.wellnesscoin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class Pausa_help_activity extends AppCompatActivity {

    private ImageButton backBtn;


    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pausa_help);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
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

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            loginItem.setVisible(true);
            profileItem.setVisible(false);
            logoffItem.setVisible(false);
        } else {
            loginItem.setVisible(false);
            profileItem.setVisible(true);
            logoffItem.setVisible(true);
        }

        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "No esta implementado todavia",
                        Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_exit:
                MenuItem mainMenuItem =  findViewById(R.id.action_main);
                MenuItem loginItem = findViewById(R.id.action_login);
                MenuItem profileItem = findViewById(R.id.action_profile);
                MenuItem logoffItem = findViewById(R.id.action_logoff);

                loginItem.setVisible(true);
                mainMenuItem.setVisible(false);
                logoffItem.setVisible(false);
                profileItem.setVisible(false);

                FirebaseAuth.getInstance().signOut();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent backIntent = new Intent(this, HelpMainActivity.class);
        startActivity(backIntent);
    }
}