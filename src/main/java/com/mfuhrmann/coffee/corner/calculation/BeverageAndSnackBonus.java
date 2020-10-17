package com.mfuhrmann.coffee.corner.calculation;

import com.mfuhrmann.coffee.corner.customer.CustomerOrder;
import com.mfuhrmann.coffee.corner.money.Price;
import com.mfuhrmann.coffee.corner.products.Product;
import com.mfuhrmann.coffee.corner.products.ProductType;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BeverageAndSnackBonus implements BonusProgram {

    private static final String BONUS_TEXT = "Beverage and Snack Extra's Bonus";

    @Override
    public Optional<BonusCalculationResult> calculateBonus(CustomerOrder customerOrder) {

        List<Product> snacks = customerOrder.getProductsGrouped().getOrDefault(ProductType.SNACK, Collections.emptyList());
        List<Product> beverages = customerOrder.getProductsGrouped().getOrDefault(ProductType.BEVERAGE, Collections.emptyList());
        List<Product> extras = customerOrder.getProductsGrouped().getOrDefault(ProductType.EXTRA, Collections.emptyList());

        int min = Math.min(snacks.size(), beverages.size());

        if (min > 0) {
            BigDecimal price = extras.stream()
                    .limit(min)
                    .map(product -> product.getPrice().getValue())
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            return Optional.of(new BonusCalculationResult(BONUS_TEXT, new Price(price)));
        }
        return Optional.empty();
    }
}
