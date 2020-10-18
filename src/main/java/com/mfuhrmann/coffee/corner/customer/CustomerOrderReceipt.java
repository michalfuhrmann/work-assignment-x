package com.mfuhrmann.coffee.corner.customer;

import com.mfuhrmann.coffee.corner.calculation.BonusCalculationResult;
import com.mfuhrmann.coffee.corner.money.Price;
import com.mfuhrmann.coffee.corner.products.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class encapsulating the receipt data. It recalculates a values that can be later either obtained or printed to console.
 */
public class CustomerOrderReceipt {

    private final List<Product> customersProducts;
    private final BigDecimal total;

    private final List<BonusCalculationResult> bonuses;
    private final BigDecimal discount;

    public CustomerOrderReceipt(List<Product> customersProducts, List<BonusCalculationResult> bonuses) {
        this.customersProducts = customersProducts;
        this.bonuses = bonuses;
        this.discount = calculateDiscounts(bonuses);
        this.total = calculateTotal(customersProducts).subtract(discount);
    }

    private BigDecimal calculateTotal(List<Product> customersProducts) {
        return customersProducts.stream()
                .map(Product::getPrice)
                .map(Price::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateDiscounts(List<BonusCalculationResult> bonuses) {
        return bonuses.stream()
                .map(BonusCalculationResult::getDiscountPrice)
                .map(Price::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Price getTotal() {
        return new Price(total);
    }

    public void print() {
        String currency = " CHF";

        System.out.println("=====  RECEIPT ======");
        System.out.println("Charlene's Coffee Corner" + System.lineSeparator());
        System.out.println("List of products:");
        customersProducts.forEach(product -> System.out.println(product.getName() + " " + product.getPrice().getValue() + currency));
        if (!bonuses.isEmpty()) {
            System.out.println("Bonuses:");
            bonuses.forEach(bonus -> System.out.println(bonus.getName() + " " + bonus.getDiscountPrice().getValue().negate() + currency));
        }
        System.out.println("==========");
        System.out.println("TOTAL: " + total + currency);
        System.out.println("DISCOUNT: " + discount + currency);
        System.out.println("TO PAY: " + total.subtract(discount) + currency);
    }


}
