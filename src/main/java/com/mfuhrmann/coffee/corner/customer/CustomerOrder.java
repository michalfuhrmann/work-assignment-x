package com.mfuhrmann.coffee.corner.customer;

import com.mfuhrmann.coffee.corner.products.Product;
import com.mfuhrmann.coffee.corner.products.ProductType;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class encapsulating customer order. Besides a list of product there is a possibility to present a stamp card.
 */
public class CustomerOrder {

    private final Map<ProductType, List<Product>> productsGrouped;
    private final List<Product> customersProducts;
    private final CustomerStampCard customerStampCard;

    public CustomerOrder(List<Product> products, CustomerStampCard customerStampCard) {
        this.productsGrouped = products.stream()
                .collect(Collectors.groupingBy(Product::getProductType));
        customersProducts = products;
        this.customerStampCard = customerStampCard;
    }

    public CustomerOrder(List<Product> products) {
        this(products, null);
    }

    public Optional<CustomerStampCard> getCustomerStampCard() {
        return Optional.ofNullable(customerStampCard);
    }

    public List<Product> getCustomersProducts() {
        return customersProducts;
    }

    public Map<ProductType, List<Product>> getProductsGrouped() {
        return productsGrouped;
    }
}
