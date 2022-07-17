package com.panicape.wellnesscoin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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
    private ListView pausasLV;
    private List<String> pausasList;
    private Button startBtn;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(this).get(MainViewModel.class);

        binding = MainFragmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        startBtn = (Button) root.findViewById(R.id.startBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent walkIntent = new Intent(getActivity(), WalkActivity.class);
                //startActivity(walkIntent);

                Intent handsIntent = new Intent(getActivity(), PausaHands.class);
                startActivity(handsIntent);
            }
        });
        pausasList = new ArrayList<String>();
        pausasList.add("Caminar");
        pausasList.add("Manos");

        pausasLV = binding.pausasLV;
        ArrayAdapter adapter = new ArrayAdapter(container.getContext(), android.R.layout.simple_list_item_1, pausasList);
        pausasLV.setAdapter(adapter);

        return root;
    }


}