package com.project.app.factory.impl;

import com.project.app.factory.ArrayFactory;
import com.project.app.entity.ArrayEntity;

public class ArrayFactoryImpl implements ArrayFactory {
    @Override
    public ArrayEntity create(int id, double[] values) {
        return new ArrayEntityBuilder().id(id).values(values).build();
    }
}

