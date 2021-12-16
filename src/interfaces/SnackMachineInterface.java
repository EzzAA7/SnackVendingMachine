package interfaces;

import components.Coin;
import components.Note;
import implementation.Product;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.HashMap;

public interface SnackMachineInterface {

    void setupMachineMoney();

    void fillMachineProducts();

    void displayProducts();

    void setBalance(BigDecimal bigDecimal);

    HashMap<String, Product> getProducts();

    Comparable<BigDecimal> getBalance();

    EnumMap<Note, Integer> getBalanceNotes();

    EnumMap<Coin, Integer> getBalanceCoins();
}
