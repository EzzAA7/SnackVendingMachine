package com.company;

import java.util.*;

public class VendingMachine {

    private EnumMap<Coin, Integer> balanceCoins;
    private HashMap<Integer, Product> products;
    private int balance;

    public VendingMachine() {
        this.balanceCoins = new EnumMap<>(Coin.class);
        this.products = new HashMap<>();
        setupMachineMoney(balanceCoins);
        fillMachineProducts();
        this.balance = calculateBalance(balanceCoins);
    }

    public EnumMap<Coin, Integer> getBalanceCoins() {

        return balanceCoins;
    }

    public int getBalance() {

        return balance;
    }

    public void setBalance(int balance) {

        this.balance = balance;
    }

    public void setBalanceCoins(EnumMap<Coin, Integer> balanceCoins) {

        this.balanceCoins = balanceCoins;
    }

    public HashMap<Integer, Product> getProducts() {
        return products;
    }

    public void setProducts(HashMap<Integer, Product> products) {
        this.products = products;
    }

    private int calculateBalance(EnumMap<Coin, Integer> balanceCoins) {
        int sum = 0;
        for (EnumMap.Entry<Coin, Integer> entry : balanceCoins.entrySet()) {
            sum += entry.getValue() * entry.getKey().getRepresentVal();
        }
        return sum;
    }

    private void setupMachineMoney(EnumMap<Coin, Integer> balanceCoins) {
        balanceCoins.put(Coin.TEN_SHEKELS, 1);
        balanceCoins.put(Coin.FIVE_SHEKELS, 6);
        balanceCoins.put(Coin.TWO_SHEKELS, 5);
        balanceCoins.put(Coin.ONE_SHEKELS, 10);
    }

    private void fillMachineProducts() {
        products.put(1, new Product(1, "TWIX", 5, 3));
        products.put(2, new Product(2, "KITKAT", 4, 5));
        products.put(3, new Product(3, "XL", 5, 10));
        products.put(4, new Product(4, "WATER", 2, 2));
        products.put(5, new Product(5, "COCA-COLA", 4, 7));
    }

    public void displayProducts() {
        System.out.println(" *********************************************");
        System.out.println("     WELCOME TO THE FREIGHTOS VENDING MACHINE           ");
        System.out.println(" *********************************************");
        System.out.println("      _________Our Products available_______               ");
        System.out.println("                                              ");

        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            System.out.println("    - " + entry.getValue().getProductId() + " -  " + entry.getValue().getName() + " | " + entry.getValue().getPrice() + " Shekels   " + " (Quantity: " + entry.getValue().getQuantity() + ")");
        }

        System.out.println("                                              ");
        System.out.println(" --- Please select a product (type its id) --- ");
    }

    public void calcChange(Change c){

        int amount = c.getAmount();

        int[] res = new int[4];

        // FOR THE 10 SHEKELS EQUIVALENT
        
        // Check if we have 10s in our balance inventory in the first place
        if(this.balanceCoins.get(Coin.TEN_SHEKELS) > 0){
            // If the num of 10s in our inventory is higher than 10s in the entered sum we can use as much as possible
            if(this.balanceCoins.get(Coin.TEN_SHEKELS) >= amount / Coin.TEN_SHEKELS.getRepresentVal() ) {
                res[0] = amount / Coin.TEN_SHEKELS.getRepresentVal();
            }
            // if not then use all possible 10s
            else
                res[0] = this.balanceCoins.get(Coin.TEN_SHEKELS);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 10s 
            if(res[0] > 0){
                amount -= Coin.TEN_SHEKELS.getRepresentVal() * res[0];
                this.balanceCoins.put(Coin.TEN_SHEKELS, this.balanceCoins.get(Coin.TEN_SHEKELS) - res[0]);
            }
        }


        // FOR THE 5 SHEKELS EQUIVALENT

        // Check if we have 5s in our balance inventory in the first place
        if(this.balanceCoins.get(Coin.FIVE_SHEKELS) > 0){
            // If the num of 5s in our inventory is higher than 5s in the entered sum we can use as much as possible
            if(this.balanceCoins.get(Coin.FIVE_SHEKELS) >= amount / Coin.FIVE_SHEKELS.getRepresentVal() ) {
                res[1] = amount / Coin.FIVE_SHEKELS.getRepresentVal();
            }
            // if not then use all possible 5s
            else
                res[1] = this.balanceCoins.get(Coin.FIVE_SHEKELS);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 5s 
            if(res[1] > 0){
                amount -= Coin.FIVE_SHEKELS.getRepresentVal() * res[1];
                this.balanceCoins.put(Coin.FIVE_SHEKELS, this.balanceCoins.get(Coin.FIVE_SHEKELS) - res[1]);
            }
        }

        // FOR THE 2 SHEKELS EQUIVALENT

        // Check if we have 2s in our balance inventory in the first place
        if(this.balanceCoins.get(Coin.TWO_SHEKELS) > 0){
            // If the num of 2s in our inventory is higher than 2s in the entered sum we can use as much as possible
            if(this.balanceCoins.get(Coin.TWO_SHEKELS) >= amount / Coin.TWO_SHEKELS.getRepresentVal() ) {
                res[2] = amount / Coin.TWO_SHEKELS.getRepresentVal();
            }
            // if not then use all possible 2s
            else
                res[2] = this.balanceCoins.get(Coin.TWO_SHEKELS);

            // the amount is updated to go forward with next coins
            // the balance inventory is decremented is case we have a new value for the num of 2s 
            if(res[2] > 0) {
                this.balanceCoins.put(Coin.TWO_SHEKELS, this.balanceCoins.get(Coin.TWO_SHEKELS) - res[2]);
                amount -= Coin.TWO_SHEKELS.getRepresentVal() * res[2];
            }
        }

        // FOR THE 1 SHEKEL EQUIVALENT

        // Check if we have 2s in our balance inventory in the first place
        if(this.balanceCoins.get(Coin.ONE_SHEKELS) > 0){
            // If the num of 1s in our inventory is higher than 1s in the entered sum we can use as much as possible
            if(this.balanceCoins.get(Coin.ONE_SHEKELS) >= amount / Coin.ONE_SHEKELS.getRepresentVal() ) {
                res[3] = amount / Coin.ONE_SHEKELS.getRepresentVal();
            }
            // if not then use all possible 1s
            else
                res[3] = this.balanceCoins.get(Coin.ONE_SHEKELS);

            // the amount is updated to go forward with next coins
            // the balance inventory is decremented is case we have a new value for the num of 1s 
            if(res[3] > 0) {
                amount -= Coin.ONE_SHEKELS.getRepresentVal() * res[3];
                this.balanceCoins.put(Coin.ONE_SHEKELS, this.balanceCoins.get(Coin.ONE_SHEKELS) - res[3]);
            }
        }

        // set coin count for change
        c.setNumOfCoins(res);

        // update to new machine balance
        this.setBalance(calculateBalance(balanceCoins));

    }

}
