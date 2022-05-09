package com.panicape.wellnesscoin.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.panicape.wellnesscoin.R;
import com.panicape.wellnesscoin.databinding.MarketplaceFragmentBinding;

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

        WebView myWebView = (WebView) root.findViewById(R.id.marketplaceView);
        myWebView.loadUrl("https://www.google.com/");

        return root;
    }


}