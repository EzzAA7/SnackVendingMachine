package implementation;

import interfaces.VMRImplementation;
import components.Card;
import components.Coin;
import components.Note;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.HashMap;

public class VMRunner implements VMRImplementation {
    private SnackMachine vm;
    private Product selectedProd;
    private HashMap<Coin, Integer> enteredCoins;
    private HashMap<Note, Integer> enteredNotes;
    private BigDecimal enteredSum;
    private Change yourChange;
    private Card yourCard;

    /**
     * initializes a vending machine runner with empty enteredCoins and enteredNotes
     * initializes the entered sum with 0
     */
    public VMRunner(SnackMachine vm) {
        this.vm = vm;
        this.enteredCoins = new HashMap<>();
        this.enteredNotes = new HashMap<>();
        this.enteredSum = BigDecimal.valueOf(0.00);
    }

    public SnackMachine getVm() {

        return vm;
    }

    public void setVm(SnackMachine vm) {

        this.vm = vm;
    }

    public Product getSelectedProd() {

        return selectedProd;
    }

    public void setSelectedProd(Product selectedProd) {
        this.selectedProd = selectedProd;
    }

    public HashMap<Coin, Integer> getEnteredCoins() {

        return enteredCoins;
    }

    public BigDecimal getEnteredSum() {

        return enteredSum;
    }

    public void setEnteredSum(BigDecimal enteredSum) {

        this.enteredSum = enteredSum;
    }

    public Change getYourChange() {

        return yourChange;
    }

    public void setYourChange(Change yourChange) {

        this.yourChange = yourChange;
    }

    public Card getYourCard() {
        return yourCard;
    }

    public void setYourCard(Card yourCard) {
        this.yourCard = yourCard;
    }

    /**
     * adds the user's inserted coin in the runner's coins and recalculates the balance
     * @param c the coin that was just inserted
     */
    @Override
    public void enterCoin(Coin c) {
        this.enteredCoins.put(c, this.enteredCoins.getOrDefault(c,0)+1);
        this.setEnteredSum(calculateEnteredSum());
        System.out.println("You have entered " + c + " ====> The total is now: " + enteredSum);
    }

    /**
     * adds the user's inserted note in the runner's note and recalculates the balance
     * @param note the note that was just inserted
     */
    @Override
    public void enterNote(Note note) {
        this.enteredNotes.put(note, this.enteredNotes.getOrDefault(note,0)+1);
        this.setEnteredSum(calculateEnteredSum());
        System.out.println("You have entered " + note + " ====> The total is now: " + enteredSum);
    }

    /**
     * displays the current balance at a given time
     */
    @Override
    public void displayBalance(){
        System.out.println(" The current entered amount is: " + this.getEnteredSum());
        System.out.println(" ------------------------------------------ ");

    }

    /**
     * Iterates the values in the enteredCoins map and calculates the the value of each
     * coin as its quantity multiplied by its representation
     * and each calculated value is added to the same
     * Also iterates similarly through enteredNotes map and calculates the the value of each
     * note as its quantity multiplied by its representation
     * and each calculated value is added to the sum
     * @return the decimal summation of entered money
     */
    @Override
    public BigDecimal calculateEnteredSum() {
        BigDecimal sum = BigDecimal.valueOf(0.00);
        for (EnumMap.Entry<Coin, Integer> entry : enteredCoins.entrySet()) {
            sum = sum.add(BigDecimal.valueOf(entry.getValue()).multiply(entry.getKey().getRepresentVal()));
        }
        for (EnumMap.Entry<Note, Integer> entry : enteredNotes.entrySet()) {
            sum = sum.add(BigDecimal.valueOf(entry.getValue()).multiply(entry.getKey().getRepresentVal()));
        }
        return sum;
    }

    /**
     * displays the disposing of the selected product once everything else is done
     */
    @Override
    public void disposeSelectedProduct(){
        // dispense product
        System.out.println(" DISPENSING: " + selectedProd.getName());
        System.out.println("Its quantity is now at: " + selectedProd.getQuantity() + "   ");

    }


}
