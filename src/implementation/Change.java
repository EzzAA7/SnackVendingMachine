package implementation;

import java.math.BigDecimal;

public class Change {
    private BigDecimal amount;
    private int[] numOfCoins;

    public Change(BigDecimal amount) {
        this.amount = amount;
        this.numOfCoins = new int[6];
    }

    public BigDecimal getAmount() {

        return amount;
    }

    public void setAmount(BigDecimal amount) {

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
                "\n the returned coins are as follows: \n\t" + numOfCoins[0] + " x 50 USD\n\t" +
                numOfCoins[1] + " x 20 USD\n\t" +
                numOfCoins[2] + " x 1 USED \n\t" +
                numOfCoins[3] + " x Fifty Cents \n\t" +
                numOfCoins[4] + " x Twenty Cents \n\t" +
                numOfCoins[5] + " x Ten Cents\n";
    }
}
