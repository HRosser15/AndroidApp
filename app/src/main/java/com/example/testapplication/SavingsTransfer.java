package com.example.testapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testapplication.R;
import com.example.testapplication.databinding.TransferSavingsBinding;

import java.util.ArrayList;
import java.util.Objects;

public class SavingsTransfer extends Fragment {
    private TransferSavingsBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState

            /** We need to grab the saving account balance from the database/file and assign it to the balance text field */
    ) {

        binding = TransferSavingsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.bSavingsTransferToChecking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment.findNavController(com.example.testapplication.SavingsTransfer.this)
                        .navigate(R.id.action_SavingsTransfer_to_SavingsTransferToSavings);

            }
        });





    }

    public void showButton(int id) {
        Button confirmButton = (Button) getView().findViewById(id);
        confirmButton.setVisibility(View.VISIBLE);
        confirmButton.setEnabled(true);
    }
}
