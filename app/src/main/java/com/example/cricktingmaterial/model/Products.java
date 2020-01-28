package com.example.cricktingmaterial.model;

import java.io.Serializable;

public class Products implements Serializable {
    private  String name,price,image,description;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Products(String name, String price, String image, String description) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
    }
}
