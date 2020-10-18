package com.mfuhrmann.coffee.corner.products;

import com.mfuhrmann.coffee.corner.money.Price;

/**
 * Base Product interface. Defines contract that needs to be fulfilled in case a new product type will be added.
 */
public interface Product {

    String getName();

    Price getPrice();

    ProductType getProductType();

}
