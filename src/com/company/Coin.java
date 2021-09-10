package com.company;

public enum Coin {
    ONE_SHEKELS(1), TWO_SHEKELS(2),  FIVE_SHEKELS(5), TEN_SHEKELS(10);

    private int representVal;

    Coin(int representVal){
        this.representVal = representVal;
    }

    public int getrepresentVal(){
        return this.representVal;
    }
}
