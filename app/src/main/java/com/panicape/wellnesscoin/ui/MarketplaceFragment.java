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

import com.google.firebase.auth.FirebaseAuth;
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
        setHasOptionsMenu(true);

        binding = MarketplaceFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        WebView myWebView = binding.marketplaceView;
        myWebView.loadUrl("https://www.cyclos.org/");

        return root;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        MenuItem loginItem = menu.findItem(R.id.action_login);
        MenuItem logoffItem = menu.findItem(R.id.action_logoff);
        MenuItem exitItem = menu.findItem(R.id.action_exit);
        MenuItem helStatusItem = menu.findItem(R.id.action_help_status);
        MenuItem mainItem = menu.findItem(R.id.action_main);
        MenuItem backItem = menu.findItem(R.id.action_back);
        MenuItem profileItem = menu.findItem(R.id.action_profile);
        MenuItem infoItem = menu.findItem(R.id.action_info);
        MenuItem webItem = menu.findItem(R.id.action_web);
        MenuItem configItem = menu.findItem(R.id.action_settings);

        MenuItem nextItem = menu.findItem(R.id.action_next);

        backItem.setVisible(true);
        webItem.setVisible(true);
        exitItem.setVisible(true);

        logoffItem.setVisible(false);
        helStatusItem.setVisible(false);
        nextItem.setVisible(false);
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
    }

}