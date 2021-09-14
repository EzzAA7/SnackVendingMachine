package com.company.com.implementation;

import com.company.com.money.Coin;
import com.company.com.money.Note;

import java.math.BigDecimal;
import java.util.*;

public class SnackMachine {

    private EnumMap<Coin, Integer> balanceCoins;
    private EnumMap<Note, Integer> balanceNotes;
    private HashMap<Integer, Product> products;
    private BigDecimal balance;

    public SnackMachine() {
        this.balanceCoins = new EnumMap<>(Coin.class);
        this.balanceNotes = new EnumMap<>(Note.class);
        this.products = new HashMap<>();
        setupMachineMoney();
        fillMachineProducts();
        this.balance = calculateBalance();
    }

    public EnumMap<Coin, Integer> getBalanceCoins() {

        return balanceCoins;
    }

    public BigDecimal getBalance() {

        return balance;
    }

    public void setBalance(BigDecimal balance) {

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

    public BigDecimal calculateBalance() {
        BigDecimal sum = BigDecimal.valueOf(0.00);
        for (EnumMap.Entry<Coin, Integer> entry : balanceCoins.entrySet()) {
            sum = sum.add(BigDecimal.valueOf(entry.getValue()).multiply(entry.getKey().getRepresentVal()));
        }

        for (EnumMap.Entry<Note, Integer> entry : balanceNotes.entrySet()) {
            sum = sum.add(BigDecimal.valueOf(entry.getValue()).multiply(entry.getKey().getRepresentVal()));
        }
        return sum;
    }

    public void setupMachineMoney() {
        balanceCoins.put(Coin.ONE_DOLLAR, 6);
        balanceCoins.put(Coin.FIFTY_CENTS, 4);
        balanceCoins.put(Coin.TWENTY_CENTS, 5);
        balanceCoins.put(Coin.TEN_CENTS, 10);

        balanceNotes.put(Note.FIFTY_DOLLARS, 2);
        balanceNotes.put(Note.TWENTY_DOLLARS, 5);

    }

    public void fillMachineProducts() {
        products.put(1, new Product(1, "TWIX", BigDecimal.valueOf(4.50), 3));
        products.put(2, new Product(2, "KITKAT", BigDecimal.valueOf(4.30), 5));
        products.put(3, new Product(3, "XL", BigDecimal.valueOf(5.50), 10));
        products.put(4, new Product(4, "WATER", BigDecimal.valueOf(1.70), 2));
        products.put(5, new Product(5, "COCA-COLA", BigDecimal.valueOf(2.90), 7));
    }

    public void displayProducts() {
        System.out.println(" *********************************************");
        System.out.println("     WELCOME TO THE FREIGHTOS VENDING MACHINE           ");
        System.out.println(" *********************************************");
        System.out.println("      _________Our Products available_______               ");
        System.out.println("                                              ");

        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            System.out.println("    - " + entry.getValue().getProductId() + " -  " + entry.getValue().getName() + " | " + entry.getValue().getPrice() + " USD   " + " (Quantity: " + entry.getValue().getQuantity() + ")");
        }
    }

