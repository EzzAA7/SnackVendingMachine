package com.company;

import com.company.com.implementation.Change;
import com.company.com.implementation.Product;
import com.company.com.implementation.SnackMachine;
import com.company.com.implementation.VMRunnder;
import com.company.com.money.Coin;

import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {

        //TODO: remove tests
        // TODO: check validity of choice


//        vmr.enterCoin(Coin.TEN_SHEKELS);
//        vmr.enterCoin(Coin.FIVE_SHEKELS);
//        vmr.enterCoin(Coin.TWO_SHEKELS);
//        vmr.enterCoin(Coin.TEN_SHEKELS);
//        vmr.enterCoin(Coin.TEN_SHEKELS);
//        vmr.enterCoin(Coin.TEN_SHEKELS);

        SnackMachine snackMachine = new SnackMachine();

        VMRunnder vmr = new VMRunnder(snackMachine);
        snackMachine.displayProducts();

        vmr.enterCoin(Coin.ONE_DOLLAR);
        vmr.enterCoin(Coin.ONE_DOLLAR);
        vmr.enterCoin(Coin.ONE_DOLLAR);
        vmr.enterCoin(Coin.ONE_DOLLAR);
        vmr.enterCoin(Coin.ONE_DOLLAR);

        int choice = inputProductAndBalance(vmr);

        Product chosenProduct = checkProductValidity(snackMachine, choice, vmr);

        // check if in balance (if entered is not less than the price)
        if(vmr.getEnteredSum().compareTo(chosenProduct.getPrice()) < 0){
            System.out.println("Not enough in balance, start entering");
        }

        // check if vending machine has enough money to return change
        else if(snackMachine.getBalance().compareTo(vmr.getEnteredSum().subtract(chosenProduct.getPrice())) < 0){
            System.out.println("Vending machine doesn't have enough change, contact admin");
        }

        else if( chosenProduct.getQuantity() < 1){
            System.out.println("Not in stock, contact admin");
        }

        else {

            try{
                // setup selected product
                vmr.setSelectedProd(chosenProduct);

                // initialize change amount
                vmr.setYourChange(new Change(vmr.getEnteredSum().subtract(chosenProduct.getPrice())));

                // calculate how change will return to user (in coins)
                snackMachine.calcChange(vmr.getYourChange());
                System.out.println(" ");
                System.out.println(vmr.getYourChange().toString());

                // decrement product quantity
                chosenProduct.setQuantity(chosenProduct.getQuantity() -1);

                // dispense product
                vmr.disposeSelectedProduct();

            }
            catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    private static Product checkProductValidity(SnackMachine snackMachine, int choice, VMRunnder v) {
        Product chosenProduct = snackMachine.getProducts().get(choice);
        if(chosenProduct == null){
            System.out.println("No such product, try again");

            //TODO: try again

            int newChoice = inputProductAndBalance(v);

            return checkProductValidity(snackMachine, newChoice, v);
        }
        else {
            System.out.println("The selected product is the following:");
            System.out.println("     " + chosenProduct.getProductId() + "  " + chosenProduct.getName() + " | " + chosenProduct.getPrice() + " Shekels   ");
        }
        return chosenProduct;
    }

    private static int inputProductAndBalance(VMRunnder vmr) {
        System.out.println("                                              ");
        System.out.println(" --- Please select a product (type its id) --- ");

        int choice = getChoice();
        // TODO: check validity of choice

        vmr.displayBalance();
        return choice;
    }

    private static int getChoice() {
        int choice;
        Scanner s = new Scanner(System.in);
        choice = s.nextInt();
        return choice;
    }
}
