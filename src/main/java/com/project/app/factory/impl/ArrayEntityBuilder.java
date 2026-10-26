package com.project.app.factory.impl;

import com.project.app.entity.ArrayEntity;
import java.util.Arrays;

public class ArrayEntityBuilder {
    private int id;
    private double[] values;

    public ArrayEntityBuilder id(int id) { this.id = id; return this; }
    public ArrayEntityBuilder values(double[] values) {
        this.values = values == null ? null : Arrays.copyOf(values, values.length);
        return this;
    }
    public ArrayEntity build() {
        return new ArrayEntity(id, values);
    }
}

