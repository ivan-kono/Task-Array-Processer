package com.project.app.repository;

import com.project.app.entity.ArrayEntity;
import com.project.app.specification.Specification;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface ArrayRepository {
    void add(ArrayEntity entity);
    boolean remove(int id);
    Optional<ArrayEntity> findById(int id);
    List<ArrayEntity> find(Specification<ArrayEntity> specification);
    List<ArrayEntity> findAll();
    List<ArrayEntity> sorted(Comparator<ArrayEntity> comparator);
}
