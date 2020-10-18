package com.mfuhrmann.coffee.corner.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomerStampCardTest {


    @ParameterizedTest
    @MethodSource("validStamps")
    public void shouldCreateStampCardWithValidNumberOfStamps(int stamps) {
        //When
        CustomerStampCard customerStampCard = new CustomerStampCard(stamps);

        //Then
        assertEquals(customerStampCard.getStampsCount(), stamps);
    }

    @ParameterizedTest
    @MethodSource("invalidStamps")
    public void shouldThrowExceptionWhenCreatingStampCardWithInvalidNumberOfStamps(int stamps) {
        //When
        assertThrows(IllegalArgumentException.class, () -> new CustomerStampCard(stamps));

    }

    private static Stream<Arguments> invalidStamps() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(-100),
                Arguments.of(5),
                Arguments.of(100));
    }

    private static Stream<Arguments> validStamps() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(2),
                Arguments.of(4));
    }
}
