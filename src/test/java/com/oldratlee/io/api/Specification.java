package com.oldratlee.io.api;

interface Specification<T> {
    boolean test(T item);
}
