package com.panicape.wellnesscoin.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.panicape.wellnesscoin.R;
import com.panicape.wellnesscoin.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private LoginViewModel loginViewModel;
    private FragmentLoginBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = binding.username;
        final EditText passwordEditText = binding.password;
        final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                FirebaseAuth.getInstance().signInWithEmailAndPassword(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            showLoginMsg("Bienvenido/a");
                            loadingProgressBar.setVisibility(View.INVISIBLE);

                            NavigationView navigationView = (NavigationView) v.getRootView().findViewById(R.id.nav_view);
                            navigationView.getMenu().findItem(R.id.nav_login).setVisible(false);
                            navigationView.getMenu().findItem(R.id.nav_gallery).setVisible(true);
//                            navigationView.getMenu().findItem(R.id.nav_slideshow).setVisible(true);
                            navigationView.getMenu().findItem(R.id.nav_marketplace).setVisible(true);
                            navigationView.getMenu().findItem(R.id.nav_login_main).setVisible(true);

//                            Navigation.findNavController(v).navigate(R.id.action_nav_login_to_nav_home);
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        if (loginViewModel != null) {
            loginViewModel = null;
        }
    }
}