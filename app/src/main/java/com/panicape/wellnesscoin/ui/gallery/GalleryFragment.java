package com.panicape.wellnesscoin.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.panicape.wellnesscoin.R;
import com.panicape.wellnesscoin.databinding.FragmentGalleryBinding;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    private FirebaseFirestore firestoreDb;

    private TextView profNameValue;
    private TextView profEmailValue;
    private TextView profProgramValue;
    private TextView profUsernameValue;
    private TextView profDependValue;


    // Methods

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        firestoreDb = FirebaseFirestore.getInstance();

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        setHasOptionsMenu(true);

        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        profEmailValue = binding.profEmailVal;
        profEmailValue.setText(email);

        profNameValue = binding.profNameValue;
        profProgramValue = binding.programTV;
        profUsernameValue = binding.profUsernameValue;
        profDependValue = binding.profDependValue;


        // Cambiar metodo a getUserByEmail
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            profEmailValue.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

            DocumentReference docRef = firestoreDb.collection("Users")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getEmail().split("@")[0]);
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();

                        if (document.exists()) {
                            profNameValue.setText(document.getData().get ("name").toString());
                            profUsernameValue.setText(document.getData().get ("username").toString());
                            profProgramValue.setText(document.getData().get ("program").toString());
                            profDependValue.setText(document.getData().get ("dependency").toString());
                        }
                    }
                }
            });
        }

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
        profileItem.setVisible(false);
        mainItem.setVisible(true);

        helpItem.setVisible(false);
        webItem.setVisible(false);

        loginItem.setVisible(false);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}