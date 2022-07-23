package com.panicape.wellnesscoin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class WalletHelpActivity extends AppCompatActivity {

    private ImageButton nextBtn;


    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_help);

        nextBtn = findViewById(R.id.WH_nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MarketplaceHelpActivity.class);
                startActivity(intent);
            }
        });
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
        switch (item.getItemId()) {
            case R.id.action_back:
                Intent intent = new Intent(this, MainActivity.class);
                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                    intent.putExtra("frag", "login");
                } else {
                    intent.putExtra("frag", "home");
                }

                startActivity(intent);
                break;

            case R.id.action_exit:
                FirebaseAuth.getInstance().signOut();
                System.exit(0);
                return true;
        }

        return super.onOptionsItemSelected(item);
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
    }

}