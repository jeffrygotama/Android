package com.example.ezyfood_project;

public class DatabaseOrder extends Database{
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public DatabaseOrder(String name, int price, int image, int quantity) {
        super(name, price, image);
        this.quantity = quantity;
    }
}
