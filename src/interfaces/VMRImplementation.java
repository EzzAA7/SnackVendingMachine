package interfaces;

import components.Coin;
import components.Note;

import java.math.BigDecimal;

public interface VMRImplementation {
    void enterCoin(Coin c);

    void enterNote(Note note);

    void displayBalance();

    BigDecimal calculateEnteredSum();

    void disposeSelectedProduct();
}
