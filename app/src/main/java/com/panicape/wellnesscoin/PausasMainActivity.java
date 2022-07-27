package com.panicape.wellnesscoin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

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

        exitItem.setVisible(true);
        webItem.setVisible(true);
        backItem.setVisible(true);
        infoItem.setVisible(true);

        nextItem.setVisible(false);
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

                finishAfterTransition();
                response = true;
                startActivity(intent);
                break;

            case R.id.action_web:
                Intent webIntent = new Intent(this, WebActivity.class);

                response = true;
                finishAfterTransition();

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

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            mainIntent.putExtra("frag","login");
        } else {
            mainIntent.putExtra("frag","home");
        }

        finishAfterTransition();
        startActivity(mainIntent);
    }

}