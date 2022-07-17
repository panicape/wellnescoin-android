package com.panicape.wellnesscoin.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.panicape.wellnesscoin.R;
import com.panicape.wellnesscoin.databinding.MarketplaceFragmentBinding;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class MarketplaceFragment extends Fragment {

    private MarketplaceViewModel mViewModel;
    private MarketplaceFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(MarketplaceViewModel.class);

        binding = MarketplaceFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        setHasOptionsMenu(true);

        WebView myWebView = (WebView) root.findViewById(R.id.marketplaceView);
        myWebView.loadUrl("https://www.cyclos.org/");

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