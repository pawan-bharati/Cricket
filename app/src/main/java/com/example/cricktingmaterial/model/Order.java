package com.example.cricktingmaterial.model;

public class Order {

    private String userid;
    private String price;
    private String billingaddress;
    private String billingnumber;
    private int ordernumber;


    public Order(String name, String userid, String price, String billingaddress, String billingnumber, int ordernumber) {
        this.name = name;
        this.userid = userid;
        this.price = price;
        this.billingaddress = billingaddress;
        this.billingnumber = billingnumber;
        this.ordernumber = ordernumber;
    }

    private String name;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBillingaddress() {
        return billingaddress;
    }

    public void setBillingaddress(String billingaddress) {
        this.billingaddress = billingaddress;
    }

    public String getBillingnumber() {
        return billingnumber;
    }

    public void setBillingnumber(String billingnumber) {
        this.billingnumber = billingnumber;
    }

    public int getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(int ordernumber) {
        this.ordernumber = ordernumber;
    }

}
