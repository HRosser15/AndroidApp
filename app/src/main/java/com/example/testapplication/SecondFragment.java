package com.example.testapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testapplication.databinding.ActivityMainBinding;
import com.example.testapplication.databinding.FragmentSecondBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class SecondFragment extends Fragment {

private FragmentSecondBinding binding;
public File userDatabase; //to get the local userDatabase file
private Scanner userDataScan; //to scan user database File
    UserLocalStore userLocalStore;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        userLocalStore = new UserLocalStore(SecondFragment.super.getContext());

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /** login screen "BACK" button event - goes to home screen*/
        //ignore this line
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        /** login screen "LOG IN" button event */    /** !!!!!!!!!!!! NOTE: log in button goes to home screen */
        binding.submitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                //currently log in sends button user back to home screen (disabled to show actual functionality)
                /*
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
                */

                //working on receiving user input below
                String userNameInput = binding.loginUsername.getText().toString(); //gets input from input text field
                String passWordInput = binding.loginPassword.getText().toString(); //gets input from input password field

                //to look at local userDatabase below
                try {
                    //looking at and comparing user database here



                } catch (Exception e) {

                }
                //if credentials match username and password in the system, navigate to account page.
                User user = new User(null, null);

                userLocalStore.storeUserData(user);
                userLocalStore.setUserLoggedIn(true);
                //else, pop up an error message that credentials don't match anything in our system.


            }
        });
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}