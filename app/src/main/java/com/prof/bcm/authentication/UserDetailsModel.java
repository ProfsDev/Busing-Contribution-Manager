package com.prof.bcm.authentication;

public class UserDetailsModel {
    private String email;
    private String fName;
    private String lName;
    private boolean isAdmin;

    public UserDetailsModel() {
    }

    public UserDetailsModel(String email, String fName, String lName, boolean isAdmin) {
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.isAdmin = isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}
