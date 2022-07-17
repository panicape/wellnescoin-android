package com.panicape.wellnesscoin;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.panicape.wellnesscoin.tools.AlarmReceiver;

import java.util.Calendar;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class ConfigActivity extends AppCompatActivity {

    private AlarmManager alarmMgr;

    private static final int alarmId = 1;

    private Switch enableAlarm;


    // Methods

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        alarmMgr =
                (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        enableAlarm = findViewById(R.id.enableAlarm);
        enableAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Calendar calendar = Calendar.getInstance();
                    int hour = calendar.get(Calendar.HOUR_OF_DAY) + 2;

                    setAlarm(buttonView.getContext(), calendar);
                    Toast.makeText(buttonView.getContext(),
                            "Alarma activada: "
                                    + calendar.get(Calendar.YEAR)+"-"+
                                    calendar.get(Calendar.MONTH)+"-"+
                                    calendar.get(Calendar.DAY_OF_MONTH)+" "+
                                    (hour) + ":" +
                                    calendar.get(Calendar.MINUTE),
                            Toast.LENGTH_SHORT).show();
                } else {
                    stopAlarm(buttonView.getContext());
                    Toast.makeText(buttonView.getContext(), "Alarma desactivada",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void setAlarm(Context ctx, Calendar calendar) {

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.

        Intent alarmIntent = new Intent(ctx, AlarmReceiver.class);
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getBroadcast(this, alarmId, alarmIntent,
                PendingIntent.FLAG_ONE_SHOT);
        alarmIntent.setData((Uri.parse("custom://" + calendar.getTimeInMillis())));
        alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
//        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    public static void stopAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
                alarmIntent, 0);
        alarmManager.cancel(pendingIntent);
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

        webItem.setVisible(true);
        exitItem.setVisible(true);
        mainItem.setVisible(true);
        logoffItem.setVisible(true);
        profileItem.setVisible(true);

        helpItem.setVisible(false);
        loginItem.setVisible(false);
        settingsItem.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        switch (item.getItemId()) {
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}