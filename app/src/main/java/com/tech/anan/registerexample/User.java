package com.tech.anan.registerexample;

public class User {
    int id;
    String email;
    String mobile;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String password;


    public User() {

    }

    public User(int id, String email, String mobile) {
        this.id = id;
        this.email = email;
        this.mobile = mobile;
    }


    // getting ID
    public int getID() {
        return this.id;
    }

    // setting id
    public void setID(int id) {
        this.id = id;
    }

    public String getEmail() {
        // TODO Auto-generated method stub
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        // TODO Auto-generated method stub
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

