package com.panicape.wellnesscoin.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.panicape.wellnesscoin.R;
import com.panicape.wellnesscoin.databinding.FragmentPausasStatusBinding;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class PausasStatusFragment extends Fragment {

    private List<String> pausas;

    private FragmentPausasStatusBinding binding;

    private ListView pausasStatusLV;


    // Constructor

    public PausasStatusFragment() {
        pausas = new ArrayList<>();

        pausas.add("pcarrillo_060620221522");
        pausas.add("pcarrillo_060620221109");
        pausas.add("pcarrillo_060620221731");
    }


    // Methods


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPausasStatusBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setHasOptionsMenu(true);
        pausasStatusLV = binding.pausaStatusListLV;
        ArrayAdapter adapter = new ArrayAdapter(container.getContext(),
                android.R.layout.simple_list_item_1, pausas);
        pausasStatusLV.setAdapter(adapter);

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

}