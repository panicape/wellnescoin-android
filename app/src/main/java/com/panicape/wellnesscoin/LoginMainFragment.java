package com.panicape.wellnesscoin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.panicape.wellnesscoin.databinding.FragmentLoginMainBinding;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LoginMainFragment extends Fragment {

    private FragmentLoginMainBinding binding;

    CardView gallery;

    CardView wallet;

    CardView doPausas;

    CardView marketplace;


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

        doPausas = (CardView) view.getRootView().findViewById(R.id.pausaCV) ;
//        gallery = (CardView) view.getRootView().findViewById(R.id.profileCV) ;
        wallet = (CardView) view.getRootView().findViewById(R.id.walletCV) ;
        marketplace = (CardView) view.getRootView().findViewById(R.id.marketplaceCV) ;

        doPausas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(), "En construcción", Toast.LENGTH_SHORT).show();
            }
        });

//        gallery.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Navigation.findNavController(v).navigate(R.id.action_nav_login_home_to_nav_gallery);
//            }
//        });

        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_login_home_to_nav_slideshow);
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