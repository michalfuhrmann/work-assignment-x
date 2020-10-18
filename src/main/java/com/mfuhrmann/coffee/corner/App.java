package com.mfuhrmann.coffee.corner;

import com.mfuhrmann.coffee.corner.calculation.BeverageAndSnackBonus;
import com.mfuhrmann.coffee.corner.calculation.BeverageStampCardBonus;
import com.mfuhrmann.coffee.corner.customer.CustomerOrder;
import com.mfuhrmann.coffee.corner.customer.CustomerOrderReceipt;
import com.mfuhrmann.coffee.corner.customer.CustomerOrderService;
import com.mfuhrmann.coffee.corner.customer.CustomerStampCard;
import com.mfuhrmann.coffee.corner.products.Product;
import com.mfuhrmann.coffee.corner.products.ProductsMenu;

import java.util.List;

public class App {

    public static void main(String[] args) {

        CustomerOrderService customerOrderService = new CustomerOrderService(List.of(new BeverageStampCardBonus(), new BeverageAndSnackBonus()));
        ProductsMenu productsMenu = new ProductsMenu();
        List<Product> products = List.of(
                productsMenu.findByName(ProductsMenu.SMALL_COFFEE).orElseThrow(),
                productsMenu.findByName(ProductsMenu.BACON_ROLL).orElseThrow(),
                productsMenu.findByName(ProductsMenu.EXTRA_MILK).orElseThrow(),
                productsMenu.findByName(ProductsMenu.SPECIAL_ROAST_COFFEE).orElseThrow());

        CustomerStampCard customerStampCard = new CustomerStampCard(4);

        CustomerOrderReceipt customerOrderReceipt = customerOrderService.acceptOrder(new CustomerOrder(products, customerStampCard));
        customerOrderReceipt.print();


    }
}
