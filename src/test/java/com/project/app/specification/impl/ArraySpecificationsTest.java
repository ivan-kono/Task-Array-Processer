package com.project.app.specification.impl;

import com.project.app.factory.impl.ArrayFactoryImpl;
import com.project.app.service.impl.ArrayStatisticsServiceImpl;
import com.project.app.specification.impl.ArraySpecifications;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArraySpecificationsTest {

    private final ArraySpecifications specifications =
            new ArraySpecifications(new ArrayStatisticsServiceImpl());

    private final ArrayFactoryImpl factory =
            new ArrayFactoryImpl();

    @Test
    void findsArrayById() {
        var array = factory.create(1, new double[]{1, 2, 3});

        assertTrue(specifications.idEquals(1).isSatisfiedBy(array));
        assertFalse(specifications.idEquals(2).isSatisfiedBy(array));
    }

    @Test
    void findsArrayContainingValue() {
        var array = factory.create(3, new double[]{10, 20, 30});

        assertTrue(specifications.containsValue(20).isSatisfiedBy(array));
        assertFalse(specifications.containsValue(25).isSatisfiedBy(array));
    }

    @Test
    void findsArraysBySumAndSize() {
        var array = factory.create(4, new double[]{2, 4, 6});

        assertTrue(
                specifications.sum(sum -> sum > 10).isSatisfiedBy(array)
        );
        assertTrue(
                specifications.size(size -> size == 3).isSatisfiedBy(array)
        );
        assertFalse(
                specifications.sum(sum -> sum < 10).isSatisfiedBy(array)
        );
    }
}

