package com.example.cricktingmaterial.model;

public class UsersCUD {

    private String fname;
    private String lname;
    private String address;
    private String number;
    private String email;
    private String password;
  //  private String security;


    //to update,add and delete
    public UsersCUD(String fname, String lname, String address, String number, String email, String password) {
        this.fname = fname;
        this.lname = lname;
        this.address = address;
        this.number = number;
        this.email = email;
        this.password = password;
     //   this.security=security;
    }



}
