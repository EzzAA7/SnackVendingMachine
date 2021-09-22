package implementation;

import interfaces.SnackMachineInterface;
import components.Coin;
import components.Note;
import exceptions.NotEnoughChange;
import utils.MoneyUtil;

import java.math.BigDecimal;
import java.util.*;

public class SnackMachine implements SnackMachineInterface {

    private EnumMap<Coin, Integer> balanceCoins;
    private EnumMap<Note, Integer> balanceNotes;
    private HashMap<String, Product> products;
    private BigDecimal balance;

    /**
     * initializes a vending machine with empty balanceCoins, balanceNotes and products
     * fills the machine with money coins and notes
     * fills the machine with products
     * calculates the starting balance following the filling
     */
    public SnackMachine() {
        this.balanceCoins = new EnumMap<>(Coin.class);
        this.balanceNotes = new EnumMap<>(Note.class);
        this.products = new HashMap<>();
        setupMachineMoney();
        fillMachineProducts();
        this.balance = new MoneyUtil().calculateBalance(this);
    }

    public EnumMap<Coin, Integer> getBalanceCoins() {

        return balanceCoins;
    }

    public BigDecimal getBalance() {

        return balance;
    }

    public void setBalance(BigDecimal balance) {

        this.balance = balance;
    }

    public EnumMap<Note, Integer> getBalanceNotes() {
        return balanceNotes;
    }

    public void setBalanceNotes(EnumMap<Note, Integer> balanceNotes) {
        this.balanceNotes = balanceNotes;
    }

    public void setBalanceCoins(EnumMap<Coin, Integer> balanceCoins) {

        this.balanceCoins = balanceCoins;
    }

    public HashMap<String, Product> getProducts() {

        return products;
    }

    public void setProducts(HashMap<String, Product> products) {

        this.products = products;
    }

    @Override
    public void setupMachineMoney() {
        // fill with coins
        balanceCoins.put(Coin.ONE_DOLLAR, 6);
        balanceCoins.put(Coin.FIFTY_CENTS, 4);
        balanceCoins.put(Coin.TWENTY_CENTS, 5);
        balanceCoins.put(Coin.TEN_CENTS, 10);

        // fill with notes
        balanceNotes.put(Note.FIFTY_DOLLARS, 2);
        balanceNotes.put(Note.TWENTY_DOLLARS, 5);

    }

