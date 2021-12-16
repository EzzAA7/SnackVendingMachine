package utils;

import components.Coin;
import components.Note;
import exceptions.NotEnoughChange;
import implementation.Change;
import implementation.SnackMachine;
import interfaces.SnackMachineInterface;

import java.math.BigDecimal;

public class ChangeUtil {


    /**
     * Calculates how much of each note/coin is need to satisfy the change amount
     * decrements the needed notes/coins from their maps
     * updates the machine balance
     * @param c the Change object which has the calculatd change amount
     * @throws NotEnoughChange in case the machine's coins/notes are not enough to give change
     * even if within the full balance
     */
    public void calcChange(Change c, SnackMachineInterface s) throws NotEnoughChange {

        BigDecimal amount = c.getAmount();

        int[] res = new int[6];

        //following logic is repetitive, can  create
        // Coin[] coins = Coin.values();
        // and use it instead in a for loop with a separate index representing res index

        // FOR THE 50 DOLLARS EQUIVALENT

        // Check if we have 50s in our balance inventory in the first place
        if (s.getBalanceNotes().get(Note.FIFTY_DOLLARS) > 0) {
            // If the num of 50s in our inventory is higher than 50s in the entered sum we can use as much as possible
            if (s.getBalanceNotes().get(Note.FIFTY_DOLLARS) >= amount.divide(Note.FIFTY_DOLLARS.getRepresentVal()).intValue()) {
                res[0] = amount.divide(Note.FIFTY_DOLLARS.getRepresentVal()).intValue();
            }
            // if not then use all possible 50s
            else
                res[0] = s.getBalanceNotes().get(Note.FIFTY_DOLLARS);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 50s
            if (res[0] > 0) {
                amount = amount.subtract(Note.FIFTY_DOLLARS.getRepresentVal().multiply(BigDecimal.valueOf(res[0])));
                s.getBalanceNotes().put(Note.FIFTY_DOLLARS, s.getBalanceNotes().get(Note.FIFTY_DOLLARS) - res[0]);
            }
        }

        // FOR THE 20 DOLLARS EQUIVALENT

        // Check if we have 20s in our balance inventory in the first place
        if (s.getBalanceNotes().get(Note.TWENTY_DOLLARS) > 0) {
            // If the num of 20s in our inventory is higher than 20s in the entered sum we can use as much as possible
            if (s.getBalanceNotes().get(Note.TWENTY_DOLLARS) >= amount.divide(Note.TWENTY_DOLLARS.getRepresentVal()).intValue()) {
                res[1] = amount.divide(Note.TWENTY_DOLLARS.getRepresentVal()).intValue();
            }
            // if not then use all possible 20s
            else
                res[1] = s.getBalanceNotes().get(Note.TWENTY_DOLLARS);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 20s
            if (res[1] > 0) {
                amount = amount.subtract(Note.TWENTY_DOLLARS.getRepresentVal().multiply(BigDecimal.valueOf(res[1])));
                s.getBalanceNotes().put(Note.TWENTY_DOLLARS, s.getBalanceNotes().get(Note.TWENTY_DOLLARS) - res[1]);
            }
        }

        // FOR THE 1 DOLLAR EQUIVALENT

        // Check if we have 1s in our balance inventory in the first place
        if (s.getBalanceCoins().get(Coin.ONE_DOLLAR) > 0) {
            // If the num of 1s in our inventory is higher than 1s in the entered sum we can use as much as possible
            if (s.getBalanceCoins().get(Coin.ONE_DOLLAR) >= amount.divide(Coin.ONE_DOLLAR.getRepresentVal()).intValue()) {
                res[2] = amount.divide(Coin.ONE_DOLLAR.getRepresentVal()).intValue();
            }
            // if not then use all possible 1s
            else
                res[2] = s.getBalanceCoins().get(Coin.ONE_DOLLAR);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 1s
            if (res[2] > 0) {
                amount = amount.subtract(Coin.ONE_DOLLAR.getRepresentVal().multiply(BigDecimal.valueOf(res[2])));
                s.getBalanceCoins().put(Coin.ONE_DOLLAR, s.getBalanceCoins().get(Coin.ONE_DOLLAR) - res[2]);
            }
        }

        // FOR THE 50 CENTS EQUIVALENT

        // Check if we have 50 cents in our balance inventory in the first place
        if (s.getBalanceCoins().get(Coin.FIFTY_CENTS) > 0) {
            // If the num of 50 cents in our inventory is higher than 50 cents in the entered sum we can use as much as possible
            if (s.getBalanceCoins().get(Coin.FIFTY_CENTS) >= amount.divide(Coin.FIFTY_CENTS.getRepresentVal()).intValue()) {
                res[3] = amount.divide(Coin.FIFTY_CENTS.getRepresentVal()).intValue();
            }
            // if not then use all possible 50 cents
            else
                res[3] = s.getBalanceCoins().get(Coin.FIFTY_CENTS);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 50 cents
            if (res[3] > 0) {
                amount = amount.subtract(Coin.FIFTY_CENTS.getRepresentVal().multiply(BigDecimal.valueOf(res[3])));
                s.getBalanceCoins().put(Coin.FIFTY_CENTS, s.getBalanceCoins().get(Coin.FIFTY_CENTS) - res[3]);
            }
        }

        // FOR THE 20 cents EQUIVALENT

        // Check if we have 20 cents in our balance inventory in the first place
        if (s.getBalanceCoins().get(Coin.TWENTY_CENTS) > 0) {
            // If the num of 20 cents in our inventory is higher than 20 cents in the entered sum we can use as much as possible
            if (s.getBalanceCoins().get(Coin.TWENTY_CENTS) >= amount.divide(Coin.TWENTY_CENTS.getRepresentVal()).intValue()) {
                res[4] = amount.divide(Coin.TWENTY_CENTS.getRepresentVal()).intValue();
            }
            // if not then use all possible 20 cents
            else
                res[4] = s.getBalanceCoins().get(Coin.TWENTY_CENTS);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 20 cents
            if (res[4] > 0) {
                amount = amount.subtract(Coin.TWENTY_CENTS.getRepresentVal().multiply(BigDecimal.valueOf(res[4])));
                s.getBalanceCoins().put(Coin.TWENTY_CENTS, s.getBalanceCoins().get(Coin.TWENTY_CENTS) - res[4]);
            }
        }

        // FOR THE 10 cents EQUIVALENT

        // Check if we have 10 cents in our balance inventory in the first place
        if (s.getBalanceCoins().get(Coin.TEN_CENTS) > 0) {
            // If the num of 10 cents in our inventory is higher than 10 cents in the entered sum we can use as much as possible
            if (s.getBalanceCoins().get(Coin.TEN_CENTS) >= amount.divide(Coin.TEN_CENTS.getRepresentVal()).intValue()) {
                res[5] = amount.divide(Coin.TEN_CENTS.getRepresentVal()).intValue();
            }
            // if not then use all possible 10 cents
            else
                res[5] = s.getBalanceCoins().get(Coin.TEN_CENTS);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 10 cents
            if (res[5] > 0) {
                amount = amount.subtract(Coin.TEN_CENTS.getRepresentVal().multiply(BigDecimal.valueOf(res[5])));
                s.getBalanceCoins().put(Coin.TEN_CENTS, s.getBalanceCoins().get(Coin.TEN_CENTS) - res[5]);
            }
        }

        // the amount should be equal to 0 in case the numbers in res are enough to give change
        // otherwise throw an exception for lack of balance
        if (amount.compareTo(BigDecimal.valueOf(0.00)) != 0) {
            throw new NotEnoughChange("Not enough change is available, contact the admin");
        }

        // set coin count for change
        c.setNumOfCoins(res);

        // update to new machine balance
        s.setBalance(new MoneyUtil().calculateBalance(s));
    }
}
