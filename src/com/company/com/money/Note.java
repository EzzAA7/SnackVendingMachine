package com.company.com.money;

import java.math.BigDecimal;

public enum Note {
    TWENTY_DOLLARS(BigDecimal.valueOf(20.00)), FIFTY_DOLLARS(BigDecimal.valueOf(50.00));

    private BigDecimal representVal;

    Note (BigDecimal representVal){
        this.representVal = representVal;
    }

    public BigDecimal getRepresentVal(){
        return this.representVal;
    }
}
