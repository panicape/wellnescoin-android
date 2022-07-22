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
 * Screen to configure app elements
 * @author panicape
 * @version 0.01 May 2022
 */
public class ConfigActivity extends AppCompatActivity {


    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

    }

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
        MenuItem nextItem = menu.findItem(R.id.action_next);

        MenuItem mainItem = menu.findItem(R.id.action_main);
        MenuItem backItem = menu.findItem(R.id.action_back);
        MenuItem profileItem = menu.findItem(R.id.action_profile);
        MenuItem infoItem = menu.findItem(R.id.action_info);
        MenuItem webItem = menu.findItem(R.id.action_web);
        MenuItem configItem = menu.findItem(R.id.action_settings);

        nextItem.setVisible(true);
        exitItem.setVisible(true);
        webItem.setVisible(true);
        backItem.setVisible(true);

        loginItem.setVisible(false);
        mainItem.setVisible(false);
        logoffItem.setVisible(false);
        infoItem.setVisible(false);
        configItem.setVisible(false);
        profileItem.setVisible(false);

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
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
        switch (item.getItemId()) {
            case  R.id.action_info:
                Intent infoIntent = new Intent(this, HelpMainActivity.class);
                finishAfterTransition();
                startActivity(infoIntent);
                return true;

            case R.id.action_web:
                Intent webIntent = new Intent(this, WebActivity.class);
                finishAfterTransition();
                startActivity(webIntent);
                return true;

            case R.id.action_exit:
                FirebaseAuth.getInstance().signOut();
                System.exit(0);
                return true;

            case R.id.action_profile:
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    Intent mainIntent = new Intent(this, MainActivity.class);
                    mainIntent.putExtra("frag", "profile");
                    finishAfterTransition();
                    startActivity(mainIntent);
                } else {
                    Toast.makeText(this,
                            "No se ha encontrado usuario conectado",
                            Toast.LENGTH_SHORT).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        finishAfterTransition();
        startActivity(intent);
    }

}