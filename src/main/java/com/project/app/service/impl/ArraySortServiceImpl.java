package com.project.app.service.impl;

import com.project.app.entity.ArrayEntity;
import com.project.app.service.ArraySortService;
import java.util.Arrays;

public class ArraySortServiceImpl implements ArraySortService{
    @Override public double[] bubbleSort(ArrayEntity entity) {
        double[] values = entity.getValues();
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length - i - 1; j++) {
                if (values[j] > values[j + 1]) {
                    double temp = values[j]; values[j] = values[j + 1]; values[j + 1] = temp;
                }
            }
        }
        return values;
    }
    @Override public double[] mergeSort(ArrayEntity entity) {
        double[] values = entity.getValues();
        mergeSort(values, 0, values.length);
        return values;
    }
    private void mergeSort(double[] values, int start, int end) {
        if (end - start < 2) return;
        int middle = (start + end) / 2;
        mergeSort(values, start, middle);
        mergeSort(values, middle, end);
        double[] merged = new double[end - start];
        int left = start, right = middle, index = 0;
        while (left < middle && right < end) merged[index++] = values[left] <= values[right] ? values[left++] : values[right++];
        while (left < middle) merged[index++] = values[left++];
        while (right < end) merged[index++] = values[right++];
        System.arraycopy(merged, 0, values, start, merged.length);
    }
}
