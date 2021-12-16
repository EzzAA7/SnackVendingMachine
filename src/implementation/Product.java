package implementation;

import exceptions.NoSuchProductException;
import interfaces.SnackMachineInterface;

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

    public static Product checkProductValidity(SnackMachineInterface snackMachine, String choice, VMRunner v) throws NoSuchProductException {

        Product chosenProduct = snackMachine.getProducts().get(choice);
        // checking validity of such a product
        if(chosenProduct == null){
            throw new NoSuchProductException("No such product, try again");
        }
        else {
            System.out.println(" ");
            System.out.print("The selected product is the following:\n");
            System.out.println("     " + chosenProduct.getProductId() + "  " + chosenProduct.getName() + " | " + chosenProduct.getPrice() + " $   ");
        }
        return chosenProduct;
    }

}
