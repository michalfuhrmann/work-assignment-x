package com.mfuhrmann.coffee.corner.products;

import com.mfuhrmann.coffee.corner.money.Price;

public interface Product {

    String getName();

    Price getPrice();

    ProductType getProductType();

}
