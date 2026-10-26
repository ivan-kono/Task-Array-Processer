package com.project.app.warehouse;

import com.project.app.entity.ArrayEntity;
import com.project.app.service.ArrayStatisticsService;
import com.project.app.service.impl.ArrayStatisticsServiceImpl;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class Warehouse {
    private static Warehouse instance;
    private final Map<Integer, ArrayMetrics> metrics = new LinkedHashMap<>();
    private final ArrayStatisticsService statisticsService = new ArrayStatisticsServiceImpl();

    private Warehouse() { }
    public static Warehouse getInstance() {
        if (instance == null) instance = new Warehouse();
        return instance;
    }
    public void recalculate(ArrayEntity entity) {
        metrics.put(entity.getId(), new ArrayMetrics(statisticsService.min(entity),
                statisticsService.max(entity), statisticsService.sum(entity),
                statisticsService.average(entity), entity.size()));
    }
    public void remove(int id) { metrics.remove(id); }
    public ArrayMetrics get(int id) { return metrics.get(id); }
    public Map<Integer, ArrayMetrics> getAll() { return Collections.unmodifiableMap(new LinkedHashMap<>(metrics)); }
}

