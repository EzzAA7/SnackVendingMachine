package com.company;

import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {

        VendingMachine vendingMachine = new VendingMachine();

        VMRunnder vmr = new VMRunnder(vendingMachine);
        vendingMachine.displayProducts();

        vmr.displayBalance();

        //TODO: remove tests
        vmr.enterCoin(Coin.TEN_SHEKELS);
        vmr.enterCoin(Coin.TEN_SHEKELS);
        vmr.enterCoin(Coin.TEN_SHEKELS);
        vmr.enterCoin(Coin.TEN_SHEKELS);
        vmr.enterCoin(Coin.TWO_SHEKELS);

//        vmr.enterCoin(Coin.TEN_SHEKELS);
//        vmr.enterCoin(Coin.FIVE_SHEKELS);
//        vmr.enterCoin(Coin.TWO_SHEKELS);
//        vmr.enterCoin(Coin.TEN_SHEKELS);
//        vmr.enterCoin(Coin.TEN_SHEKELS);
//        vmr.enterCoin(Coin.TEN_SHEKELS);


        int choice = getChoice();

        Product chosenProduct = Product.getProduct(choice);
        if(chosenProduct == null){
            System.out.println("No such product, try again");
            //TODO: try again
        }
        else {
            System.out.println("The selected product is the following:");
            System.out.println("     " + chosenProduct.getProductId() + "  " + chosenProduct.name() + " | " + chosenProduct.getPrice() + " Shekels   ");
        }

        //TODO: check if in balance
        if(vmr.getEnteredSum() < chosenProduct.getPrice()){
            System.out.println("Not enough in balance, start entering");
        }
        else if(vendingMachine.getBalance() < vmr.getEnteredSum() - chosenProduct.getPrice()){
            System.out.println("Vending machine doesn't have enough change, contact admin");
        }
        //TODO: decremnt balance, return possible change, reset sum, reset change, dispense product
        else {
            vmr.setSelectedProd(chosenProduct);
            Change change = new Change(vmr.getEnteredSum() - chosenProduct.getPrice());

            vendingMachine.calcChange(change);
            System.out.println(" ");
            System.out.println(change.toString());

        }





    }

    private static int getChoice() {
        int choice;
        Scanner s = new Scanner(System.in);
        choice = s.nextInt();
        return choice;
    }
}
