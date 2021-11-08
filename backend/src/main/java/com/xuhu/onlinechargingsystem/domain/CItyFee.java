package com.xuhu.onlinechargingsystem.domain;

public enum CItyFee {
    PARIS(10),
    CHONGQING(20),
    NEWYORK(10),
    LONDON(15);

    private int value;
    private CItyFee(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