    public void calcChange(Change c){

        BigDecimal amount = c.getAmount();

        int[] res = new int[6];

        // FOR THE 50 DOLLARS EQUIVALENT

        // Check if we have 10s in our balance inventory in the first place
        if(this.balanceNotes.get(Note.FIFTY_DOLLARS) > 0){
            // If the num of 50s in our inventory is higher than 50s in the entered sum we can use as much as possible
            if(this.balanceNotes.get(Note.FIFTY_DOLLARS) >= amount.divide(Note.FIFTY_DOLLARS.getRepresentVal()).intValue()) {
                res[0] = amount.divide(Note.FIFTY_DOLLARS.getRepresentVal()).intValue();
            }
            // if not then use all possible 50s
            else
                res[0] = this.balanceNotes.get(Note.FIFTY_DOLLARS);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 50s 
            if(res[0] > 0){
                amount = amount.subtract(Note.FIFTY_DOLLARS.getRepresentVal().multiply(BigDecimal.valueOf(res[0])));
                this.balanceNotes.put(Note.FIFTY_DOLLARS, this.balanceNotes.get(Note.FIFTY_DOLLARS) - res[0]);
            }
        }

        // FOR THE 20 DOLLARS EQUIVALENT

        // Check if we have 10s in our balance inventory in the first place
        if(this.balanceNotes.get(Note.TWENTY_DOLLARS) > 0){
            // If the num of 20s in our inventory is higher than 20s in the entered sum we can use as much as possible
            if(this.balanceNotes.get(Note.TWENTY_DOLLARS) >= amount.divide(Note.TWENTY_DOLLARS.getRepresentVal()).intValue()) {
                res[1] = amount.divide(Note.TWENTY_DOLLARS.getRepresentVal()).intValue();
            }
            // if not then use all possible 20s
            else
                res[1] = this.balanceNotes.get(Note.TWENTY_DOLLARS);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 20s 
            if(res[1] > 0){
                amount = amount.subtract(Note.TWENTY_DOLLARS.getRepresentVal().multiply(BigDecimal.valueOf(res[1])));
                this.balanceNotes.put(Note.TWENTY_DOLLARS, this.balanceNotes.get(Note.TWENTY_DOLLARS) - res[1]);
            }
        }

        // FOR THE 1 DOLLAR EQUIVALENT
        
        // Check if we have 10s in our balance inventory in the first place
        if(this.balanceCoins.get(Coin.ONE_DOLLAR) > 0){
            // If the num of 1s in our inventory is higher than 1s in the entered sum we can use as much as possible
            if(this.balanceCoins.get(Coin.ONE_DOLLAR) >= amount.divide(Coin.ONE_DOLLAR.getRepresentVal()).intValue()) {
                res[2] = amount.divide(Coin.ONE_DOLLAR.getRepresentVal()).intValue();
            }
            // if not then use all possible 1s
            else
                res[2] = this.balanceCoins.get(Coin.ONE_DOLLAR);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 1s 
            if(res[2] > 0){
                amount = amount.subtract(Coin.ONE_DOLLAR.getRepresentVal().multiply(BigDecimal.valueOf(res[2])));
                this.balanceCoins.put(Coin.ONE_DOLLAR, this.balanceCoins.get(Coin.ONE_DOLLAR) - res[2]);
            }
        }
        
        // FOR THE 50 CENTS EQUIVALENT

        // Check if we have 50 cents in our balance inventory in the first place
        if(this.balanceCoins.get(Coin.FIFTY_CENTS) > 0){
            // If the num of 50 cents in our inventory is higher than 50 cents in the entered sum we can use as much as possible
            if(this.balanceCoins.get(Coin.FIFTY_CENTS) >= amount.divide(Coin.FIFTY_CENTS.getRepresentVal()).intValue()) {
                res[3] = amount.divide(Coin.FIFTY_CENTS.getRepresentVal()).intValue();
            }
            // if not then use all possible 50 cents
            else
                res[3] = this.balanceCoins.get(Coin.FIFTY_CENTS);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 50 cents 
            if(res[3] > 0){
                amount = amount.subtract(Coin.FIFTY_CENTS.getRepresentVal().multiply(BigDecimal.valueOf(res[3])));
                this.balanceCoins.put(Coin.FIFTY_CENTS, this.balanceCoins.get(Coin.FIFTY_CENTS) - res[3]);
            }
        }

        // FOR THE 20 cents EQUIVALENT

        // Check if we have 20 cents in our balance inventory in the first place
        if(this.balanceCoins.get(Coin.TWENTY_CENTS) > 0){
            // If the num of 20 cents in our inventory is higher than 20 cents in the entered sum we can use as much as possible
            if(this.balanceCoins.get(Coin.TWENTY_CENTS) >= amount.divide(Coin.TWENTY_CENTS.getRepresentVal()).intValue()) {
                res[4] = amount.divide(Coin.TWENTY_CENTS.getRepresentVal()).intValue();
            }
            // if not then use all possible 20 cents
            else
                res[4] = this.balanceCoins.get(Coin.TWENTY_CENTS);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 20 cents 
            if(res[4] > 0){
                amount = amount.subtract(Coin.TWENTY_CENTS.getRepresentVal().multiply(BigDecimal.valueOf(res[4])));
                this.balanceCoins.put(Coin.TWENTY_CENTS, this.balanceCoins.get(Coin.TWENTY_CENTS) - res[4]);
            }
        }

        // FOR THE 10 cents EQUIVALENT

        // Check if we have 10 cents in our balance inventory in the first place
        if(this.balanceCoins.get(Coin.TEN_CENTS) > 0){
            // If the num of 10 cents in our inventory is higher than 10 cents in the entered sum we can use as much as possible
            if(this.balanceCoins.get(Coin.TEN_CENTS) >= amount.divide(Coin.TEN_CENTS.getRepresentVal()).intValue()) {
                res[5] = amount.divide(Coin.TEN_CENTS.getRepresentVal()).intValue();
            }
            // if not then use all possible 10 cents
            else
                res[5] = this.balanceCoins.get(Coin.TEN_CENTS);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 10 cents 
            if(res[5] > 0){
                amount = amount.subtract(Coin.TEN_CENTS.getRepresentVal().multiply(BigDecimal.valueOf(res[5])));
                this.balanceCoins.put(Coin.TEN_CENTS, this.balanceCoins.get(Coin.TEN_CENTS) - res[5]);
            }
        }

        // set coin count for change
        c.setNumOfCoins(res);

        // update to new machine balance
        this.setBalance(calculateBalance());

    }

}
