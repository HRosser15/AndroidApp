package com.example.testapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.testapplication.databinding.PageAccountBinding;

public class AccountPage extends Fragment {
    private PageAccountBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = PageAccountBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

        binding.bSavings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // if user does not have savings account, pop up error
                NavHostFragment.findNavController(AccountPage.this)
                        .navigate(R.id.action_AccountPage_to_SavingsPage);
            }
        });
        // We can create a button to create a savings account

    }
}
