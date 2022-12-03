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
import com.example.testapplication.databinding.DepositSavingsBinding;

import java.util.ArrayList;
import java.util.Objects;

public class SavingsDeposit extends Fragment {
    private DepositSavingsBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState

            /** We need to grab the saving account balance from the database/file and assign it to the balance text field */
    ) {

        binding = DepositSavingsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.bSavingsSubmitDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] currentUserData = UserData.GetCurrentUser();
                // Stores amount user enters for deposit and convert to double.
                String depositAmount = Objects.requireNonNull(binding.tbSavingsDepositAmount.getText()).toString(); //gets input from input text field
                float decDepositAmount = Float.parseFloat(depositAmount);
                /** ^add this to balance*/

                // Create a textView for Savings after deposit
                TextView tvSavingsAfterDeposit = (TextView) getView().findViewById(R.id.tvSavingsAfterDeposit);
                // Get user's balance
                float calculatedBalance = decDepositAmount + Float.parseFloat(currentUserData[4]);
                String calculatedBalanceString = String.format("%.02f", calculatedBalance);

                // add deposit to balance
                currentUserData[4] = String.valueOf(calculatedBalance);

                if (currentUserData[3].equals("Savings")) { //if the current user is a checking account, then set the balance, if not, find the checking account w same name and password
                    binding.tvSavingsAfterDeposit.setText(String.valueOf(calculatedBalance));
                } else {
                    try {
                        ArrayList<String[]> tempUserData = UserData.GetUserData();
                        for (String[] s : tempUserData) {
                            if (s[1].equals(currentUserData[1]) && s[3].equals("Savings")) {
                                binding.tvSavingsAfterDeposit.setText(calculatedBalanceString);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                // Displays what balance will be once deposit is made
                tvSavingsAfterDeposit.setText(String.valueOf(calculatedBalanceString));

                //This "Confirm" button will appear once the user clicks "Deposit"
                Button confirmDeposit = Objects.requireNonNull(binding.bSavingsConfirmDeposit);
                showButton(R.id.bSavingsConfirmDeposit);

            }
        });

        binding.bSavingsConfirmDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Get updated balance and cast to String
                TextView tvSavingsAfterDeposit = (TextView) getView().findViewById(R.id.tvSavingsAfterDeposit);
                String newBalance = tvSavingsAfterDeposit.toString();

                /** We will need to update User balance with a method here */
                // UserData.setBalance(newBalance);


                NavHostFragment.findNavController(com.example.testapplication.SavingsDeposit.this)
                        .navigate(R.id.action_SavingsDeposit_to_SavingsPage);
            }
        });



    }

    public void showButton(int id) {
        Button confirmButton = (Button) getView().findViewById(id);
        confirmButton.setVisibility(View.VISIBLE);
        confirmButton.setEnabled(true);
    }
}