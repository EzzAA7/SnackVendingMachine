package com.company;

public class Product {
    private int productId;
    private String name;
    private int price;
    private int quantity;

    Product(int productId, String name, int price, int quantity){
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    public int getProductId(){

        return productId;
    }

    public int getPrice(){

        return this.price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
