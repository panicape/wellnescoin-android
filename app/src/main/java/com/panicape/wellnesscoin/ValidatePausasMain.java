package com.panicape.wellnesscoin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class ValidatePausasMain extends AppCompatActivity {

    private List<String> pausas;


    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pausas = new ArrayList<>();
        pausas.add("Pausa caminar");
        pausas.add("Pausa Video");

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_validate_pausas_main);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem loginItem = menu.findItem(R.id.action_login);
        MenuItem profileItem = menu.findItem(R.id.action_profile);
        MenuItem logoffItem = menu.findItem(R.id.action_logoff);
        MenuItem mainItem = menu.findItem(R.id.action_main);

        if(FirebaseAuth.getInstance().getCurrentUser() == null) {
            loginItem.setVisible(true);
            profileItem.setVisible(false);
            logoffItem.setVisible(false);
        } else {
            loginItem.setVisible(false);
            profileItem.setVisible(true);
            logoffItem.setVisible(true);
            mainItem.setVisible(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "No esta implementado todavia",
                        Toast.LENGTH_SHORT).show();
                return true;
//            case R.id.action_validar_pausas:
//                Intent intent = new Intent(this, ValidatePausaActivity.class);
//                startActivity(intent);
//                return true;
            case  R.id.action_info:
                Intent infoIntent = new Intent(this, HelpMainActivity.class);
                startActivity(infoIntent);
                return true;
            case R.id.action_web:
                Intent webIntent = new Intent(this, WebActivity.class);
                startActivity(webIntent);
                return true;
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
                finish();
                return true;
            case R.id.action_profile:
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("frag", "profile");
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "No se ha encontrado usuario conectado", Toast.LENGTH_SHORT);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("frag", "home");
        startActivity(intent);
    }
}