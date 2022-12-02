package com.example.testapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testapplication.databinding.PageCheckingBinding;

import java.util.ArrayList;

public class CheckingPage extends Fragment {
    private PageCheckingBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState)
            /** We need to grab the checking account balance from the database/file and assign it to the balance text field
             * Done ^^*/

    {

        binding = PageCheckingBinding.inflate(inflater, container, false);
        String[] currentUserData = UserData.GetCurrentUser();

        if (currentUserData[3].equals("Checking")) { //if the current user is a checking account, then set the balance, if not, find the checking account w same name and password
            binding.decBalance.setText(currentUserData[4]);
        } else {
            try {
                ArrayList<String[]> tempUserData = UserData.GetUserData();
                for (String[] s : tempUserData) {
                    if (s[1].equals(currentUserData[1]) && s[3].equals("Checking")) {
                        binding.decBalance.setText(s[4]);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*
        binding.bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AccountPage.this)
                        .navigate(R.id.action_AccountPage_to_FirstFragment);
            }
        });

        binding.bChecking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(AccountPage.this)
                        .navigate(R.id.action_AccountPage_to_CheckingPage);
            }

        });

         */
    }

}
