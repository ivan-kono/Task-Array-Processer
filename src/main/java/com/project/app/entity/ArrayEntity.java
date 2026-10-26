package com.project.app.entity;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public final class ArrayEntity {
    private final int id;
    private final List<ArrayChangeListener> listeners = new ArrayList<>();
    private final double[] values;

    public ArrayEntity(int id, double[] values) {
        if (id == 0) {
            throw new IllegalArgumentException("Array id must not be blank");
        }
        if (values == null) {
            throw new IllegalArgumentException("Array values must not be null");
        }
        this.id = id;
        this.values = Arrays.copyOf(values, values.length);
    }

    public int getId() { return this.id; }
    public double[] getValues() { return Arrays.copyOf(values, values.length); }
    public int size() { return values.length; }

    public void setValue(int index, double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Array values must be finite");
        }
        values[index] = value;
        notifyListeners();
    }

    public void addListener(ArrayChangeListener listener) {
        if (listener != null && !listeners.contains(listener)) listeners.add(listener);
    }

    public void removeListener(ArrayChangeListener listener) { listeners.remove(listener); }

    private void notifyListeners() {
        for (ArrayChangeListener listener : List.copyOf(listeners)) listener.onArrayChanged(this);
    }

    @Override
    public String toString() { return id + "=" + Arrays.toString(values); }

    public int hashCode() {
        int result = this.getId() ^ this.getId() >>> 32;

        for(double value : this.values) {
            long bits = Double.doubleToLongBits(value);
            result = 31 * result + (int)(bits ^ bits >>> 32);
        }

        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            ArrayEntity other = (ArrayEntity)obj;
            if (this.getId() != other.getId()) {
                return false;
            } else if (this.values.length != other.values.length) {
                return false;
            } else {
                for(int i = 0; i < this.values.length; ++i) {
                    if (Double.compare(this.values[i], other.values[i]) != 0) {
                        return false;
                    }
                }

                return true;
            }
        }
    }
}

