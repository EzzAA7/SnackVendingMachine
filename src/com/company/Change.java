package com.company;

import java.util.Arrays;

public class Change {
    private  int amount;
    private int[] numOfCoins;

    public Change(int amount) {
        this.amount = amount;
        this.numOfCoins = new int[4];
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int[] getNumOfCoins() {
        return numOfCoins;
    }

    public void setNumOfCoins(int[] numOfCoins) {
        this.numOfCoins = numOfCoins;
    }

    @Override
    public String toString() {
        return "Change{" +
                "amount=" + amount +
                ", numOfCoins=" + Arrays.toString(numOfCoins) +
                '}';
    }
}