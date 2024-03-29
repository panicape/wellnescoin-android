package com.panicape.wellnesscoin.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.panicape.wellnesscoin.HelpMainActivity;
import com.panicape.wellnesscoin.R;

/**
 *
 * @author panicape
 * @version 0.01 May 2022
 */
public class LoginFragment extends Fragment {

    LoginViewModel loginViewModel;

    com.panicape.wellnesscoin.databinding.FragmentLoginBinding binding;

    private EditText usernameEditText;
    private EditText passwordEditText;

    private ImageButton logInfoBtn;
    private Button loginButton;

    MenuItem backItem;

    ProgressBar loadingProgressBar;


    // Methods

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = com.panicape.wellnesscoin.databinding.FragmentLoginBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        usernameEditText = binding.username;
        passwordEditText = binding.password;

        loadingProgressBar = binding.loading;

        loginButton = binding.login;
        logInfoBtn = binding.logInfoBtn;

        logInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.INVISIBLE);

                Intent infoIntent = new Intent(getActivity(), HelpMainActivity.class);
                startActivity(infoIntent);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                FirebaseAuth.getInstance().signInWithEmailAndPassword(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString()).addOnCompleteListener(
                                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loadingProgressBar.setVisibility(View.INVISIBLE);
                            Navigation.findNavController(v).navigate(R.id.login_home_frag);
                        } else {
                            showLoginMsg("Credenciales erroneas");
                            loadingProgressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        });
    }

    private void showLoginMsg(String msg) {
        if (getContext() != null && getContext().getApplicationContext() != null) {
            Toast.makeText(getContext().getApplicationContext(),
                    msg, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        backItem = menu.findItem(R.id.action_back);
        if (backItem.isVisible()) {
            backItem.setVisible(false);
        }

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        backItem = menu.findItem(R.id.action_back);
        if (backItem.isVisible()) {
            backItem.setVisible(false);
        }
    }



    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

        if (loginViewModel != null) {
            loginViewModel = null;
        }
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseAuth.getInstance().signOut();
        }
    }

}