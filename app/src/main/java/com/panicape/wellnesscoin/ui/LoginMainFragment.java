package com.panicape.wellnesscoin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.panicape.wellnesscoin.PausasMainActivity;
import com.panicape.wellnesscoin.R;
import com.panicape.wellnesscoin.databinding.FragmentLoginMainBinding;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class LoginMainFragment extends Fragment {

    private FragmentLoginMainBinding binding;

    private CardView pausaStatus, doPausas, marketplace, wallet;



    // Methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginMainBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        doPausas = binding.pausaCV;
        marketplace = binding.marketplaceCV ;
        pausaStatus = binding.pausastatusCV;
        wallet = binding.walletCV;

        doPausas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(view.getContext(), "En construcción", Toast.LENGTH_SHORT).show();
                //Intent doPausaIntent = new Intent(getActivity(), PausaHands.class);
                //startActivity(doPausaIntent);

                Intent doPausaIntent = new Intent(getActivity(), PausasMainActivity.class);
                startActivity(doPausaIntent);
            }
        });

        pausaStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_pausas_status);
            }
        });

        marketplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.nav_marketplace);
            }
        });

        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
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

        helpItem.setVisible(false);
        webItem.setVisible(false);

        loginItem.setVisible(false);
        mainItem.setVisible(false);
    }

    @Override
    public void onDestroyView() {
        binding = null;

        super.onDestroyView();
    }
}