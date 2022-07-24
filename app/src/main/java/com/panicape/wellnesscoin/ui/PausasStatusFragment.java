package com.panicape.wellnesscoin.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.panicape.wellnesscoin.R;
import com.panicape.wellnesscoin.databinding.FragmentPausasStatusBinding;
import com.panicape.wellnesscoin.tools.AlarmReceiver;

import java.util.Calendar;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class PausasStatusFragment extends Fragment {

    private FragmentPausasStatusBinding binding;

    private EditText intervalET;

    private AlarmManager alarmMgr;

    private static final int alarmId = 1;

    private Switch enableAlarm;

    private EditText alarmView;


    // Constructor

    public PausasStatusFragment() {
    }


    // Methods

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPausasStatusBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setHasOptionsMenu(true);

        alarmMgr =
                (AlarmManager) root.getContext().getSystemService(Context.ALARM_SERVICE);

        enableAlarm = binding.psEnabeNotifications;
        intervalET = binding.alarmIntervalET;

        alarmView = binding.alarmView;


        String alarmStatus = checkAlarmStatus(root.getContext());
        if (alarmStatus!=null) {
            alarmView.setText(alarmStatus);
        } else{
            alarmView.setText("INACTIVE");
        }

        enableAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    boolean canActivate = false;

                    Calendar calendar = Calendar.getInstance();
                    int hour = 0;
                    hour = calendar.get(Calendar.HOUR_OF_DAY);

                    if (intervalET.getText() == null || intervalET.getText().toString().equals("")) {
                        hour += 2;
                        canActivate = true;
                    } else {
                        Log.i("ALARM INTERVAL", ""+intervalET.getText().toString());
                        Integer result = Integer.valueOf(intervalET.getText().toString());

                            if (result >= 1 && result <= 8) {
                                Toast.makeText(buttonView.getContext(),
                                        "El intervalo de tiempo tiene que ser entre 1 y 8 horas",
                                        Toast.LENGTH_SHORT);
                                canActivate = false;
                            } else {
                                canActivate = true;
                                hour += result;
                            }
                    }

                    if (canActivate) {
                        if (hour <= 24) {

                            calendar.add(Calendar.HOUR, hour);
                        } else {
                            hour = hour - 24;

                            int currentDay = calendar.get(calendar.DAY_OF_MONTH);
                            calendar.add(Calendar.DAY_OF_MONTH, currentDay + 1);
                            calendar.add(Calendar.HOUR_OF_DAY, hour);
                        }

                        setAlarm(buttonView.getContext(), calendar);

                        Toast.makeText(buttonView.getContext(),
                                "Alarma activada: "
                                        + calendar.get(Calendar.YEAR)+"-"+
                                        calendar.get(Calendar.MONTH)+"-"+
                                        calendar.get(Calendar.DAY_OF_MONTH)+" "+
                                        (hour) + ":" +
                                        calendar.get(Calendar.MINUTE),
                                Toast.LENGTH_LONG).show();

                        if (checkAlarmStatus(buttonView.getContext())!=null) {
                            alarmView.setText(checkAlarmStatus(buttonView.getContext()));
                        }
                    }
                } else {
                    stopAlarm(buttonView.getContext());
                    Toast.makeText(buttonView.getContext(), "Alarma desactivada",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }


    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        MenuItem loginItem = menu.findItem(R.id.action_login);
        MenuItem helpItem =  menu.findItem(R.id.action_info);
        MenuItem settingsItem =  menu.findItem(R.id.action_settings);
        MenuItem profileItem = menu.findItem(R.id.action_profile);
        MenuItem webItem = menu.findItem(R.id.action_web);
        MenuItem mainItem = menu.findItem(R.id.action_main);
        MenuItem logoffItem = menu.findItem(R.id.action_logoff);
        MenuItem exitItem = menu.findItem(R.id.action_exit);

        exitItem.setVisible(true);
        settingsItem.setVisible(true);
        logoffItem.setVisible(true);
        profileItem.setVisible(true);
        mainItem.setVisible(true);

        helpItem.setVisible(false);
        webItem.setVisible(false);

        loginItem.setVisible(false);
    }


    public void setAlarm(Context ctx, Calendar calendar) {
        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.

        Intent alarmIntent = new Intent(ctx, AlarmReceiver.class);
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getBroadcast(ctx, alarmId, alarmIntent,
                PendingIntent.FLAG_ONE_SHOT);
        alarmIntent.setData((Uri.parse("custom://" + calendar.getTimeInMillis())));
        alarmMgr.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    public static void stopAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
                alarmIntent, 0);
        alarmManager.cancel(pendingIntent);
    }

    public String checkAlarmStatus(Context ctx) {
        //checking if alarm is working with pendingIntent
        Intent intent = new Intent(getActivity(), AlarmReceiver.class);//the same as up
        intent.setAction(Context.ALARM_SERVICE);//the same as up

        boolean isActive = (PendingIntent.getBroadcast(getActivity(), 1, intent,
                PendingIntent.FLAG_NO_CREATE) != null);//just changed the flag
        Log.d("PAUSA_STATUS_SCREEN. ALARM", "La alarma "
                + (isActive ? "" : "NO") + " está activa");

        if (isActive) {
            if (alarmMgr.getNextAlarmClock() != null) {
                return alarmMgr.getNextAlarmClock().toString();
            }
        }

        return  "INACTIVA";
    }

}