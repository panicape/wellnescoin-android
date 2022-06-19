package com.panicape.wellnesscoin.ui.wallet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.panicape.wellnesscoin.R;
import com.panicape.wellnesscoin.databinding.FragmentWalletBinding;

import java.util.ArrayList;

public class WalletFragment extends Fragment {

    private WalletViewModel slideshowViewModel;
    private FragmentWalletBinding binding;

    private WebView walletWebView;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(WalletViewModel.class);

        binding = FragmentWalletBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        walletWebView = binding.walletWV;
        walletWebView.loadUrl("https://www.cyclos.org/");

        return root;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}