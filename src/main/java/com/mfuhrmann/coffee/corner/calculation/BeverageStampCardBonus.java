package com.mfuhrmann.coffee.corner.calculation;

import com.mfuhrmann.coffee.corner.customer.CustomerOrder;
import com.mfuhrmann.coffee.corner.money.Price;
import com.mfuhrmann.coffee.corner.products.Product;
import com.mfuhrmann.coffee.corner.products.ProductType;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * Bonus logic that is calculated whenever customer shows a stamp card.
 */
public class BeverageStampCardBonus implements BonusProgram {
    public static final int BONUS_THRESHOLD = 5;
    private static final String BONUS_TEXT = "5th Beverage Bonus";


    @Override
    public Optional<BonusCalculationResult> calculateBonus(CustomerOrder customerOrder) {
        List<Product> beverages = customerOrder.getProductsGrouped().getOrDefault(ProductType.BEVERAGE, Collections.emptyList());

        return customerOrder.getCustomerStampCard()
                .filter(stampCard -> stampCard.getStampsCount() + beverages.size() >= BONUS_THRESHOLD)
                .map(stampCard -> getPricesOfEvery5thBeverage(beverages, stampCard));
    }

    private BonusCalculationResult getPricesOfEvery5thBeverage(List<Product> beverages, com.mfuhrmann.coffee.corner.customer.CustomerStampCard stampCard) {
        int discountCounts = (stampCard.getStampsCount() + beverages.size()) / BONUS_THRESHOLD;
        int offset = BONUS_THRESHOLD - 1 - stampCard.getStampsCount();

        BigDecimal discount = IntStream.range(0, discountCounts)
                .map(op -> (op * BONUS_THRESHOLD) + offset)
                .mapToObj(op -> beverages.get(op).getPrice().getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new BonusCalculationResult(BONUS_TEXT, new Price(discount));
    }

}
