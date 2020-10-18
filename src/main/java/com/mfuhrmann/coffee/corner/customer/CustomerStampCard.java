package com.mfuhrmann.coffee.corner.customer;

import com.mfuhrmann.coffee.corner.calculation.BeverageStampCardBonus;

/**
 * Class that is used for wrapping logic related to customer stamp card.
 * It is assumed it is presented by the customer and it's state is not stored anywhere inside of the application.
 */
public class CustomerStampCard {
    private static final String ERROR_MSG = "Customer Stamp card can only have 0-%s stamps";
    private final int beverageCount;

    public CustomerStampCard(int beverageCount) {
        if (beverageCount < 0 || beverageCount > BeverageStampCardBonus.BONUS_THRESHOLD - 1) {
            throw new IllegalArgumentException(String.format(ERROR_MSG, BeverageStampCardBonus.BONUS_THRESHOLD - 1));
        }
        this.beverageCount = beverageCount;
    }

    public int getStampsCount() {
        return beverageCount;
    }
}
