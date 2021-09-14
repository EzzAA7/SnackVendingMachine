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

    /**
     * Sets up the machine and starts the user's implementation
     * using said machine
     * @param args
     * @throws NoSuchMonetary
     */
    public static void main(String[] args) throws NoSuchMonetary, NotPossibleCard, NoSuchSlot,
            NotEnoughInStock, NotEnoughChange, NotSufficientFundsCard, NoSuchProductException {

        SnackMachine snackMachine = new SnackMachine();
        startMachine(snackMachine);

    }

    /**
     * first creates an implementation using VMRunner and displays the products
     * takes the product selection input from user keypad
     * checks if the input is valid and checks needed conditions
     * ask user to keep entering money to one of the slots until reaching balance
     * once there, handle change calculation printing it, decrementing the quantity
     * and finally dispensing the product
     * @param snackMachine the main machine
     */
    private static void startMachine(SnackMachine snackMachine) throws NoSuchProductException, NoSuchMonetary, NotEnoughInStock, NotEnoughChange, NotPossibleCard, NotSufficientFundsCard, NoSuchSlot {

        VMRunner vmr = new VMRunner(snackMachine);
            snackMachine.displayProducts();

            String choice = inputProduct(vmr);

            Product chosenProduct = snackMachine.getProducts().get(choice);

            chosenProduct = chosenProduct.checkProductValidity(snackMachine, choice, vmr, chosenProduct);
            Boolean choseMoney = false;

            // check if vending machine has enough money to return change
            if(snackMachine.getBalance().compareTo(vmr.getEnteredSum().subtract(chosenProduct.getPrice())) < 0){
                throw new NotEnoughChange("Vending machine doesn't have enough change, contact admin");
            }

            // check if the chosen product is in stock
            else if( chosenProduct.getQuantity() < 1){
                throw new NotEnoughInStock("Not in stock, contact admin");
            }

            // meets needed conditions so can proceed
            else{

                // check if in balance (if entered is not less than the price)
                while(vmr.getEnteredSum().compareTo(chosenProduct.getPrice()) < 0){
                    // setup user choise for which money slot
                    setupSlotDecision(choseMoney);

                    int slot = getSlot();
                    BigDecimal money;

                    // check which slot was chosen
                    if(slot == 1){
                        choseMoney = handleCoinSlot(vmr);
                    }

                    // if its the cardSlot check if no previous coin/note slots were used
                    else if(slot == 2 && choseMoney == false){
                        handleCardSlot(vmr, chosenProduct);
                        break;
                    }

                    // in case noteSlot is chosen
                    else if(slot == 3){
                        choseMoney = handleNoteSlot(vmr);
                    }
                    // any other entered value is invalid
                    else{
                        throw new NoSuchSlot("Choose a valid slot, you have 3 to choose from");
                    }

                    // after each insertion print the balacne for the user
                    vmr.displayBalance();
                }

                /**
                 * setup handling the change, printing it, decrementing the quantity
                 * and finally dispensing the product
                 */
                setupResult(snackMachine, vmr, chosenProduct);

            }



    }

    /**
     * simply setup the user input for moneySlot
     * @param choseMoney
     */
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

    /**
     * Finds which note was entered the user using enum Note's noteValue
     * if it doesn't exist then it throws an exception
     * @param vmr this current user's run at the machine
     * @return choseMoney which is a Boolean to explain that a non card method was used
     * @throws NoSuchMonetary in case no such note exists
     */
    private static Boolean handleNoteSlot(VMRunner vmr) throws NoSuchMonetary {
        Boolean choseMoney;
        BigDecimal money;
        choseMoney = true;

        // get user input
        money = getMoney();
        Note desiredNote;

        if((desiredNote = Note.noteValue(money)) != Note.EMPTY){
            vmr.enterNote(desiredNote);
        }
        // in case no such input is a note
        else
            throw new NoSuchMonetary("No such note, please enter a value among (20, 50");
        return choseMoney;
    }

    /**
     * Creates card from number and type and sets it to this run
     * checks if card is feasible to user in the first place
     * @param vmr this current user's run at the machine
     * @param chosenProduct this chosen product by the user at the start of the run
     * @throws NotPossibleCard in case the card's number is invalid
     * @throws NotSufficientFundsCard in case the card doesn't have enough funds to make the payment
     */
    private static void handleCardSlot(VMRunner vmr, Product chosenProduct) throws NotPossibleCard, NotSufficientFundsCard {

        // get the user input for the card number
        String number = getCard();
        // generate card type using the card number
        String type = getCreditCardType(number);

        Card card = new Card(number, type);
        // set card for this run
        vmr.setYourCard(card);

        // check if card has enough funds by subtracting the product price from its balance
        if(card.getCardBalance().compareTo(chosenProduct.getPrice()) < 0){
            throw new NotSufficientFundsCard("Your card doesn't have enough funds.");
        }
        return;
    }

    /**
     * Finds which coin was entered the user using enum Coin's coinValue
     * if it doesn't exist then it throws an exception
     * @param vmr this current user's run at the machine
     * @return choseMoney which is a Boolean to explain that a non card method was used
     * @throws NoSuchMonetary in case not such coin
     */
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

    /**
     * Displays a message to select product
     * returns the user's product selection input
     * @return the value the user inputted
     */
    private static String inputProduct(VMRunner vmr) {
        System.out.println("                                              ");
        System.out.println(" --- Please select a product (type its selection number) --- ");

        String choice = getChoice();

        return choice;
    }

    /**
     * handles the user's input
     * @return the value the user inputted
     */
    private static String getChoice() {
        String choice;
        Scanner s = new Scanner(System.in);
        choice = s.nextLine();
        return choice;
    }

    /**
     * handles the user's money input
     * @return the decimal value the user inputted
     */
    private static BigDecimal getMoney() {
        System.out.println(" ");
        System.out.println("Please enter a monetary value");
        BigDecimal choice;
        Scanner s = new Scanner(System.in);
        choice = s.nextBigDecimal();
        return choice;
    }

    /**
     * handles the user's slot input
     * @return the intvalue the user inputted
     */
    private static int getSlot() {
        int choice;
        Scanner s = new Scanner(System.in);
        choice = s.nextInt();
        return choice;
    }

    /**
     * handles the user's card number input
     * @return the value the user inputted
     * @throws NotPossibleCard in case the card number is not possible
     */
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

    /**
     * Generates a card type using its number by matching it with existing Regexes
     * @param CreditCardNumber the card number inputted by the user
     * @return the type of card this is
     */
    private static String getCreditCardType(String CreditCardNumber)
    {
        String regVisa = "^4[0-9]{12}(?:[0-9]{3})?$";
        String regMaster = "^5[1-5][0-9]{14}$";

        // matches the number to a regex and returns it if possible
        if (CreditCardNumber.matches(regVisa))
            return "VISA";
        else if (CreditCardNumber.matches(regMaster))
            return "MASTER";
        else
            return "UNKNOWNTYPE";
    }
}
