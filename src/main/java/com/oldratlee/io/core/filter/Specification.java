package com.oldratlee.io.core.filter;

import java.util.function.Predicate;

/**
 * Check data.
 *
 * @param <T> data type
 * @author oldratlee
 */
@FunctionalInterface
public interface Specification<T> extends Predicate<T> {
    @Override
    boolean test(T item);
}
