package com.company.com.implementation;

import errors.NotEnoughChange;

import java.math.BigDecimal;

public interface SnackMachineInterface {
    BigDecimal calculateBalance();

    void setupMachineMoney();

    void fillMachineProducts();

    void displayProducts();

    void calcChange(Change c) throws NotEnoughChange;
}
