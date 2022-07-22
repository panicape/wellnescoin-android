package com.panicape.wellnesscoin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class WebActivity extends AppCompatActivity {

    private WebView myWebView;


    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        myWebView = (WebView) findViewById(R.id.wellfarWV);
        myWebView.loadUrl("https://clarenes.wixsite.com/welfarecoin");
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
        MenuItem nextItem = menu.findItem(R.id.action_next);

        nextItem.setVisible(false);

        helpItem.setVisible(true);
        webItem.setVisible(true);
        exitItem.setVisible(true);

        loginItem.setVisible(false);
        mainItem.setVisible(false);
        logoffItem.setVisible(false);
        settingsItem.setVisible(false);
        profileItem.setVisible(false);

        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean response = false;

        switch (item.getItemId()) {
            case R.id.action_back:
                Intent mainIntent = new Intent(this, MainActivity.class);
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    mainIntent.putExtra("frag","login");
                } else {
                    mainIntent.putExtra("frag","home");
                }

                finishAfterTransition();
                startActivity(mainIntent);

                response = true;
                break;
            case R.id.action_info:
                Intent helpIntent = new Intent(this, HelpMainActivity.class);
                startActivity(helpIntent);

                response = true;
                break;
            case R.id.action_exit:
                FirebaseAuth.getInstance().signOut();
                System.exit(0);

                response = true;
                break;
        }

        return response;
    }

    @Override
    public void onBackPressed() {
        Intent backIntent = new Intent(this, MainActivity.class);
        if (FirebaseAuth.getInstance().getCurrentUser()==null) {
            backIntent.putExtra("frag", "login");
        } else {
            backIntent.putExtra("frag", "home");
        }

        finishAfterTransition();
        startActivity(backIntent);
    }

}