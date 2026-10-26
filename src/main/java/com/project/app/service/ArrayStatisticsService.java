package com.project.app.service;

import com.project.app.entity.ArrayEntity;
import java.util.OptionalDouble;

public interface ArrayStatisticsService {
    OptionalDouble min(ArrayEntity entity);
    OptionalDouble max(ArrayEntity entity);
    OptionalDouble sum(ArrayEntity entity);
    OptionalDouble average(ArrayEntity entity);
}
