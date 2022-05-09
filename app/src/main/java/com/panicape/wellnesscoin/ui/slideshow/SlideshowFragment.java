package com.panicape.wellnesscoin.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.panicape.wellnesscoin.R;
import com.panicape.wellnesscoin.databinding.FragmentSlideshowBinding;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;

    private ArrayAdapter<String> lvAdapter;
    private ListView pausasCheckLV;

    private Button redimirBtn, reloadBtn;
    private EditText walletET;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        pausasCheckLV = (ListView) root.findViewById(R.id.pausasCheckedLV);
        lvAdapter = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_list_item_1, slideshowViewModel.getList().getValue());
        pausasCheckLV.setAdapter(lvAdapter);

        walletET = (EditText) root.findViewById(R.id.walletField);
        walletET.setText(slideshowViewModel.getWalletValue().getValue());

        redimirBtn = (Button) root.findViewById(R.id.redimirBtn);
        redimirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                        "Se ha redimido las recompensas de su pausa con éxito",
                        Toast.LENGTH_SHORT).show();
                lvAdapter = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_list_item_1, new ArrayList<String>());
                pausasCheckLV.setAdapter(lvAdapter);
                int listSize = slideshowViewModel.getList().getValue().size();
                Integer reward = Integer.valueOf(walletET.getText().toString()) + listSize*2;
                walletET.setText(reward.toString());
            }
        });

        reloadBtn = (Button) root.findViewById(R.id.updateBtn);
        reloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                lvAdapter = new ArrayAdapter<>(root.getContext(), android.R.layout.simple_list_item_1, new ArrayList<String>());
//                pausasCheckLV.setAdapter(lvAdapter);
                Toast.makeText(v.getContext(),
                        "Actualizado",
                        Toast.LENGTH_SHORT).show();
            }
        });
        return root;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}