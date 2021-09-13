package com.company;

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
        return "Amount of Change: " + amount +
                "\n the returned coins are as follows: \n\t" + numOfCoins[0] + " Ten Sheksls\n\t" +
                numOfCoins[1] + " Five Sheksls\n\t" + numOfCoins[2] + " Two Sheksls\n\t" +
                numOfCoins[3] + " One Sheksls\n";
    }
}
