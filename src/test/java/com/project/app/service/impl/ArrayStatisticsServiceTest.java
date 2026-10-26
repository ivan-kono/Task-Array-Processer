package com.project.app.service.impl;

import com.project.app.factory.impl.ArrayFactoryImpl;
import com.project.app.service.impl.ArrayStatisticsServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStatisticsServiceTest {

    private final ArrayStatisticsServiceImpl service =
            new ArrayStatisticsServiceImpl();

    private final ArrayFactoryImpl factory =
            new ArrayFactoryImpl();

    @Test
    void calculatesStatisticsForPositiveNumbers() {
        var array = factory.create(1, new double[]{1, 2, 3, 4});

        assertEquals(1.0, service.min(array).orElseThrow());
        assertEquals(4.0, service.max(array).orElseThrow());
        assertEquals(10.0, service.sum(array).orElseThrow());
        assertEquals(2.5, service.average(array).orElseThrow());
    }

    @Test
    void calculatesStatisticsForNegativeNumbers() {
        var array = factory.create(2, new double[]{-5, -2, -8});

        assertEquals(-8.0, service.min(array).orElseThrow());
        assertEquals(-2.0, service.max(array).orElseThrow());
        assertEquals(-15.0, service.sum(array).orElseThrow());
        assertEquals(-5.0, service.average(array).orElseThrow());
    }

    @Test
    void calculatesStatisticsForSingleElement() {
        var array = factory.create(3, new double[]{7});

        assertEquals(7.0, service.min(array).orElseThrow());
        assertEquals(7.0, service.max(array).orElseThrow());
        assertEquals(7.0, service.sum(array).orElseThrow());
        assertEquals(7.0, service.average(array).orElseThrow());
    }

    @Test
    void returnsEmptyOptionalForEmptyArray() {
        var array = factory.create(4, new double[0]);

        assertTrue(service.min(array).isEmpty());
        assertTrue(service.max(array).isEmpty());
        assertTrue(service.sum(array).isEmpty());
        assertTrue(service.average(array).isEmpty());
    }
}
