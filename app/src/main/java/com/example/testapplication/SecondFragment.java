package com.example.testapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testapplication.databinding.ActivityMainBinding;
import com.example.testapplication.databinding.FragmentSecondBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;

import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Objects;
import java.util.Scanner;

public class SecondFragment extends Fragment {

private FragmentSecondBinding binding;
public File userDatabase; //to get the local userDatabase file
private Scanner userDataScan; //to scan user database File

    public String[][] userDataArray; //to store the data from the UserData file
    /* ================================================================================
        for connecting app to a server
    UserLocalStore userLocalStore;
    ================================================================================
         */


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        /* ================================================================================
        for connecting app to a server
        userLocalStore = new UserLocalStore(SecondFragment.super.getContext());
        ================================================================================
         */

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

                //Receive user input
                String userNameInput = Objects.requireNonNull(binding.loginUsername.getText()).toString(); //gets input from input text field
                String passWordInput = Objects.requireNonNull(binding.loginPassword.getText()).toString(); //gets input from input password field

                // Log username and password in Logcat
                Log.d("username", userNameInput);
                Log.d("password", passWordInput);

                /** Temporary if statement to test log in feature - needs to be updated to check match from file/database */
                if (userNameInput.equals("bob2") && passWordInput.equals("abcdef1!")) {

                    NavHostFragment.findNavController(SecondFragment.this)
                            .navigate(R.id.action_SecondFragment_to_AccountPage);
                }
                //pop up error if log in credentials don't match
                else {
                    Toast.makeText(SecondFragment.super.getContext(),
                            "Invalid username or password", Toast.LENGTH_LONG).show();
                }

                //to look at local userDatabase below
                try {
                    //looking at and comparing user database here


                   userDatabase = new File("UserData.csv");

                   userDataScan = new Scanner(userDatabase);
                   int i =0;
                    while (userDataScan.hasNextLine()) {
                        String temp = userDataScan.next();
                        String[] tempArray = temp.split(",");

                        for (int j=0; j < 6; j++) {
                            userDataArray[i][j] = tempArray[j];
                        }
                        i++;
                    }




                } catch (Exception e) {
                    System.out.println(e + ", "+ e.getMessage());
                }
                //if credentials match username and password in the system, navigate to account page.
                /* ================================================================================
                 Method for connecting app to a server
                User user = new User(null, null);

                userLocalStore.storeUserData(user);
                userLocalStore.setUserLoggedIn(true);
                //else, pop up an error message that credentials don't match anything in our system.
                =====================================================================================
                 */


            }
        });
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}