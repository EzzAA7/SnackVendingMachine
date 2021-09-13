package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class VMRunnder {
    private VendingMachine vm;
    private Product selectedProd;
    private HashMap<Coin, Integer> enteredCoins;
    private int enteredSum;

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

    public void setEnteredCoins(HashMap<Coin, Integer> enteredCoins) {

        this.enteredCoins = enteredCoins;
    }

    public int getEnteredSum() {

        return enteredSum;
    }

    public void setEnteredSum(int enteredSum) {

        this.enteredSum = enteredSum;
    }

    public void displayBalance(){
        System.out.println(" The current entered amount is: " + this.getEnteredSum());
        System.out.println(" ------------------------------------------ ");

    }
}
