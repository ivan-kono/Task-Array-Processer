package com.project.app.repository.impl;

import com.project.app.entity.ArrayChangeListener;
import com.project.app.entity.ArrayEntity;
import com.project.app.specification.Specification;
import com.project.app.warehouse.Warehouse;
import com.project.app.repository.ArrayRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class ArrayRepositoryImpl implements ArrayRepository, ArrayChangeListener {
    private static ArrayRepositoryImpl instance;
    private final Map<Integer, ArrayEntity> entities = new LinkedHashMap<>();
    private final Warehouse warehouse = Warehouse.getInstance();

    private ArrayRepositoryImpl() { }
    public static ArrayRepositoryImpl getInstance() {
        if (instance == null) instance = new ArrayRepositoryImpl();
        return instance;
    }
    @Override public void add(ArrayEntity entity) {
        if (entity == null) throw new IllegalArgumentException("Entity must not be null");
        if (entities.containsKey(entity.getId())) throw new IllegalArgumentException("Duplicate id: " + entity.getId());
        entities.put(entity.getId(), entity);
        entity.addListener(this);
        warehouse.recalculate(entity);
    }
    @Override public boolean remove(int id) {
        ArrayEntity entity = entities.remove(id);
        if (entity == null) return false;
        entity.removeListener(this);
        warehouse.remove(id);
        return true;
    }
    @Override public Optional<ArrayEntity> findById(int id) { return Optional.ofNullable(entities.get(id)); }
    @Override public List<ArrayEntity> find(Specification<ArrayEntity> specification) {
        List<ArrayEntity> result = new ArrayList<>();
        for (ArrayEntity entity : entities.values()) if (specification.isSatisfiedBy(entity)) result.add(entity);
        return result;
    }
    @Override public List<ArrayEntity> findAll() { return new ArrayList<>(entities.values()); }
    @Override public List<ArrayEntity> sorted(Comparator<ArrayEntity> comparator) {
        List<ArrayEntity> result = findAll();
        result.sort(comparator);
        return result;
    }
    @Override public void onArrayChanged(ArrayEntity entity) { warehouse.recalculate(entity); }
    public void clear() {
        for (ArrayEntity entity : entities.values()) entity.removeListener(this);
        for (int id : entities.keySet()) warehouse.remove(id);
        entities.clear();
    }
}
