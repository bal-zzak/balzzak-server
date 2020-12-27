package com.balzzak.goods.model.enums;

public enum SaleState {
    NONE(0),
    TEST1(1),
    TEST2(2);

    private final int index;

    SaleState(int index) {
        this.index = index;
    }
}
