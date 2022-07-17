package com.panicape.wellnesscoin.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.panicape.wellnesscoin.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class PausasStatusFragment extends Fragment {

    private List<String> pausas;


    // Constructor

    public PausasStatusFragment() {
        pausas = new ArrayList<>();

        pausas.add("pcarrillo_060620221522");
        pausas.add("pcarrillo_060620221109");
        pausas.add("pcarrillo_060620221731");
    }


    // Methods

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pausas_status, container, false);
    }

}