package com.project.app.service;

import com.project.app.entity.ArrayEntity;

public interface ArraySortService {
    double[] bubbleSort(ArrayEntity entity);
    double[] mergeSort(ArrayEntity entity);
}
