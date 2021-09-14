package implementation;

import interfaces.SnackMachineInterface;
import components.Coin;
import components.Note;
import exceptions.NotEnoughChange;

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
        this.balance = calculateBalance();
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

    public void setBalanceCoins(EnumMap<Coin, Integer> balanceCoins) {

        this.balanceCoins = balanceCoins;
    }

    public HashMap<String, Product> getProducts() {

        return products;
    }

    public void setProducts(HashMap<String, Product> products) {

        this.products = products;
    }

    /**
     * Iterates the values in the balanceCoins map and calculates the the value of each
     * coin as its quantity multiplied by its representation
     * and each calculated value is added to the sum
     * Also iterates similarly through balanceNotes map and calculates the the value of each
     * note as its quantity multiplied by its representation
     * and each calculated value is added to the sum
     * @return the decimal summation of balance
     */
    @Override
    public BigDecimal calculateBalance() {
        BigDecimal sum = BigDecimal.valueOf(0.00);
        for (EnumMap.Entry<Coin, Integer> entry : balanceCoins.entrySet()) {
            sum = sum.add(BigDecimal.valueOf(entry.getValue()).multiply(entry.getKey().getRepresentVal()));
        }

        for (EnumMap.Entry<Note, Integer> entry : balanceNotes.entrySet()) {
            sum = sum.add(BigDecimal.valueOf(entry.getValue()).multiply(entry.getKey().getRepresentVal()));
        }
        return sum;
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
        products.put("B1", new Product("B1", "COCA-COLA", BigDecimal.valueOf(2.90), 7));
        products.put("B2", new Product("B2", "COCA-COLA", BigDecimal.valueOf(2.90), 7));
        products.put("B3", new Product("B3", "COCA-COLA", BigDecimal.valueOf(2.90), 7));
        products.put("B4", new Product("B4", "COCA-COLA", BigDecimal.valueOf(2.90), 7));
        products.put("B5", new Product("B5", "COCA-COLA", BigDecimal.valueOf(2.90), 7));
        products.put("C1", new Product("C1", "COCA-COLA", BigDecimal.valueOf(2.90), 7));
        products.put("C2", new Product("C2", "COCA-COLA", BigDecimal.valueOf(2.90), 7));
        products.put("C3", new Product("C3", "COCA-COLA", BigDecimal.valueOf(2.90), 7));
        products.put("C4", new Product("C4", "COCA-COLA", BigDecimal.valueOf(2.90), 7));
        products.put("C5", new Product("C5", "COCA-COLA", BigDecimal.valueOf(2.90), 7));
        products.put("D1", new Product("D1", "COCA-COLA", BigDecimal.valueOf(2.90), 7));
        products.put("D2", new Product("D2", "COCA-COLA", BigDecimal.valueOf(2.90), 7));
        products.put("D3", new Product("D3", "COCA-COLA", BigDecimal.valueOf(2.90), 7));
        products.put("D4", new Product("D4", "COCA-COLA", BigDecimal.valueOf(2.90), 7));
        products.put("D5", new Product("D5", "COCA-COLA", BigDecimal.valueOf(2.90), 7));
        products.put("E1", new Product("E1", "COCA-COLA", BigDecimal.valueOf(2.90), 7));
        products.put("E2", new Product("E2", "COCA-COLA", BigDecimal.valueOf(2.90), 7));
        products.put("E3", new Product("E3", "COCA-COLA", BigDecimal.valueOf(2.90), 7));
        products.put("E4", new Product("E4", "COCA-COLA", BigDecimal.valueOf(2.90), 7));
        products.put("E5", new Product("E5", "SPRITE", BigDecimal.valueOf(2.90), 7));
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

    /**
     * Calculates how much of each note/coin is need to satisfy the change amount
     * decrements the needed notes/coins from their maps
     * updates the machine balance
     * @param c the Change object which has the calculatd change amount
     * @throws NotEnoughChange in case the machine's coins/notes are not enough to give change
     * even if within the full balance
     */
    @Override
    public void calcChange(Change c) throws NotEnoughChange {

        BigDecimal amount = c.getAmount();

        int[] res = new int[6];

        // FOR THE 50 DOLLARS EQUIVALENT

        // Check if we have 10s in our balance inventory in the first place
        if(this.balanceNotes.get(Note.FIFTY_DOLLARS) > 0){
            // If the num of 50s in our inventory is higher than 50s in the entered sum we can use as much as possible
            if(this.balanceNotes.get(Note.FIFTY_DOLLARS) >= amount.divide(Note.FIFTY_DOLLARS.getRepresentVal()).intValue()) {
                res[0] = amount.divide(Note.FIFTY_DOLLARS.getRepresentVal()).intValue();
            }
            // if not then use all possible 50s
            else
                res[0] = this.balanceNotes.get(Note.FIFTY_DOLLARS);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 50s 
            if(res[0] > 0){
                amount = amount.subtract(Note.FIFTY_DOLLARS.getRepresentVal().multiply(BigDecimal.valueOf(res[0])));
                this.balanceNotes.put(Note.FIFTY_DOLLARS, this.balanceNotes.get(Note.FIFTY_DOLLARS) - res[0]);
            }
        }

        // FOR THE 20 DOLLARS EQUIVALENT

        // Check if we have 10s in our balance inventory in the first place
        if(this.balanceNotes.get(Note.TWENTY_DOLLARS) > 0){
            // If the num of 20s in our inventory is higher than 20s in the entered sum we can use as much as possible
            if(this.balanceNotes.get(Note.TWENTY_DOLLARS) >= amount.divide(Note.TWENTY_DOLLARS.getRepresentVal()).intValue()) {
                res[1] = amount.divide(Note.TWENTY_DOLLARS.getRepresentVal()).intValue();
            }
            // if not then use all possible 20s
            else
                res[1] = this.balanceNotes.get(Note.TWENTY_DOLLARS);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 20s 
            if(res[1] > 0){
                amount = amount.subtract(Note.TWENTY_DOLLARS.getRepresentVal().multiply(BigDecimal.valueOf(res[1])));
                this.balanceNotes.put(Note.TWENTY_DOLLARS, this.balanceNotes.get(Note.TWENTY_DOLLARS) - res[1]);
            }
        }

        // FOR THE 1 DOLLAR EQUIVALENT
        
        // Check if we have 10s in our balance inventory in the first place
        if(this.balanceCoins.get(Coin.ONE_DOLLAR) > 0){
            // If the num of 1s in our inventory is higher than 1s in the entered sum we can use as much as possible
            if(this.balanceCoins.get(Coin.ONE_DOLLAR) >= amount.divide(Coin.ONE_DOLLAR.getRepresentVal()).intValue()) {
                res[2] = amount.divide(Coin.ONE_DOLLAR.getRepresentVal()).intValue();
            }
            // if not then use all possible 1s
            else
                res[2] = this.balanceCoins.get(Coin.ONE_DOLLAR);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 1s 
            if(res[2] > 0){
                amount = amount.subtract(Coin.ONE_DOLLAR.getRepresentVal().multiply(BigDecimal.valueOf(res[2])));
                this.balanceCoins.put(Coin.ONE_DOLLAR, this.balanceCoins.get(Coin.ONE_DOLLAR) - res[2]);
            }
        }
        
        // FOR THE 50 CENTS EQUIVALENT

        // Check if we have 50 cents in our balance inventory in the first place
        if(this.balanceCoins.get(Coin.FIFTY_CENTS) > 0){
            // If the num of 50 cents in our inventory is higher than 50 cents in the entered sum we can use as much as possible
            if(this.balanceCoins.get(Coin.FIFTY_CENTS) >= amount.divide(Coin.FIFTY_CENTS.getRepresentVal()).intValue()) {
                res[3] = amount.divide(Coin.FIFTY_CENTS.getRepresentVal()).intValue();
            }
            // if not then use all possible 50 cents
            else
                res[3] = this.balanceCoins.get(Coin.FIFTY_CENTS);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 50 cents 
            if(res[3] > 0){
                amount = amount.subtract(Coin.FIFTY_CENTS.getRepresentVal().multiply(BigDecimal.valueOf(res[3])));
                this.balanceCoins.put(Coin.FIFTY_CENTS, this.balanceCoins.get(Coin.FIFTY_CENTS) - res[3]);
            }
        }

        // FOR THE 20 cents EQUIVALENT

        // Check if we have 20 cents in our balance inventory in the first place
        if(this.balanceCoins.get(Coin.TWENTY_CENTS) > 0){
            // If the num of 20 cents in our inventory is higher than 20 cents in the entered sum we can use as much as possible
            if(this.balanceCoins.get(Coin.TWENTY_CENTS) >= amount.divide(Coin.TWENTY_CENTS.getRepresentVal()).intValue()) {
                res[4] = amount.divide(Coin.TWENTY_CENTS.getRepresentVal()).intValue();
            }
            // if not then use all possible 20 cents
            else
                res[4] = this.balanceCoins.get(Coin.TWENTY_CENTS);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 20 cents 
            if(res[4] > 0){
                amount = amount.subtract(Coin.TWENTY_CENTS.getRepresentVal().multiply(BigDecimal.valueOf(res[4])));
                this.balanceCoins.put(Coin.TWENTY_CENTS, this.balanceCoins.get(Coin.TWENTY_CENTS) - res[4]);
            }
        }

        // FOR THE 10 cents EQUIVALENT

        // Check if we have 10 cents in our balance inventory in the first place
        if(this.balanceCoins.get(Coin.TEN_CENTS) > 0){
            // If the num of 10 cents in our inventory is higher than 10 cents in the entered sum we can use as much as possible
            if(this.balanceCoins.get(Coin.TEN_CENTS) >= amount.divide(Coin.TEN_CENTS.getRepresentVal()).intValue()) {
                res[5] = amount.divide(Coin.TEN_CENTS.getRepresentVal()).intValue();
            }
            // if not then use all possible 10 cents
            else
                res[5] = this.balanceCoins.get(Coin.TEN_CENTS);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 10 cents 
            if(res[5] > 0){
                amount = amount.subtract(Coin.TEN_CENTS.getRepresentVal().multiply(BigDecimal.valueOf(res[5])));
                this.balanceCoins.put(Coin.TEN_CENTS, this.balanceCoins.get(Coin.TEN_CENTS) - res[5]);
            }
        }

        // the amount should be equal to 0 in case the numbers in res are enough to give change
        // otherwise throw an exception for lack of balance
        if(amount.compareTo(BigDecimal.valueOf(0.00) ) != 0){
            throw new NotEnoughChange("Not enough change is available, contact the admin");
        }

        // set coin count for change
        c.setNumOfCoins(res);

        // update to new machine balance
        this.setBalance(calculateBalance());

    }

}
