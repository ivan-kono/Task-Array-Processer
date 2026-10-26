package com.project.app.specification;

public interface Specification<T> {
    boolean isSatisfiedBy(T candidate);
}
