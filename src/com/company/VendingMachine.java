package com.company;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine {

    private HashMap<Coin, Integer> balanceCoins;
    private int balance;

    public VendingMachine() {
        this.balanceCoins = new HashMap<>();
        fillMachine(balanceCoins);
        this.balance = calculateBalance(balanceCoins);
    }

    public HashMap<Coin, Integer> getBalanceCoins() {
        return balanceCoins;
    }

    public void setBalanceCoins(HashMap<Coin, Integer> balanceCoins) {
        this.balanceCoins = balanceCoins;
    }

    private int calculateBalance(HashMap<Coin, Integer> balanceCoins) {
        int sum = 0;
        for (Map.Entry<Coin, Integer> entry : balanceCoins.entrySet()) {
            sum += entry.getValue() * entry.getKey().getrepresentVal();
        }
        return sum;
    }

    private void fillMachine(HashMap<Coin, Integer> balanceCoins) {
        balanceCoins.put(Coin.TEN_SHEKELS, 5);
        balanceCoins.put(Coin.FIVE_SHEKELS, 6);
        balanceCoins.put(Coin.TWO_SHEKELS, 5);
        balanceCoins.put(Coin.ONE_SHEKELS, 10);
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void displayProducts() {
        System.out.println(" *********************************************");
        System.out.println("     WELCOME TO THE VENDING MACHINE           ");
        System.out.println(" *********************************************");
        System.out.println("            Products available:               ");
        System.out.println("                                              ");
        for(Product product: Product.values()){
            if(!Product.EMPTY.equals(product)) {
                System.out.println("    - " + product.getProductId() + " -  " + product.name() + " | " + product.getPrice() + " Shekels   " + " (Quantity: " + product.getQuantity() + ")");
            }
        }
        System.out.println("                                              ");
        System.out.println(" --- Please select a product (type its id) --- ");
    }
}
