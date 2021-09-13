package com.company;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class VMRunnder {
    private VendingMachine vm;
    private Product selectedProd;
    private HashMap<Coin, Integer> enteredCoins;
    private int enteredSum;
    private Change yourChange;

    public VMRunnder(VendingMachine vm) {
        this.vm = vm;
        this.enteredCoins = new HashMap<>();
        this.enteredSum = 0;
    }

    public VendingMachine getVm() {

        return vm;
    }

    public void setVm(VendingMachine vm) {

        this.vm = vm;
    }

    public Product getSelectedProd() {
        return selectedProd;
    }

    public void setSelectedProd(Product selectedProd) {
        this.selectedProd = selectedProd;
    }

    public HashMap<Coin, Integer> getEnteredCoins() {

        return enteredCoins;
    }

    public void enterCoin(Coin c) {
        this.enteredCoins.put(c, this.enteredCoins.getOrDefault(c,0)+1);
        this.setEnteredSum(calculateEnteredSum());
        System.out.println("You have entered " + c + " ====> The total is now: " + enteredSum);
    }

    public int getEnteredSum() {

        return enteredSum;
    }

    public void setEnteredSum(int enteredSum) {

        this.enteredSum = enteredSum;
    }

    public Change getYourChange() {
        return yourChange;
    }

    public void setYourChange(Change yourChange) {
        this.yourChange = yourChange;
    }

    public void displayBalance(){
        System.out.println(" The current entered amount is: " + this.getEnteredSum());
        System.out.println(" ------------------------------------------ ");

    }

    private int calculateEnteredSum() {
        int sum = 0;
        for (EnumMap.Entry<Coin, Integer> entry : enteredCoins.entrySet()) {
            sum += entry.getValue() * entry.getKey().getRepresentVal();
        }
        return sum;
    }
}
