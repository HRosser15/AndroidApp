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
import com.example.testapplication.databinding.WithdrawSavingsBinding;

import java.util.ArrayList;
import java.util.Objects;

public class SavingsWithdraw extends Fragment {
    private WithdrawSavingsBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState

            /** We need to grab the saving account balance from the database/file and assign it to the balance text field */
    ) {

        binding = WithdrawSavingsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.bSavingsSubmitWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] currentUserData = UserData.GetCurrentUser();
                // Stores amount user enters for Withdraw and convert to double.
                String WithdrawAmount = Objects.requireNonNull(binding.tbSavingsWithdrawAmount.getText()).toString(); //gets input from input text field
                float decWithdrawAmount = (-1) * Float.parseFloat(WithdrawAmount);
                /** ^add this to balance (it is negative)*/

                // Create a textView for Savings after Withdraw
                TextView tvSavingsAfterWithdraw = (TextView) getView().findViewById(R.id.tvSavingsAfterWithdraw);
                // Get user's balance
                float calculatedBalance = decWithdrawAmount + Float.parseFloat(currentUserData[4]);
                String calculatedBalanceString = String.format("%.02f", calculatedBalance);

                // add Withdraw to balance
                currentUserData[4] = String.valueOf(calculatedBalance);

                if (currentUserData[3].equals("Savings")) { //if the current user is a savings account, then set the balance, if not, find the checking account w same name and password
                    binding.tvSavingsAfterWithdraw.setText(String.valueOf(calculatedBalance));
                } else {
                    try {
                        ArrayList<String[]> tempUserData = UserData.GetUserData();
                        for (String[] s : tempUserData) {
                            if (s[1].equals(currentUserData[1]) && s[3].equals("Savings")) {
                                binding.tvSavingsAfterWithdraw.setText(calculatedBalanceString);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                // Displays what balance will be once Withdraw is made
                tvSavingsAfterWithdraw.setText(String.valueOf(calculatedBalanceString));

                //This "Confirm" button will appear once the user clicks "Withdraw"
                Button confirmWithdraw = Objects.requireNonNull(binding.bSavingsConfirmWithdraw);
                showButton(R.id.bSavingsConfirmWithdraw);

            }
        });

        binding.bSavingsConfirmWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Get updated balance and cast to String
                TextView tvSavingsAfterDeposit = (TextView) getView().findViewById(R.id.tvSavingsAfterWithdraw);
                String newBalance = tvSavingsAfterDeposit.toString();

                /** We will need to update User balance with a method here */
                // UserData.setBalance(newBalance);


                NavHostFragment.findNavController(com.example.testapplication.SavingsWithdraw.this)
                        .navigate(R.id.action_SavingsWithdraw_to_SavingsPage);
            }
        });



    }

    public void showButton(int id) {
        Button confirmButton = (Button) getView().findViewById(id);
        confirmButton.setEnabled(true);
    }
}