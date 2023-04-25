package com.oldratlee.io.core.filter;

/**
 * Check data.
 *
 * @param <T> data type
 * @author oldratlee
 */
@FunctionalInterface
public interface Specification<T> {
    boolean test(T item);
}
