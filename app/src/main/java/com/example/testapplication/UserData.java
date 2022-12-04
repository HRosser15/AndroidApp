package com.example.testapplication;

import java.util.ArrayList;

public class UserData {
    public static ArrayList<String[]> userData;
    public static String[] currentUser;

    public static void initialize() {

        //creating initial list of user account in the arrayList. If you want to add more, expand the array and add case below
        userData = new ArrayList<String[]>();

        String[] toAddInitially = new String[2];
        toAddInitially[0] = "1,bobert2,JiKy_M1!,Checking,1000.00"; //format: (account number (+1 each)), (userName), (password), (balance)
        toAddInitially[1] = "2,bobert2,JiKy_M1!,Savings,4000.00"; //format: (account number (+1 each)), (userName), (password), (balance)


        for (int i=0; i < toAddInitially.length; i++) {
            userData.add(toAddInitially[i].split(","));
        }
    }


    public static ArrayList<String[]> GetUserData() { //to return list of user data
        return userData;
    }

    public static void AddUserData(String[] toAdd) {
        userData.add(toAdd);
    }
    public static int GetUserNumber (String[] s) {
        boolean userFound = false;
        int toReturn= 0;
        for (String[] z: userData) {
            if (z.equals(s)) {
                userFound = true;
                toReturn = userData.indexOf(z);
            }
        }
        if (userFound) {
            return toReturn;
        } else {
            return -1;
        }
    }
    public static void SetCurrentUser(int j) {
        currentUser = userData.get(j);
    }
    public static String[] GetCurrentUser() {
        return currentUser;
    }
    public static void resetCurrentUser() {
        currentUser = null;
    }
    public static void setBalance (String s) {
        Float floatBalance = Float.parseFloat(s);
        String newBalance = String.format("%.02f", floatBalance);

            currentUser[4] = newBalance;
        }

    }
