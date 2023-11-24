package com.prof.bcm.authentication;

public class ApplicationUser {
    private static UserDetailsModel userDetails;


    public static UserDetailsModel getUserDetails() {
        return userDetails;
    }

    public static void setUserDetails(UserDetailsModel userDetails) {
        ApplicationUser.userDetails = userDetails;
    }

    public static void removeUser(){
        userDetails = null;
    }
}
