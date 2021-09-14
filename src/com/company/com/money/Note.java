package com.company.com.money;

import java.math.BigDecimal;

public enum Note {
    TWENTY_DOLLARS(BigDecimal.valueOf(20.00)), FIFTY_DOLLARS(BigDecimal.valueOf(50.00)), EMPTY(BigDecimal.valueOf(0.00));

    private BigDecimal representVal;

    Note (BigDecimal representVal){
        this.representVal = representVal;
    }

    public BigDecimal getRepresentVal(){
        return this.representVal;
    }

    public static Note noteValue(BigDecimal val){
        for(Note note: Note.values()){
            if(val.compareTo(note.getRepresentVal()) == 0){
                return note;
            }
        }
        return EMPTY;
    }
}
