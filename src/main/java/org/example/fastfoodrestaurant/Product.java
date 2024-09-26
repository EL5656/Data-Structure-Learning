package org.example.fastfoodrestaurant;

abstract class Product {
    protected String name, prodID;
    protected double price;
    protected int amount;

    public Product(String name, String prodID, double price, int amount) {
        this.name = name;
        this.prodID = prodID;
        this.price=price;
        this.amount = amount;
    }
    public Product(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProdID() {
        return prodID;
    }

    public void setProdID(String prodID) {
        this.prodID = prodID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public abstract double calculateProdPrice(int number);

}