package com.company.com.implementation;

import com.company.com.money.Card;
import com.company.com.money.Coin;
import com.company.com.money.Note;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.HashMap;

public class VMRunner {
    private SnackMachine vm;
    private Product selectedProd;
    private HashMap<Coin, Integer> enteredCoins;
    private HashMap<Note, Integer> enteredNotes;
    private BigDecimal enteredSum;
    private Change yourChange;
    private Card yourCard;

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

    public void enterCoin(Coin c) {
        this.enteredCoins.put(c, this.enteredCoins.getOrDefault(c,0)+1);
        this.setEnteredSum(calculateEnteredSum());
        System.out.println("You have entered " + c + " ====> The total is now: " + enteredSum);
    }

    public void enterNote(Note note) {
        this.enteredNotes.put(note, this.enteredNotes.getOrDefault(note,0)+1);
        this.setEnteredSum(calculateEnteredSum());
        System.out.println("You have entered " + note + " ====> The total is now: " + enteredSum);
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

    public void displayBalance(){
        System.out.println(" The current entered amount is: " + this.getEnteredSum());
        System.out.println(" ------------------------------------------ ");

    }

    private BigDecimal calculateEnteredSum() {
        BigDecimal sum = BigDecimal.valueOf(0.00);
        for (EnumMap.Entry<Coin, Integer> entry : enteredCoins.entrySet()) {
            sum = sum.add(BigDecimal.valueOf(entry.getValue()).multiply(entry.getKey().getRepresentVal()));
        }
        for (EnumMap.Entry<Note, Integer> entry : enteredNotes.entrySet()) {
            sum = sum.add(BigDecimal.valueOf(entry.getValue()).multiply(entry.getKey().getRepresentVal()));
        }
        return sum;
    }

    public void disposeSelectedProduct(){
        // dispense product
        System.out.println(" DISPENSING: " + selectedProd.getName());
        System.out.println("Its quantity is now at: " + selectedProd.getQuantity() + "   ");

    }


}
