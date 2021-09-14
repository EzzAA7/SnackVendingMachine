package components;

import java.math.BigDecimal;
import java.util.EnumMap;

public class Card  {
    private String number;
    private String type;
    private EnumMap<Note, Integer> cardNotes;
    private BigDecimal cardBalance;

    public Card (String number, String type) {
        this.number = number;
        this.type = type;
        this.cardNotes = new EnumMap<>(Note.class);
        setupCardMoney();
        this.cardBalance = calculateBalance();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EnumMap<Note, Integer> getCardNotes() {
        return cardNotes;
    }

    public void setCardNotes(EnumMap<Note, Integer> cardNotes) {
        this.cardNotes = cardNotes;
    }

    public BigDecimal getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(BigDecimal cardBalance) {
        this.cardBalance = cardBalance;
    }

    public BigDecimal calculateBalance() {
        BigDecimal sum = BigDecimal.valueOf(0.00);
        for (EnumMap.Entry<Note, Integer> entry : cardNotes.entrySet()) {
            sum = sum.add(BigDecimal.valueOf(entry.getValue()).multiply(entry.getKey().getRepresentVal()));
        }
        return sum;
    }

    public void setupCardMoney() {
        cardNotes.put(Note.FIFTY_DOLLARS, 0 + (int)(Math.random() * ((10 - 0) + 1)));
        cardNotes.put(Note.TWENTY_DOLLARS, 0 + (int)(Math.random() * ((10 - 0) + 1)));
    }

    public void makePayment(BigDecimal amount){
        
        int[] res = new int[6];

        // FOR THE 50 DOLLARS EQUIVALENT

        // Check if we have 10s in our balance inventory in the first place
        if(this.cardNotes.get(Note.FIFTY_DOLLARS) > 0){
            // If the num of 50s in our inventory is higher than 50s in the entered sum we can use as much as possible
            if(this.cardNotes.get(Note.FIFTY_DOLLARS) >= amount.divide(Note.FIFTY_DOLLARS.getRepresentVal()).intValue()) {
                res[0] = amount.divide(Note.FIFTY_DOLLARS.getRepresentVal()).intValue();
            }
            // if not then use all possible 50s
            else
                res[0] = this.cardNotes.get(Note.FIFTY_DOLLARS);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 50s 
            if(res[0] > 0){
                amount = amount.subtract(Note.FIFTY_DOLLARS.getRepresentVal().multiply(BigDecimal.valueOf(res[0])));
                this.cardNotes.put(Note.FIFTY_DOLLARS, this.cardNotes.get(Note.FIFTY_DOLLARS) - res[0]);
            }
        }

        // FOR THE 20 DOLLARS EQUIVALENT

        // Check if we have 10s in our balance inventory in the first place
        if(this.cardNotes.get(Note.TWENTY_DOLLARS) > 0){
            // If the num of 20s in our inventory is higher than 20s in the entered sum we can use as much as possible
            if(this.cardNotes.get(Note.TWENTY_DOLLARS) >= amount.divide(Note.TWENTY_DOLLARS.getRepresentVal()).intValue()) {
                res[1] = amount.divide(Note.TWENTY_DOLLARS.getRepresentVal()).intValue();
            }
            // if not then use all possible 20s
            else
                res[1] = this.cardNotes.get(Note.TWENTY_DOLLARS);
            // the amount is updated to go forward with next coins

            // the balance inventory is decremented is case we have a new value for the num of 20s 
            if(res[1] > 0){
                amount = amount.subtract(Note.TWENTY_DOLLARS.getRepresentVal().multiply(BigDecimal.valueOf(res[1])));
                this.cardNotes.put(Note.TWENTY_DOLLARS, this.cardNotes.get(Note.TWENTY_DOLLARS) - res[1]);
            }
        }
        
        // update to new machine balance
        this.setCardBalance(calculateBalance());

        System.out.println("The payment has been done by " + this.number + " " + this.type);

    }
}
