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

        pausaHelpItem.setVisible(false);
        pausaStatusItem.setVisible(false);
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

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean response = false;

        switch (item.getItemId()) {
            case R.id.action_back:
                Intent intent = new Intent(this, MainActivity.class);
                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                    intent.putExtra("frag", "login");
                } else {
                    intent.putExtra("frag", "home");
                }

                response = true;
                finishAfterTransition();
                startActivity(intent);
                break;

            case R.id.action_web:
                Intent webIntent = new Intent(this, WebActivity.class);

                finishAfterTransition();
                response = true;

                startActivity(webIntent);

                break;

            case  R.id.action_info:
                Intent infoIntent = new Intent(this, HelpMainActivity.class);

                finishAfterTransition();
                response = true;

                startActivity(infoIntent);
                return true;

            case R.id.action_exit:
                FirebaseAuth.getInstance().signOut();

                finishAfterTransition();
                response = true;

                System.exit(0);
                return true;
        }

        return response;
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
        Intent mainIntent = new Intent(this, MainActivity.class);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            mainIntent.putExtra("frag","login");
        } else {
            mainIntent.putExtra("frag","home");
        }

        startActivity(mainIntent);
    }

}