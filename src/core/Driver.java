package core;

import implementation.Change;
import implementation.Product;
import implementation.SnackMachine;
import implementation.VMRunner;
import components.Card;
import components.Coin;
import components.Note;
import exceptions.*;

import java.math.BigDecimal;
import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws NoSuchMonetary {

        SnackMachine snackMachine = new SnackMachine();

        startMachine(snackMachine);

    }

    private static void startMachine(SnackMachine snackMachine) {
        try{
            VMRunner vmr = new VMRunner(snackMachine);
            snackMachine.displayProducts();

            String choice = inputProductAndBalance(vmr);

            Product chosenProduct = checkProductValidity(snackMachine, choice, vmr);
            Boolean choseMoney = false;

            // check if vending machine has enough money to return change
            if(snackMachine.getBalance().compareTo(vmr.getEnteredSum().subtract(chosenProduct.getPrice())) < 0){
                throw new NotEnoughChange("Vending machine doesn't have enough change, contact admin");
            }

            else if( chosenProduct.getQuantity() < 1){
                throw new NotEnoughInStock("Not in stock, contact admin");
            }

            // meets needed conditions => can proceed
            else{

                // check if in balance (if entered is not less than the price)
                while(vmr.getEnteredSum().compareTo(chosenProduct.getPrice()) < 0){
                    setupSlotDecision(choseMoney);

                    int slot = getSlot();
                    BigDecimal money;
                    if(slot == 1){
                        choseMoney = handleCoinSlot(vmr);
                    }

                    else if(slot == 2 && choseMoney == false){
                        handleCardSlot(vmr, chosenProduct);
                        break;
                    }

                    else if(slot == 3){
                        choseMoney = handleNoteSlot(vmr);
                    }
                    else{
                        throw new NoSuchSlot("Choose a valid slot, you have 3 to choose from");
                    }

                    vmr.displayBalance();
                }

                setupResult(snackMachine, vmr, chosenProduct);

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void setupSlotDecision(Boolean choseMoney) {
        System.out.println(" ");
        System.out.println("Not enough in balance, start entering");
        System.out.println("Choose between one of the available money slots");
        System.out.println("\t\t (1) o\tCoinSlot: There are four denominations: • 10c • 20c • 50c • $1 ");
        if(choseMoney == false)
            System.out.println("\t\t (2) o\tCardSlot : all cards accepted ");
        System.out.println("\t\t (3) o\tNotes Slot :20$ and 50$ only");
        System.out.println(" ");
    }

    private static Boolean handleNoteSlot(VMRunner vmr) throws NoSuchMonetary {
        Boolean choseMoney;
        BigDecimal money;
        choseMoney = true;
        money = getMoney();
        Note desiredNote;
        if((desiredNote = Note.noteValue(money)) != Note.EMPTY){
            vmr.enterNote(desiredNote);
        }
        else
            throw new NoSuchMonetary("No such note, please enter a value among (20, 50");
        return choseMoney;
    }

    private static void handleCardSlot(VMRunner vmr, Product chosenProduct) throws NotPossibleCard, NotSufficientFundsCard {
        String number = getCard();
        String type = GetCreditCardType(number);

        Card card = new Card(number, type);
        vmr.setYourCard(card);
        // check if card has enough
        if(card.getCardBalance().compareTo(chosenProduct.getPrice()) < 0){
            throw new NotSufficientFundsCard("Your card doesn't have enough funds.");
        }
        return;
    }

    private static Boolean handleCoinSlot(VMRunner vmr) throws NoSuchMonetary {
        Boolean choseMoney;
        BigDecimal money;
        choseMoney = true;
        money = getMoney();
        Coin desiredCoin;

        if((desiredCoin = Coin.coinValue(money)) != Coin.EMPTY){
            vmr.enterCoin(desiredCoin);
        }
        else
            throw new NoSuchMonetary("No such coin, please enter a value among (0.10. 0.20, 0.50, 1) or use a different slot");
        return choseMoney;
    }

    private static void setupResult(SnackMachine snackMachine, VMRunner vmr, Product chosenProduct) throws NotEnoughChange {
        // setup selected product
        vmr.setSelectedProd(chosenProduct);
        System.out.println(" ");


        // calculate how change will return to user (in coins)
        // in case of using coins and notes there will be no known card and there will be possible change
        if(vmr.getYourCard() == null) {
            // initialize change amount
            vmr.setYourChange(new Change(vmr.getEnteredSum().subtract(chosenProduct.getPrice())));
            snackMachine.calcChange(vmr.getYourChange());
            System.out.println(vmr.getYourChange().toString());
        }
        // in case of using a card there will be no change so immediately make payment
        else{
            vmr.getYourCard().makePayment(chosenProduct.getPrice());
        }

        // decrement product quantity
        chosenProduct.setQuantity(chosenProduct.getQuantity() -1);

        // dispense product
        vmr.disposeSelectedProduct();
    }

    private static Product checkProductValidity(SnackMachine snackMachine, String choice, VMRunner v) throws NoSuchProductException {
        Product chosenProduct = snackMachine.getProducts().get(choice);

        // checking validity of such a product
        if(chosenProduct == null){
            throw new NoSuchProductException("No such product, try again");
        }
        else {
            System.out.println(" ");
            System.out.println("The selected product is the following:");
            System.out.println("     " + chosenProduct.getProductId() + "  " + chosenProduct.getName() + " | " + chosenProduct.getPrice() + " $   ");
        }
        return chosenProduct;
    }

    private static String inputProductAndBalance(VMRunner vmr) {
        System.out.println("                                              ");
        System.out.println(" --- Please select a product (type its selection number) --- ");

        String choice = getChoice();

        return choice;
    }

    private static String getChoice() {
        String choice;
        Scanner s = new Scanner(System.in);
        choice = s.nextLine();
        return choice;
    }

    private static BigDecimal getMoney() {
        System.out.println(" ");
        System.out.println("Please enter a monetary value");
        BigDecimal choice;
        Scanner s = new Scanner(System.in);
        choice = s.nextBigDecimal();
        return choice;
    }

    private static int getSlot() {
        int choice;
        Scanner s = new Scanner(System.in);
        choice = s.nextInt();
        return choice;
    }

    private static String getCard() throws NotPossibleCard {
        System.out.println("Enter a card number");
        String choice;
        Scanner s = new Scanner(System.in);
        choice = s.nextLine();
        if(choice.length() != 16){
            throw new NotPossibleCard("Please enter a valid 16 numbered-card");
        }
        return choice;
    }

    private static String GetCreditCardType(String CreditCardNumber)
    {
        String regVisa = "^4[0-9]{12}(?:[0-9]{3})?$";
        String regMaster = "^5[1-5][0-9]{14}$";


        if (CreditCardNumber.matches(regVisa))
            return "VISA";
        else if (CreditCardNumber.matches(regMaster))
            return "MASTER";
        else
            return "UNKNOWNTYPE";
    }
}
