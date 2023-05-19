package com.panicape.wellnesscoin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.panicape.wellnesscoin.databinding.ActivityMainBinding;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private NavController navController;


    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED) {
            //ask for permission
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, 0);
        }

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_gallery,
                R.id.nav_home,
                R.id.nav_wallet,
                R.id.nav_marketplace,
                R.id.nav_login,
                R.id.nav_login_main)
                .setOpenableLayout(drawer).build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        NavigationUI.setupWithNavController(navigationView, navController);

        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().get("frag") != null) {
                String frag = getIntent().getExtras().get("frag").toString();
                switch (frag) {
                    case "login":
                        navController.navigate(R.id.nav_login);
                        break;
                    case "profile":
                        navController.navigate(R.id.nav_gallery);
                        break;
                    case "home":
                        navController.navigate(R.id.nav_login_main);
                        break;
                    case "marketplace":
                        navController.navigate(R.id.nav_marketplace);
                        break;
                    case "pausa_status":
                        navController.navigate(R.id.nav_pausas_status);
                        break;
                }
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

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
        MenuItem helpStatusItem = menu.findItem(R.id.action_help_status);
        MenuItem mainItem = menu.findItem(R.id.action_main);
        MenuItem backItem = menu.findItem(R.id.action_back);
        MenuItem profileItem = menu.findItem(R.id.action_profile);
        MenuItem infoItem = menu.findItem(R.id.action_info);
        MenuItem webItem = menu.findItem(R.id.action_web);
        MenuItem configItem = menu.findItem(R.id.action_settings);

        MenuItem pausaStatusItem = menu.findItem(R.id.action_pausa_status);

        backItem.setVisible(true);
        webItem.setVisible(true);
        exitItem.setVisible(true);

        logoffItem.setVisible(false);
        helpStatusItem.setVisible(false);
        pausaStatusItem.setVisible(false);
        loginItem.setVisible(false);
        mainItem.setVisible(false);
        infoItem.setVisible(false);
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
        boolean response = false;

        switch (item.getItemId()) {
            case R.id.action_profile:
                navController.navigate(R.id.nav_gallery);

                response = true;
                break;

            case R.id.action_logoff:
                FirebaseAuth.getInstance().signOut();
                navController.navigate(R.id.nav_login);

                response = true;
            case R.id.action_exit:
                FirebaseAuth.getInstance().signOut();
                navController.navigate(R.id.nav_login);

                response = true;
                break;
            case R.id.action_web:
                Intent webIntent = new Intent(this, WebActivity.class);
                startActivity(webIntent);

                response = true;
                break;

            case R.id.action_pausa_help:
                navController.navigate(R.id.nav_login);
                break;

            case R.id.action_pausa_status:
                navController.navigate(R.id.nav_pausas_status);
                break;
        }

        return response;
    }

    @Override
    public void onBackPressed() {
        int id = navController.getCurrentDestination().getId();

        switch (id) {
            case R.id.nav_login:
                Toast.makeText(this, "Hasta pronto", Toast.LENGTH_SHORT);
                this.finishAffinity();

                break;
            case R.id.nav_login_main:
                Toast.makeText(this, "Sesión Cerrada", Toast.LENGTH_SHORT);
                FirebaseAuth.getInstance().signOut();
                navController.navigate(R.id.action_login);

                break;
            case R.id.nav_gallery:

            case R.id.nav_marketplace:

            case R.id.nav_wallet:
                navController.navigate(R.id.action_main);
                break;

            default:
                break;
        }
    }

}