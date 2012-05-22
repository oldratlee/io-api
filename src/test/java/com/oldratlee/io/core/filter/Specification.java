package com.oldratlee.io.core.filter;

public interface Specification<T> {
    boolean test(T item);
}
