package components;

import interfaces.MonetaryValue;

import java.math.BigDecimal;

public enum Note implements MonetaryValue {
    TWENTY_DOLLARS(BigDecimal.valueOf(20.00)), FIFTY_DOLLARS(BigDecimal.valueOf(50.00)), EMPTY(BigDecimal.valueOf(0.00));

    private BigDecimal representVal;

    Note (BigDecimal representVal){
        this.representVal = representVal;
    }

    @Override
    public BigDecimal getRepresentVal(){

        return this.representVal;
    }

    /**
     * checks if such a note value exists
     * @param val the user inputted note value
     * @return the note representing the inputted value
     */
    public static Note noteValue(BigDecimal val){
        for(Note note: Note.values()){
            if(val.compareTo(note.getRepresentVal()) == 0){
                return note;
            }
        }
        return EMPTY;
    }
}
