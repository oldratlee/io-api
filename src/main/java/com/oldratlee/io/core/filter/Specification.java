package com.oldratlee.io.core.filter;

/**
 * Check data.
 *  
 * @author oldratlee
 *
 * @param <T> data type
 */
@FunctionalInterface
public interface Specification<T> {
    boolean test(T item);
}
