package utils;

import components.Coin;
import components.Note;
import implementation.SnackMachine;
import implementation.VMRunner;
import interfaces.MonetaryValue;

import java.math.BigDecimal;
import java.util.EnumMap;

public class MoneyUtil {

    public BigDecimal calculateEnteredSum(VMRunner v) {
        BigDecimal sum = BigDecimal.valueOf(0.00);
        for (EnumMap.Entry<Coin, Integer> entry : v.getEnteredCoins().entrySet()) {
            sum = sum.add(BigDecimal.valueOf(entry.getValue()).multiply(entry.getKey().getRepresentVal()));
        }
        for (EnumMap.Entry<Note, Integer> entry : v.getEnteredNotes().entrySet()) {
            sum = sum.add(BigDecimal.valueOf(entry.getValue()).multiply(entry.getKey().getRepresentVal()));
        }
        return sum;
    }


    /**
     * Iterates the values in the balanceCoins map and calculates the the value of each
     * coin as its quantity multiplied by its representation
     * and each calculated value is added to the sum
     * Also iterates similarly through balanceNotes map and calculates the the value of each
     * note as its quantity multiplied by its representation
     * and each calculated value is added to the sum
     * @return the decimal summation of balance
     */
    public BigDecimal calculateBalance(SnackMachine snackMachine) {
        BigDecimal sum = BigDecimal.valueOf(0.00);
        for (EnumMap.Entry<Coin, Integer> entry : snackMachine.getBalanceCoins().entrySet()) {
            sum = sum.add(BigDecimal.valueOf(entry.getValue()).multiply(entry.getKey().getRepresentVal()));
        }

        for (EnumMap.Entry<Note, Integer> entry : snackMachine.getBalanceNotes().entrySet()) {
            sum = sum.add(BigDecimal.valueOf(entry.getValue()).multiply(entry.getKey().getRepresentVal()));
        }
        return sum;
    }
}
