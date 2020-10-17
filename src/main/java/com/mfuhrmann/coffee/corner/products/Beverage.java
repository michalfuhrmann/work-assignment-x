package com.mfuhrmann.coffee.corner.products;

import com.mfuhrmann.coffee.corner.money.Price;

public class Beverage implements Product {

    private final String name;
    private final Price price;

    public Beverage(String name, Price price) {
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
        return ProductType.BEVERAGE;
    }

}

