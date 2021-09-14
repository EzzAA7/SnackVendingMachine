package interfaces;

import exceptions.NotEnoughChange;
import implementation.Change;

import java.math.BigDecimal;

public interface SnackMachineInterface {
    BigDecimal calculateBalance();

    void setupMachineMoney();

    void fillMachineProducts();

    void displayProducts();

    void calcChange(Change c) throws NotEnoughChange;
}
