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

public class SecondFragment extends Fragment {

private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

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
                //if credentials match username and password in the system, navigate to account page.
                //else, pop up an error message that credentials don't match anything in our system.


                //currently log in sends button user back to home screen
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);

                //working on recieving user input below
                String userNameInput = binding.loginUsername.getText().toString(); //gets input from input text field
                String passWordInput = binding.loginPassword.getText().toString(); //gets input from input password field
                

            }
        });
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}