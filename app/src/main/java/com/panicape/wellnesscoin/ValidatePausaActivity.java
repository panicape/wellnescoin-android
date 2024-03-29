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
public class ValidatePausaActivity extends AppCompatActivity {

    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_pausa);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem loginItem = menu.findItem(R.id.action_login);
        MenuItem logoffItem = menu.findItem(R.id.action_logoff);
        MenuItem exitItem = menu.findItem(R.id.action_exit);
        MenuItem pausaStatusItem = menu.findItem(R.id.action_pausa_status);

        MenuItem mainItem = menu.findItem(R.id.action_main);
        MenuItem backItem = menu.findItem(R.id.action_back);
        MenuItem profileItem = menu.findItem(R.id.action_profile);
        MenuItem infoItem = menu.findItem(R.id.action_info);
        MenuItem webItem = menu.findItem(R.id.action_web);
        MenuItem configItem = menu.findItem(R.id.action_settings);
        MenuItem pausaHelpItem = menu.findItem(R.id.action_pausa_help);

        exitItem.setVisible(true);
        webItem.setVisible(true);
        backItem.setVisible(true);
        infoItem.setVisible(true);

        pausaStatusItem.setVisible(false);
        pausaHelpItem.setVisible(false);
        loginItem.setVisible(false);
        mainItem.setVisible(false);
        configItem.setVisible(false);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
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
        boolean response = false;

        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent configIntent = new Intent(this, ConfigActivity.class);
                response= true;
                finishAfterTransition();
                startActivity(configIntent);
                break;

            case  R.id.action_info:
                Intent infoIntent = new Intent(this, HelpMainActivity.class);
                finishAfterTransition();
                startActivity(infoIntent);
                response= true;
                break;

            case R.id.action_web:
                Intent webIntent = new Intent(this, WebActivity.class);

                finishAfterTransition();
                startActivity(webIntent);
                response = true;

                break;

            case R.id.action_exit:
                MenuItem mainMenuItem =  findViewById(R.id.action_main);
                MenuItem loginItem = findViewById(R.id.action_login);
                MenuItem profileItem = findViewById(R.id.action_profile);
                MenuItem logoffItem = findViewById(R.id.action_logoff);

                mainMenuItem.setVisible(false);
                logoffItem.setVisible(false);
                loginItem.setVisible(true);
                profileItem.setVisible(false);

                FirebaseAuth.getInstance().signOut();
                this.finishAffinity();

                response= true;
                break;

            case R.id.action_profile:
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("frag", "profile");
                    finishAfterTransition();
                    startActivity(intent);
                } else {
                    Toast.makeText(this,
                            "No se ha encontrado usuario conectado", Toast.LENGTH_SHORT);
                }

                response = true;
                break;
        }

        return response;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PausasMainActivity.class);

        finishAfterTransition();
        startActivity(intent);
    }

}