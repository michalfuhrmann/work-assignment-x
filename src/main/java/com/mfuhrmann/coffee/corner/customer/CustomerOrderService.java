package com.mfuhrmann.coffee.corner.customer;

import com.mfuhrmann.coffee.corner.calculation.BonusCalculationResult;
import com.mfuhrmann.coffee.corner.calculation.BonusProgram;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service handling customer orders and responsible for calculating bonuses.
 */
public class CustomerOrderService {

    private final List<BonusProgram> bonusPrograms;

    public CustomerOrderService(List<BonusProgram> bonusPrograms) {
        this.bonusPrograms = bonusPrograms;
    }

    public CustomerOrderReceipt acceptOrder(CustomerOrder customerOrder) {

        List<BonusCalculationResult> bonuses = bonusPrograms.stream()
                .flatMap(bonusProgram -> bonusProgram.calculateBonus(customerOrder).stream())
                .collect(Collectors.toList());

        return new CustomerOrderReceipt(customerOrder.getCustomersProducts(), bonuses);
    }

}
