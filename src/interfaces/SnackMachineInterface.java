package interfaces;

import exceptions.NotEnoughChange;
import implementation.Change;

import java.math.BigDecimal;

public interface SnackMachineInterface {

    void setupMachineMoney();

    void fillMachineProducts();

    void displayProducts();
}
