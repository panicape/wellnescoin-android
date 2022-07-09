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

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
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

        MenuItem mainItem = menu.findItem(R.id.action_main);
        MenuItem profileItem = menu.findItem(R.id.action_profile);
        MenuItem infoItem = menu.findItem(R.id.action_info);
        MenuItem webItem = menu.findItem(R.id.action_web);
        MenuItem configItem = menu.findItem(R.id.action_settings);

        int id = navController.getCurrentDestination().getId();

        switch (id) {
            case R.id.nav_login:
                infoItem.setVisible(false);
                webItem.setVisible(true);
                exitItem.setVisible(true);

                loginItem.setVisible(false);
                mainItem.setVisible(false);
                logoffItem.setVisible(false);
                configItem.setVisible(false);
                profileItem.setVisible(false);

                break;
            case R.id.nav_login_main:
                infoItem.setVisible(false);
                webItem.setVisible(false);
                exitItem.setVisible(true);

                loginItem.setVisible(false);
                mainItem.setVisible(false);
                configItem.setVisible(false);

                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    logoffItem.setVisible(true);
                    profileItem.setVisible(true);
                }

                break;
        }

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

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

                loginItem.setVisible(true);

                mainMenuItem.setVisible(false);
                logoffItem.setVisible(false);
                profileItem.setVisible(false);

                FirebaseAuth.getInstance().signOut();
                finish();
                return true;
            case R.id.action_profile:
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    navController.navigate(R.id.nav_gallery);
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
        FirebaseAuth.getInstance().signOut();
        finish();
    }
}