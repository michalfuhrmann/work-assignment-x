package com.mfuhrmann.coffee.corner.calculation;

import com.mfuhrmann.coffee.corner.money.Price;

public class BonusCalculationResult {
    private final String name;
    private final Price discountPrice;

    public BonusCalculationResult(String name, Price discountPrice) {
        this.name = name;
        this.discountPrice = discountPrice;
    }

    public String getName() {
        return name;
    }

    public Price getDiscountPrice() {
        return discountPrice;
    }
}
