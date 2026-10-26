package com.project.app.factory;

import com.project.app.entity.ArrayEntity;

public interface ArrayFactory {
    ArrayEntity create(int id, double[] values);
}