    /**
     * fills machine's products by populating the products map
     */
    @Override
    public void fillMachineProducts() {
        products.put("A1", new Product("A1", "TWIX", BigDecimal.valueOf(4.50), 3));
        products.put("A2", new Product("A2", "KITKAT", BigDecimal.valueOf(4.30), 5));
        products.put("A3", new Product("A3", "XL", BigDecimal.valueOf(5.50), 10));
        products.put("A4", new Product("A4", "WATER", BigDecimal.valueOf(1.70), 2));
        products.put("A5", new Product("A5", "COCA-COLA", BigDecimal.valueOf(2.90), 7));
        products.put("B1", new Product("B1", "FANTA", BigDecimal.valueOf(2.10), 0 + (int)(Math.random() * ((10 - 0) + 1))));
        products.put("B2", new Product("B2", "BAVARIA", BigDecimal.valueOf(2.20), 0 + (int)(Math.random() * ((10 - 0) + 1))));
        products.put("B3", new Product("B3", "MIRANDA", BigDecimal.valueOf(2.00), 0 + (int)(Math.random() * ((10 - 0) + 1))));
        products.put("B4", new Product("B4", "RED-PEPPER", BigDecimal.valueOf(3.90), 0 + (int)(Math.random() * ((10 - 0) + 1))));
        products.put("B5", new Product("B5", "COKE", BigDecimal.valueOf(1.90), 0 + (int)(Math.random() * ((10 - 0) + 1))));
        products.put("C1", new Product("C1", "COKE-DI", BigDecimal.valueOf(0.90), 0 + (int)(Math.random() * ((10 - 0) + 1))));
        products.put("C2", new Product("C2", "TIMEOUT", BigDecimal.valueOf(4.90), 0 + (int)(Math.random() * ((10 - 0) + 1))));
        products.put("C3", new Product("C3", "AQUANA", BigDecimal.valueOf(5.70), 0 + (int)(Math.random() * ((10 - 0) + 1))));
        products.put("C4", new Product("C4", "JERICHO", BigDecimal.valueOf(4.10), 0 + (int)(Math.random() * ((10 - 0) + 1))));
        products.put("C5", new Product("C5", "EIN-GEDI", BigDecimal.valueOf(1.50), 0 + (int)(Math.random() * ((10 - 0) + 1))));
        products.put("D1", new Product("D1", "MARS", BigDecimal.valueOf(2.90), 0 + (int)(Math.random() * ((10 - 0) + 1))));
        products.put("D2", new Product("D2", "SNICKERS", BigDecimal.valueOf(2.90), 0 + (int)(Math.random() * ((10 - 0) + 1))));
        products.put("D3", new Product("D3", "BOUNTY", BigDecimal.valueOf(2.90), 0 + (int)(Math.random() * ((10 - 0) + 1))));
        products.put("D4", new Product("D4", "MALTESERS", BigDecimal.valueOf(2.90), 0 + (int)(Math.random() * ((10 - 0) + 1))));
        products.put("D5", new Product("D5", "CADBURY", BigDecimal.valueOf(2.90), 0 + (int)(Math.random() * ((10 - 0) + 1))));
        products.put("E1", new Product("E1", "KINDER", BigDecimal.valueOf(2.90), 0 + (int)(Math.random() * ((10 - 0) + 1))));
        products.put("E2", new Product("E2", "COCA-COLA", BigDecimal.valueOf(2.90), 0 + (int)(Math.random() * ((10 - 0) + 1))));
        products.put("E3", new Product("E3", "COCA-COLA", BigDecimal.valueOf(2.90), 0 + (int)(Math.random() * ((10 - 0) + 1))));
        products.put("E4", new Product("E4", "COCA-COLA", BigDecimal.valueOf(2.90), 0 + (int)(Math.random() * ((10 - 0) + 1))));
        products.put("E5", new Product("E5", "SPRITE", BigDecimal.valueOf(2.90), 0 + (int)(Math.random() * ((10 - 0) + 1))));
    }

    /**
     * displays the products in a 5*5 matrix
     */
    @Override
    public void displayProducts() {
        System.out.println(" \t\t\t\t\t\t*********************************************");
        System.out.println(" \t\t\t\t\t\t    WELCOME TO THE FREIGHTOS VENDING MACHINE           ");
        System.out.println("\t\t\t\t\t\t *********************************************");
        System.out.printf("\t\t\t\t\t %-25s", "________________________________ Our available products ________________________________");
        System.out.println("                                              ");

        char row = 'A';
        int i = 1;
        System.out.printf(
                "\t\t\t\t || %-15s | %-15s | %-15s | %-15s | %-15s ||", i, (i+1), (i+2) , (i+3), (i+4));
        System.out.println(" ");

        for (; row <= 'E' ; row+=1) {
            System.out.println("\t\t\t\t----------------------------------------------------------------------------------------------");

            System.out.printf("\t\t (" + row + ")");

            System.out.printf(
                    "\t || %-15s | %-15s | %-15s | %-15s | %-15s ||", products.get(""+row+i).getName(),
                    products.get(""+row+(i+1)).getName(), products.get(""+row+(i+2)).getName() , products.get(""+row+(i+3)).getName(),
                    products.get(""+row+(i+4)).getName() );

            System.out.println(" ");
            System.out.printf(
                    "\t\t\t\t || $%-14s | $%-14s | $%-14s | $%-14s | $%-14s ||", products.get(""+row+(i)).getPrice(),
                    products.get(""+row+(i+1)).getPrice(), products.get(""+row+(i+2)).getPrice() , products.get(""+row+(i+3)).getPrice(),
                    products.get(""+row+(i+4)).getPrice() );

            System.out.println(" ");
            System.out.printf(
                    "\t\t\t\t || Quantity: %-5s | Quantity: %-5s | Quantity: %-5s | Quantity: %-5s | Quantity: %-5s ||",
                    products.get(""+row+(i)).getQuantity(),
                    products.get(""+row+(i+1)).getQuantity(), products.get(""+row+(i+2)).getQuantity() , products.get(""+row+(i+3)).getQuantity(),
                    products.get(""+row+(i+4)).getQuantity() );

            System.out.println(" ");
            System.out.println("\t\t\t\t==============================================================================================");
            System.out.println(" ");

        }
    }

}
