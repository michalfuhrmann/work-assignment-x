package com.mfuhrmann.coffee.corner.products;

import com.mfuhrmann.coffee.corner.money.Price;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class containing all available products in the menu.
 *
 */
public class ProductsMenu {

    public static final String SMALL_COFFEE = "Small Coffee";
    public static final String MEDIUM_COFFEE = "Medium Coffee";
    public static final String LARGE_COFFEE = "Large Coffee";
    public static final String ORANGE_JUICE = "Orange juice";
    public static final String BACON_ROLL = "Bacon Roll";
    public static final String EXTRA_MILK = "Extra Milk";
    public static final String FOAMED_MILK = "Foamed Milk";
    public static final String SPECIAL_ROAST_COFFEE = "Special Roast Coffee";

    private final Map<String, Product> map = initProductsMap();

    public ProductsMenu() {
    }

    /**
     * Finds menu product by its name
     * @param name name
     * @return optional of product or empty optional if not found
     */
    public Optional<Product> findByName(String name) {
        return Optional.ofNullable(map.get(name));
    }

    private Map<String, Product> initProductsMap() {

        return List.of(
                new Beverage(SMALL_COFFEE, new Price(BigDecimal.valueOf(2.50))),
                new Beverage(MEDIUM_COFFEE, new Price(BigDecimal.valueOf(3.00))),
                new Beverage(LARGE_COFFEE, new Price(BigDecimal.valueOf(3.50))),
                new Beverage(ORANGE_JUICE, new Price(BigDecimal.valueOf(3.95))),
                new Snack(BACON_ROLL, new Price(BigDecimal.valueOf(4.50))),
                new Extra(EXTRA_MILK, new Price(BigDecimal.valueOf(0.30))),
                new Extra(FOAMED_MILK, new Price(BigDecimal.valueOf(0.50))),
                new Extra(SPECIAL_ROAST_COFFEE, new Price(BigDecimal.valueOf(0.90))))
                .stream()
                .collect(Collectors.toMap(Product::getName, Function.identity()));
    }
}
