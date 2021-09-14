package com.company.com.implementation;

import com.company.com.money.Coin;
import com.company.com.money.Note;

import java.math.BigDecimal;

public interface VMRImplementation {
    void enterCoin(Coin c);

    void enterNote(Note note);

    void displayBalance();

    BigDecimal calculateEnteredSum();

    void disposeSelectedProduct();
}
