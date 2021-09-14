package com.company.com.money;

import java.math.BigDecimal;

public enum Coin {
    TEN_CENTS(BigDecimal.valueOf(0.10)), TWENTY_CENTS(BigDecimal.valueOf(0.20)),
    FIFTY_CENTS(BigDecimal.valueOf(0.50)), ONE_DOLLAR(BigDecimal.valueOf(1.00)),
    EMPTY(BigDecimal.valueOf(0.00));

    private BigDecimal representVal;

    Coin(BigDecimal representVal){
        this.representVal = representVal;
    }

    public BigDecimal getRepresentVal(){
        return this.representVal;
    }

    public static Coin coinValue(BigDecimal val){
        for(Coin coin: Coin.values()){
            if(val.compareTo(coin.getRepresentVal()) == 0){
                return coin;
            }
        }
        return EMPTY;
    }
}
