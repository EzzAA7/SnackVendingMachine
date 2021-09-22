package tests;

import components.Coin;
import components.Note;
import core.Driver;
import exceptions.*;
import implementation.Change;
import implementation.Product;
import implementation.SnackMachine;
import implementation.VMRunner;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.ChangeUtil;
import utils.MoneyUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class VMRunnerTest {

    private static SnackMachine snackMachine;
    private static VMRunner vmRunner;
    private static PrintStream standardOut = System.out;
    private static ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeClass
    public static void setUp() {
        snackMachine = new SnackMachine();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterClass
    public static void tearDown() {
        snackMachine = null;
    }

    @Test
    public void ShouldInsetCorrectNumberOfCoins() {
        vmRunner = new VMRunner(snackMachine);
        BigDecimal money = BigDecimal.valueOf(0.50);
        Coin desiredCoin = Coin.coinValue(money);
        vmRunner.enterCoin(desiredCoin);
        assertEquals(1, vmRunner.getEnteredCoins().size());

    }

    @Test
    public void ShouldInsetCorrectNumberInMapCoin() {
        vmRunner = new VMRunner(snackMachine);
        BigDecimal money = BigDecimal.valueOf(0.50);
        Coin desiredCoin = Coin.coinValue(money);
        vmRunner.enterCoin(desiredCoin);
        vmRunner.enterCoin(desiredCoin);

        int val = vmRunner.getEnteredCoins().get(desiredCoin);
        assertEquals(2, val);

    }

    @Test
    public void ShouldHaveCorrectSumOfCoins(){
        vmRunner = new VMRunner(snackMachine);

        BigDecimal money = BigDecimal.valueOf(0.50);
        Coin desiredCoin = Coin.coinValue(money);
        vmRunner.enterCoin(desiredCoin);
        vmRunner.enterCoin(desiredCoin);
        vmRunner.enterCoin(desiredCoin);

        BigDecimal val = new MoneyUtil().calculateEnteredSum(vmRunner);
        assertEquals(BigDecimal.valueOf(1.50), val);
    }

    @Test
    public void ShouldInsetCorrectNumberOfNotes() {
        vmRunner = new VMRunner(snackMachine);

        BigDecimal money = BigDecimal.valueOf(20.00);
        Note desiredNote = Note.noteValue(money);
        vmRunner.enterNote(desiredNote);

        assertEquals(1, vmRunner.getEnteredNotes().size());

    }

    @Test
    public void ShouldInsertCorrectNumberInMapNote() {
        vmRunner = new VMRunner(snackMachine);

        BigDecimal money = BigDecimal.valueOf(20.00);
        Note desiredNote = Note.noteValue(money);
        vmRunner.enterNote(desiredNote);
        vmRunner.enterNote(desiredNote);

        int val = vmRunner.getEnteredNotes().get(desiredNote);
        assertEquals(2, val);

    }

    @Test
    public void ShouldHaveCorrectSumOfNotes(){
        vmRunner = new VMRunner(snackMachine);

        BigDecimal money = BigDecimal.valueOf(20.00);
        Note desiredNote = Note.noteValue(money);
        vmRunner.enterNote(desiredNote);
        vmRunner.enterNote(desiredNote);

        BigDecimal val = new MoneyUtil().calculateEnteredSum(vmRunner);
        assertEquals(BigDecimal.valueOf(40.00), val);
    }

    @Test
    public void ShouldHaveRightBalance(){
        vmRunner = new VMRunner(snackMachine);

        Coin desiredCoin = Coin.coinValue(BigDecimal.valueOf(0.50));
        vmRunner.enterCoin(desiredCoin);
        vmRunner.enterCoin(desiredCoin);
        vmRunner.enterCoin(desiredCoin);

        Note desiredNote = Note.noteValue(BigDecimal.valueOf(20.00));
        vmRunner.enterNote(desiredNote);

        BigDecimal val = vmRunner.getEnteredSum();
        assertEquals(BigDecimal.valueOf(21.50), val);

    }

    @Test
    public void ShouldSelectCorrectProduct() throws NoSuchProductException {
        vmRunner = new VMRunner(snackMachine);
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        Product chosenProduct = Product.checkProductValidity(snackMachine, "A2", vmRunner);
        vmRunner.setSelectedProd(chosenProduct);
        String res1 = ("The selected product is the following:\n");
        res1 = res1.concat("     " + chosenProduct.getProductId() + "  " + chosenProduct.getName() + " | " + chosenProduct.getPrice() + " $   ").trim();
        assertEquals(res1, outputStreamCaptor.toString().trim());
    }

    @Test
    public void ShouldDisposeCorrectProduct() throws NoSuchProductException {
        vmRunner = new VMRunner(snackMachine);

        Product chosenProduct = Product.checkProductValidity(snackMachine, "A2", vmRunner);
        vmRunner.setSelectedProd(chosenProduct);
        vmRunner.disposeSelectedProduct();
        String res1 = ("The selected product is the following:\n");
        res1 = res1.concat("     " + chosenProduct.getProductId() + "  " + chosenProduct.getName() + " | " + chosenProduct.getPrice() + " $   ")
                .concat("\r\n");
        String res = res1.concat(" DISPENSING: " + chosenProduct.getName()).concat("\r\n")
                .concat("Its quantity is now at: " + chosenProduct.getQuantity() + "   ").trim();
        assertEquals(res, outputStreamCaptor.toString().trim());
    }

    @Test
    public void ShouldNotHaveSuchCoin(){
        vmRunner = new VMRunner(snackMachine);

        Coin c = Coin.coinValue(BigDecimal.valueOf(150.00));
        assertEquals(BigDecimal.valueOf(0.00), c.getRepresentVal());
    }

    @Test
    public void ShouldNotHaveSuchNote(){
        vmRunner = new VMRunner(snackMachine);

        Note note = Note.noteValue(BigDecimal.valueOf(0.66));
        assertEquals(BigDecimal.valueOf(0.00), note.getRepresentVal());
    }

    /**
     * setup valid product (A2  KITKAT | 4.3 $)
     * and note (50)
     * the change should be 45.7
     *  the returned coins are as follows:
     * 	0 x 50 USD
     * 	2 x 20 USD
     * 	5 x 1 USED
     * 	1 x Fifty Cents
     * 	1 x Twenty Cents
     * 	0 x Ten Cents
     */
    @Test
    public void ShouldReturnValidChange() throws NotEnoughChange, NoSuchProductException {

        vmRunner = new VMRunner(snackMachine);

        Product chosenProduct = Product.checkProductValidity(snackMachine, "A2", vmRunner);

        BigDecimal money = BigDecimal.valueOf(50.00);
        Note desiredNote = Note.noteValue(money);
        vmRunner.enterNote(desiredNote);
        vmRunner.setYourChange(new Change(vmRunner.getEnteredSum().subtract(chosenProduct.getPrice())));
        new ChangeUtil().calcChange(vmRunner.getYourChange(), snackMachine);
        assertEquals(BigDecimal.valueOf(45.7), vmRunner.getYourChange().getAmount());
        assertArrayEquals(new int[]{0,2,5,1,1,0}, vmRunner.getYourChange().getNumOfCoins());

    }

}
