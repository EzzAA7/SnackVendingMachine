package components;

import interfaces.MonetaryValue;

import java.math.BigDecimal;

public enum Coin implements MonetaryValue {
    TEN_CENTS(BigDecimal.valueOf(0.10)), TWENTY_CENTS(BigDecimal.valueOf(0.20)),
    FIFTY_CENTS(BigDecimal.valueOf(0.50)), ONE_DOLLAR(BigDecimal.valueOf(1.00)),
    EMPTY(BigDecimal.valueOf(0.00));

    private BigDecimal representVal;

    Coin(BigDecimal representVal){

        this.representVal = representVal;
    }

    @Override
    public BigDecimal getRepresentVal(){

        return this.representVal;
    }

    /**
     * checks if such a coin value exists
     * @param val the user inputted coin value
     * @return the coin representing the inputted value
     */
    public static Coin coinValue(BigDecimal val){
        for(Coin coin: Coin.values()){
            if(val.compareTo(coin.getRepresentVal()) == 0){
                return coin;
            }
        }
        return EMPTY;
    }
}
