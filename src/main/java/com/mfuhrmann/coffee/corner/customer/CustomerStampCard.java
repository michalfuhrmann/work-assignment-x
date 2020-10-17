package com.mfuhrmann.coffee.corner.customer;

import com.mfuhrmann.coffee.corner.calculation.BeverageStampCardBonus;

public class CustomerStampCard {
    private static final String ERROR_MSG = "Customer Stamp card can only have 0-%s stamps";
    private final int beverageCount;

    public CustomerStampCard(int beverageCount) {
        if (beverageCount < 0 || beverageCount > BeverageStampCardBonus.BONUS_THRESHOLD) {
            throw new IllegalArgumentException(String.format(ERROR_MSG, BeverageStampCardBonus.BONUS_THRESHOLD - 1));
        }
        this.beverageCount = beverageCount;
    }

    public int getBeverageCount() {
        return beverageCount;
    }
}
