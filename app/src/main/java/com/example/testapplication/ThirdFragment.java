package com.example.testapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.testapplication.databinding.FragmentThirdBinding;


public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentThirdBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /** "BACK" button event - goes to home screen */
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_FirstFragment);
            }
        });

        /** "SIGN UP" button event - Pops up error if invalid username or password; does nothing if valid */
        binding.submitSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get user input username and password
                String username = binding.signupUsername.getText().toString(); //gets input from input text field
                String password = binding.signupPassword.getText().toString(); //gets input from input password field

                // Log new username and password in Logcat
                Log.d("username", username);
                Log.d("password", password);

                // determine if username is unique
                if (!usernameIsUnique(username)) {
                    Toast.makeText(ThirdFragment.super.getContext(),
                            "ERROR: Username is already in use", Toast.LENGTH_LONG).show();

                }

                // determine if password is strong
                if (!pwIsStrong(password)) {
                    Toast.makeText(ThirdFragment.super.getContext(),
                            "ERROR: Entered password does not meet requirements", Toast.LENGTH_LONG).show();
                }
                // =====================================================================================
                // NOTE: we cloud add a static string and add the reasons as to why the password doesn't
                // meet requirements, but that is low priority right now.
                // =====================================================================================



                // If username and password are valid, create account and switch pages
                if (pwIsStrong(password) && usernameIsUnique(username)) {
                    // create a new user with input values
                    User newUser = new User(username, password);

                    // Log userId in Logcat
                    Log.d("userId", String.valueOf(newUser.userId));

                    // creates default checking account with new user's userId
                    Checking checkingAccount = new Checking(newUser.userId);

                    NavHostFragment.findNavController(ThirdFragment.this)
                            .navigate(R.id.action_ThirdFragment_to_FourthFragment);
                }

                // This 2-line code section below will navigate, I'm just leaving it here in
                // case we can use it to navigate to the next fragment once we have it set up.
                    //  NavHostFragment.findNavController(ThirdFragment.this)
                    //          .navigate(R.id.action_ThirdFragment_to_FirstFragment);
            }
        });

    }





    //Tests if username is unique
    public boolean usernameIsUnique(String s) {
        // This will need to read from a file or database
        // and confirm the username does not match any existing usernames
        if (s.equals("bob1")) {
            return false;
        }
        return true;
    }

    //Tests if password meets requirements
    public boolean pwIsStrong(String s) {
        boolean lengthIsValid = false;
        boolean containsNumber = false;
        boolean containsSpace = false;
        boolean containsSpecialChar = false;

        // tests password length
        if (s.length() >= 8) {
            lengthIsValid = true;
        }

        // tests password contains at least one number,
        // one special character, and no spaces
        for (int i = 0; i < s.length(); i++) {
            // Confirm password contains a digit
            if (Character.isDigit(s.charAt(i))) {
                containsNumber = true;
            }
            // Confirm password has no spaces
            if (Character.isWhitespace(s.charAt(i))) {
                containsSpace = true;
            }
            // Confirm password has at least one special character
            if (s.charAt(i) == 33 || s.charAt(i) == 35 ||
                    s.charAt(i) == 36 || s.charAt(i) == 38 ||
                    s.charAt(i) == 63 || s.charAt(i) == 95 ) {
                containsSpecialChar = true;
            }
        }

        // Send error message if requirements are not met
        if (!lengthIsValid || !containsNumber || containsSpace || !containsSpecialChar) {
            return false;
        }
        // else, return true
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}