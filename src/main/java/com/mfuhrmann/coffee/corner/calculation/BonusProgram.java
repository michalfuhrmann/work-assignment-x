package com.mfuhrmann.coffee.corner.calculation;

import com.mfuhrmann.coffee.corner.customer.CustomerOrder;

import java.util.Optional;

public interface BonusProgram {

    Optional<BonusCalculationResult> calculateBonus(CustomerOrder customerOrder);
}
