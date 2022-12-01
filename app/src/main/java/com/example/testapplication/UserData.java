package com.example.testapplication;

import java.util.ArrayList;

public class UserData {
    public static ArrayList<String[]> userData;

    public static void initialize() {

        //creating initial list of user account in the arrayList. If you want to add more, expand the array and add case below
        userData = new ArrayList<String[]>();

        String[] toAddInitially = new String[2];
        toAddInitially[0] = "1,bobert2,JiKy_M!,Checking,1000"; //format: (account number (+1 each)), (userName), (password), (balance)
        toAddInitially[1] = "2,bobert2,JiKy_M!,Savings,4000"; //format: (account number (+1 each)), (userName), (password), (balance)


        for (int i=0; i < toAddInitially.length; i++) {
            userData.add(toAddInitially[i].split(","));
        }
    }


    public static ArrayList<String[]> GetUserData() { //to return list of user data
        return userData;
    }

    public void AddUserData(String[] toAdd) {
        userData.add(toAdd);
    }


}
