package com.company;

public enum Product {
    TWIX(1,4, 3),
    COKE(2,5, 2),
    WATER(3,2,5),
    SANDWICH(4,7, 3),
    EMPTY(0,0, 0);

    private int productId;
    private int price;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int quantity;

    Product(int productId, int price, int quantity){
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public int getProductId(){
        return productId;
    }

    public int getPrice(){
        return this.price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public static Product valueOf(int numberSelection){
        for(Product product: Product.values()){
            if(numberSelection == product.getProductId()){
                return product;
            }
        }
        return EMPTY;
    }

    public static Product getProduct(int numberSelection){
        for(Product product: Product.values()){
            if(numberSelection == product.getProductId()){
                return product;
            }
        }
        return null;
    }


}
