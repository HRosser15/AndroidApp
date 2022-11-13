package com.example.testapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testapplication.databinding.PageSavingsBinding;

public class SavingsPage extends Fragment {
    private PageSavingsBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState

            /** We need to grab the saving account balance from the database/file and assign it to the balance text field */
    ) {

        binding = PageSavingsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.bSavingsDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SavingsPage.this)
                        .navigate(R.id.action_SavingsPage_to_SavingsDeposit);
            }
        });

        binding.bSavingsWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SavingsPage.this)
                        .navigate(R.id.action_SavingsPage_to_SavingsWithdraw);
            }

        });

        binding.bSavingsTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SavingsPage.this)
                        .navigate(R.id.action_SavingsPage_to_SavingsTransfer);
            }

        });


    }

}