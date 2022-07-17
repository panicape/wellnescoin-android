package com.panicape.wellnesscoin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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

    CardView wallet, doPausas, marketplace;



    // Methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Navigation.findNavController(view).clearBackStack(R.id.nav_login);

        doPausas = (CardView) view.getRootView().findViewById(R.id.pausaCV) ;
        marketplace = (CardView) view.getRootView().findViewById(R.id.marketplaceCV) ;

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

        marketplace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_login_home_to_nav_marketplace);
            }
        });
    }

}