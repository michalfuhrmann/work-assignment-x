package com.mfuhrmann.coffee.corner.calculation;

import com.mfuhrmann.coffee.corner.customer.CustomerOrder;

import java.util.Optional;

/**
 * Interface describing contract for creation bonus programs.
 */
public interface BonusProgram {

    Optional<BonusCalculationResult> calculateBonus(CustomerOrder customerOrder);
}
