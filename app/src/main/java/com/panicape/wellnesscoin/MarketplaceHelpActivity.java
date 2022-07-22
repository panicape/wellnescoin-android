package com.panicape.wellnesscoin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


/**
 * @author panicape
 * @version  1.01 June 2022
 */
public class MarketplaceHelpActivity extends AppCompatActivity {

    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marketplace_help);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(@NonNull Menu menu) {
        MenuItem loginItem = menu.findItem(R.id.action_login);
        MenuItem helpItem =  menu.findItem(R.id.action_info);
        MenuItem settingsItem =  menu.findItem(R.id.action_settings);
        MenuItem profileItem = menu.findItem(R.id.action_profile);
        MenuItem webItem = menu.findItem(R.id.action_web);
        MenuItem mainItem = menu.findItem(R.id.action_main);
        MenuItem logoffItem = menu.findItem(R.id.action_logoff);
        MenuItem exitItem = menu.findItem(R.id.action_exit);
        MenuItem backItem = findViewById(R.id.action_back);
        MenuItem nextItem = menu.findItem(R.id.action_next);

        nextItem.setVisible(false);

        webItem.setVisible(true);
        backItem.setVisible(true);
        exitItem.setVisible(true);
        loginItem.setVisible(true);

        mainItem.setVisible(false);
        logoffItem.setVisible(false);
        profileItem.setVisible(false);
        helpItem.setVisible(false);
        settingsItem.setVisible(false);

        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_back:
                Intent intent = new Intent(this, MainActivity.class);
                if (FirebaseAuth.getInstance().getCurrentUser()==null) {
                    intent.putExtra("frag", "login");
                } else {
                    intent.putExtra("frag", "home");
                }

                startActivity(intent);
                break;

            case R.id.action_settings:
                Intent configIntent = new Intent(this, ConfigActivity.class);
                startActivity(configIntent);
                return true;

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
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
        Intent intent = new Intent(this, HelpMainActivity.class);
        startActivity(intent);
    }

}