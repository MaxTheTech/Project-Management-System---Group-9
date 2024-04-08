package dtu.projectManagementSystem.app;

import java.util.List;

public class SoftwareHuset {

    private static boolean isLoggedIn = false;

    private static String currentlyLoggedIn;

    public static void setCurrentlyLoggedIn(String login){currentlyLoggedIn=login;}

    public static String getCurrentlyLoggedIn(){return currentlyLoggedIn;}

    public static boolean isLoggedIn(){return isLoggedIn;}

    public static List<String> employees;

    public SoftwareHuset(){

    }



}
