package com.example.testapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.testapplication.R;
import com.example.testapplication.databinding.DepositCheckingBinding;

import java.util.ArrayList;
import java.util.Objects;

public class CheckingDeposit extends Fragment {
    private DepositCheckingBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState

            /** We need to grab the saving account balance from the database/file and assign it to the balance text field */
    ) {

        binding = DepositCheckingBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.bCheckingSubmitDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Stores amount user enters for deposit and convert to double.
                String depositAmount = Objects.requireNonNull(binding.tbCheckingDepositAmount.getText()).toString(); //gets input from input text field
                double decDepositAmount = Double.parseDouble(depositAmount);
                /** ^add this to balance and convert back to String */

                // Displays what balance will be once deposit is made
                TextView tvCheckingAfterDeposit = (TextView) getView().findViewById(R.id.tvCheckingAfterDeposit);
                /** Currenlty this doesn't show as it happens at the same time the page navigates away.
                 *  We should probably create a "Confirm Amount" button that is invisible and disabled
                 *  until this button is clicked. Then pressing the "Confirm Amount" button would complete
                 *  the deposit and complete the navigation below
                 */

                String[] currentUserData = UserData.GetCurrentUser();

                if (currentUserData[3].equals("Checking")) { //if the current user is a checking account, then set the balance, if not, find the checking account w same name and password
                    decDepositAmount = decDepositAmount+Double.parseDouble(currentUserData[4]);
                    UserData.setBalance(String.valueOf(decDepositAmount));
                } else {
                    try {
                        ArrayList<String[]> tempUserData = UserData.GetUserData();
                        for (String[] s : tempUserData) {
                            if (s[1].equals(currentUserData[1]) && s[3].equals("Checking")) {
                                decDepositAmount = decDepositAmount+Double.parseDouble(currentUserData[4]);
                                UserData.setBalance(String.valueOf(decDepositAmount));
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                tvCheckingAfterDeposit.setText(String.valueOf(decDepositAmount));


            }
        });



    }

}