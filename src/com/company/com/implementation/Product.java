package com.company.com.implementation;

import java.math.BigDecimal;

public class Product {
    private String productId;
    private String name;
    private BigDecimal price;
    private int quantity;

    Product(String productId, String name, BigDecimal price, int quantity){
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

    public String getProductId(){

        return productId;
    }

    public BigDecimal getPrice(){

        return this.price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
