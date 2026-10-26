package com.project.app.service;

import com.project.app.entity.ArrayEntity;
import com.project.app.repository.ArrayRepository;
import com.project.app.specification.Specification;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public interface RepositoryQueryService {
    Optional<ArrayEntity> findById(int id);
    List<ArrayEntity> find(Specification<ArrayEntity> specification);
    List<ArrayEntity> sorted(Comparator<ArrayEntity> comparator);
}
