package com.mfuhrmann.coffee.corner.customer;

import com.mfuhrmann.coffee.corner.calculation.BeverageStampCardBonus;
import com.mfuhrmann.coffee.corner.calculation.BeverageAndSnackBonus;
import com.mfuhrmann.coffee.corner.money.Price;
import com.mfuhrmann.coffee.corner.products.Product;
import com.mfuhrmann.coffee.corner.products.ProductsMenu;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerOrderServiceTest {

    private final CustomerOrderService customerOrderService = new CustomerOrderService(List.of(new BeverageStampCardBonus(), new BeverageAndSnackBonus()));
    private final ProductsMenu productsMenu = new ProductsMenu();


    @ParameterizedTest
    @MethodSource("productsWithNoBonuses")
    void shouldReturnPriceOfProductGivenNoBonuses(List<String> products, Double expectedPrice) {
        //Given
        List<Product> customerProducts = products.stream()
                .map(p -> productsMenu.findByName(p).orElseThrow())
                .collect(Collectors.toList());
        CustomerOrder customerOrder = new CustomerOrder(customerProducts);

        //When
        CustomerOrderReceipt customerOrderReceipt = customerOrderService.acceptOrder(customerOrder);

        //Then
        assertEquals(new Price(BigDecimal.valueOf(expectedPrice)), customerOrderReceipt.getTotal());

    }

    private static Stream<Arguments> productsWithNoBonuses() {
        return Stream.of(
                Arguments.of(List.of(ProductsMenu.SMALL_COFFEE), 2.5),
                Arguments.of(List.of(ProductsMenu.SMALL_COFFEE, ProductsMenu.SMALL_COFFEE), 5.0)
        );
    }

    @ParameterizedTest
    @MethodSource("productsWithCustomerStampCard")
    void shouldProperlyReturnPriceOfProductIncludingStampCardBonus(List<String> products, int customerStamps, Double expectedPrice) {
        //Given
        List<Product> customerProducts = products.stream()
                .map(p -> productsMenu.findByName(p).orElseThrow())
                .collect(Collectors.toList());
        CustomerStampCard customerStampCard = new CustomerStampCard(customerStamps);
        CustomerOrder customerOrder = new CustomerOrder(customerProducts, customerStampCard);

        //When
        CustomerOrderReceipt customerOrderReceipt = customerOrderService.acceptOrder(customerOrder);

        //Then
        assertEquals(new Price(BigDecimal.valueOf(expectedPrice)), customerOrderReceipt.getTotal());

    }

    private static Stream<Arguments> productsWithCustomerStampCard() {
        return Stream.of(
                Arguments.of(List.of(ProductsMenu.SMALL_COFFEE), 3, 2.5),
                Arguments.of(List.of(ProductsMenu.SMALL_COFFEE, ProductsMenu.SMALL_COFFEE), 2, 5.0),
                Arguments.of(List.of(ProductsMenu.SMALL_COFFEE, ProductsMenu.SMALL_COFFEE), 3, 2.5),
                Arguments.of(List.of(ProductsMenu.SMALL_COFFEE, ProductsMenu.LARGE_COFFEE), 4, 3.5),
                Arguments.of(List.of(ProductsMenu.LARGE_COFFEE, ProductsMenu.SMALL_COFFEE), 4, 2.5),
                Arguments.of(List.of(ProductsMenu.MEDIUM_COFFEE, ProductsMenu.MEDIUM_COFFEE, ProductsMenu.MEDIUM_COFFEE, ProductsMenu.MEDIUM_COFFEE, ProductsMenu.MEDIUM_COFFEE, ProductsMenu.MEDIUM_COFFEE), 4, 12.0),
                Arguments.of(List.of(ProductsMenu.LARGE_COFFEE, ProductsMenu.MEDIUM_COFFEE, ProductsMenu.MEDIUM_COFFEE, ProductsMenu.MEDIUM_COFFEE, ProductsMenu.MEDIUM_COFFEE, ProductsMenu.LARGE_COFFEE), 4, 12.0)
        );

    }

    @ParameterizedTest
    @MethodSource("productsWithExtrasBonus")
    void shouldReturnPriceIncludingBeverageAndSnackBonus(List<String> products, Double expectedPrice) {
        //Given
        List<Product> customerProducts = products.stream()
                .map(p -> productsMenu.findByName(p).orElseThrow())
                .collect(Collectors.toList());
        CustomerOrder customerOrder = new CustomerOrder(customerProducts);

        //When
        CustomerOrderReceipt customerOrderReceipt = customerOrderService.acceptOrder(customerOrder);

        //Then
        assertEquals(new Price(BigDecimal.valueOf(expectedPrice)), customerOrderReceipt.getTotal());

    }

    private static Stream<Arguments> productsWithExtrasBonus() {
        return Stream.of(
                Arguments.of(List.of(ProductsMenu.SMALL_COFFEE, ProductsMenu.BACON_ROLL), 7.0),
                Arguments.of(List.of(ProductsMenu.SMALL_COFFEE, ProductsMenu.BACON_ROLL, ProductsMenu.EXTRA_MILK), 7.0),
                Arguments.of(List.of(ProductsMenu.SMALL_COFFEE, ProductsMenu.BACON_ROLL, ProductsMenu.EXTRA_MILK, ProductsMenu.SPECIAL_ROAST_COFFEE), 7.9),
                Arguments.of(List.of(ProductsMenu.SMALL_COFFEE, ProductsMenu.BACON_ROLL, ProductsMenu.SMALL_COFFEE, ProductsMenu.BACON_ROLL, ProductsMenu.EXTRA_MILK, ProductsMenu.SPECIAL_ROAST_COFFEE), 14.0)
        );
    }

    @ParameterizedTest
    @MethodSource("productsWithCustomerStampCardAndExtrasBonuses")
    void shouldReturnPriceGivenTwoBonuses(List<String> products, int customerStamps, Double expectedPrice) {
        //Given
        List<Product> customerProducts = products.stream()
                .map(p -> productsMenu.findByName(p).orElseThrow())
                .collect(Collectors.toList());
        CustomerStampCard customerStampCard = new CustomerStampCard(customerStamps);
        CustomerOrder customerOrder = new CustomerOrder(customerProducts, customerStampCard);

        //When
        CustomerOrderReceipt customerOrderReceipt = customerOrderService.acceptOrder(customerOrder);

        //Then
        assertEquals(new Price(BigDecimal.valueOf(expectedPrice)), customerOrderReceipt.getTotal());

    }

    private static Stream<Arguments> productsWithCustomerStampCardAndExtrasBonuses() {
        return Stream.of(
                Arguments.of(List.of(ProductsMenu.SMALL_COFFEE, ProductsMenu.BACON_ROLL), 4, 4.5),
                Arguments.of(List.of(ProductsMenu.SMALL_COFFEE, ProductsMenu.BACON_ROLL, ProductsMenu.EXTRA_MILK), 4, 4.5),
                Arguments.of(List.of(ProductsMenu.SMALL_COFFEE, ProductsMenu.BACON_ROLL, ProductsMenu.BACON_ROLL, ProductsMenu.EXTRA_MILK), 4, 9.0)
        );

    }

}
