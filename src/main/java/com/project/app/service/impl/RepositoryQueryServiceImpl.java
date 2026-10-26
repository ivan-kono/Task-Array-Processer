package com.project.app.service.impl;

import com.project.app.entity.ArrayEntity;
import com.project.app.repository.ArrayRepository;
import com.project.app.service.RepositoryQueryService;
import com.project.app.specification.Specification;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class RepositoryQueryServiceImpl implements RepositoryQueryService {
    private final ArrayRepository repository;
    public RepositoryQueryServiceImpl(ArrayRepository repository) { this.repository = repository; }
    @Override public Optional<ArrayEntity> findById(int id) { return repository.findById(id); }
    @Override public List<ArrayEntity> find(Specification<ArrayEntity> specification) { return repository.find(specification); }
    @Override public List<ArrayEntity> sorted(Comparator<ArrayEntity> comparator) { return repository.sorted(comparator); }
}
