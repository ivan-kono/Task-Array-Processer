package com.project.app.service.impl;

import com.project.app.entity.ArrayEntity;
import com.project.app.service.ArrayStatisticsService;

import java.util.OptionalDouble;

public class ArrayStatisticsServiceImpl implements ArrayStatisticsService {
    @Override public OptionalDouble min(ArrayEntity entity) {
        double[] values = entity.getValues();
        double result = values[0];
        for (double value : values) if (value < result) result = value;
        return OptionalDouble.of(result);
    }
    @Override public OptionalDouble max(ArrayEntity entity) {
        double[] values = entity.getValues();
        double result = values[0];
        for (double value : values) if (value > result) result = value;
        return OptionalDouble.of(result);
    }
    @Override public OptionalDouble sum(ArrayEntity entity) {
        double[] values = entity.getValues();
        double result = 0;
        for (double value : values) result += value;
        return OptionalDouble.of(result);
    }
    @Override public OptionalDouble average(ArrayEntity entity) {
        double[] values = entity.getValues();
        return OptionalDouble.of(sum(entity).orElseThrow() / values.length);
    }
}
