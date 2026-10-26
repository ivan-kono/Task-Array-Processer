package com.project.app.specification.impl;

import com.project.app.entity.ArrayEntity;
import com.project.app.service.ArrayStatisticsService;
import com.project.app.specification.Specification;
import java.util.function.Predicate;

public class ArraySpecifications {
    private final ArrayStatisticsService statistics;
    public ArraySpecifications(ArrayStatisticsService statistics) { this.statistics = statistics; }

    public Specification<ArrayEntity> idEquals(int id) { return entity -> entity.getId() == id; }
    public Specification<ArrayEntity> containsValue(double value) {
        return entity -> { for (double item : entity.getValues()) if (Double.compare(item, value) == 0) return true; return false; };
    }
    public Specification<ArrayEntity> size(Predicate<Integer> predicate) { return entity -> predicate.test(entity.size()); }
    public Specification<ArrayEntity> sum(Predicate<Double> predicate) {
        return entity -> statistics.sum(entity).isPresent() && predicate.test(statistics.sum(entity).getAsDouble());
    }
    public Specification<ArrayEntity> average(Predicate<Double> predicate) {
        return entity -> statistics.average(entity).isPresent() && predicate.test(statistics.average(entity).getAsDouble());
    }
    public Specification<ArrayEntity> min(Predicate<Double> predicate) {
        return entity -> statistics.min(entity).isPresent() && predicate.test(statistics.min(entity).getAsDouble());
    }
    public Specification<ArrayEntity> max(Predicate<Double> predicate) {
        return entity -> statistics.max(entity).isPresent() && predicate.test(statistics.max(entity).getAsDouble());
    }
}

