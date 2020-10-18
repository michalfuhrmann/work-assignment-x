package com.mfuhrmann.coffee.corner.products;

import com.mfuhrmann.coffee.corner.money.Price;

/**
 * Snack subcategory
 */
public class Snack implements Product {

    private final String name;
    private final Price price;

    public Snack(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }

    public ProductType getProductType() {
        return ProductType.SNACK;
    }

}
