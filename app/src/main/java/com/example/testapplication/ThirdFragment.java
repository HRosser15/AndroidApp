package com.example.testapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        /** sign up screen "BACK" button event - goes to home screen */
        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_FirstFragment);
            }
        });

        /** sign up screen "SIGN UP" button event */

        /** !!! Needs edit - sign up button goes to home screen !!!
         * Ideally, it will take the user to a new page that welcomes them and asks them to log in */
        binding.submitSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity m = new MainActivity();
                m.handleText(view);
                // if username is NOT unique, pop up error message
                // if password does NOT meet requirements, pop up error message
                // else, create account and return to main page for user to log in.

                //currently sign up sends button user back to home screen
                //NavHostFragment.findNavController(ThirdFragment.this)
                //        .navigate(R.id.action_ThirdFragment_to_FirstFragment);
            }
        });

    }




    public void handleText(View v) {

    }
    public boolean usernameIsUnique(String s) {
        if (s.equals("bob1")) {
            return false;
        }
        return true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}