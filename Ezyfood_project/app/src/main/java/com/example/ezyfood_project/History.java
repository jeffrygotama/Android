package com.example.ezyfood_project;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

public class History{
    private String name;
    private int price;
    private int quantity;
    private LatLng address;
    private Date date;
    private int image;

    public History(String name, int price, int quantity, LatLng address, Date date) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.date = date;
    }

    public History(String name, int price, int quantity, LatLng address, Date date, int image) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.date = date;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LatLng getAddress() {
        return address;
    }

    public void setAddress(LatLng address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
