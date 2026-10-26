package com.project.app.service.impl;

import com.project.app.factory.impl.ArrayFactoryImpl;
import com.project.app.service.impl.ArraySortServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArraySortServiceTest {

    private final ArraySortServiceImpl service =
            new ArraySortServiceImpl();

    private final ArrayFactoryImpl factory =
            new ArrayFactoryImpl();

    @Test
    void sortsWithBothAlgorithms() {
        var array = factory.create(1, new double[]{5, -1, 3, 2});
        double[] expected = {-1, 2, 3, 5};

        assertArrayEquals(expected, service.bubbleSort(array));
        assertArrayEquals(expected, service.mergeSort(array));
    }

    @Test
    void sortsArrayWithDuplicates() {
        var array = factory.create(2, new double[]{2, 1, 2, 1});
        double[] expected = {1, 1, 2, 2};

        assertArrayEquals(expected, service.bubbleSort(array));
        assertArrayEquals(expected, service.mergeSort(array));
    }

    @Test
    void handlesEmptyArrays() {
        var array = factory.create(3, new double[0]);

        assertArrayEquals(new double[0], service.bubbleSort(array));
        assertArrayEquals(new double[0], service.mergeSort(array));
    }

    @Test
    void sortingDoesNotModifyOriginalArray() {
        var array = factory.create(4, new double[]{3, 1, 2});

        service.bubbleSort(array);
        assertArrayEquals(new double[]{3, 1, 2}, array.getValues());

        service.mergeSort(array);
        assertArrayEquals(new double[]{3, 1, 2}, array.getValues());
    }
}
