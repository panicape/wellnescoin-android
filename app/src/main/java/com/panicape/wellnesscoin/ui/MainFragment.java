package com.panicape.wellnesscoin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.panicape.wellnesscoin.PausaHands;
import com.panicape.wellnesscoin.R;
import com.panicape.wellnesscoin.databinding.MainFragmentBinding;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    private MainFragmentBinding binding;

    private Button startBtn;


    // Methods

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(MainViewModel.class);

        binding = MainFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setHasOptionsMenu(true);

        startBtn = binding.startBtn;
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent walkIntent = new Intent(getActivity(), WalkActivity.class);
                //startActivity(walkIntent);

                Intent handsIntent = new Intent(getActivity(), PausaHands.class);
                startActivity(handsIntent);
            }
        });
        List<String> pausasList = new ArrayList<>();
        pausasList.add("Caminar");
        pausasList.add("Manos");
        ListView pausasLV = binding.pausasLV;
        ArrayAdapter adapter = new ArrayAdapter(container.getContext(),
                android.R.layout.simple_list_item_1, pausasList);
        pausasLV.setAdapter(adapter);

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