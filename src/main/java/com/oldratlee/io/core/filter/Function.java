package com.oldratlee.io.core.filter;

/**
 * transform from one type to another.
 *
 * @param <From> Input data type
 * @param <To>   Output data type
 * @author oldratlee
 */
@FunctionalInterface
public interface Function<From, To> extends java.util.function.Function<From, To> {
    /**
     * @return return the transformed data. {@code null} to indicate ignore the input data.
     */
    To map(From from);

    @Override
    default To apply(From from) {
        return map(from);
    }
}
