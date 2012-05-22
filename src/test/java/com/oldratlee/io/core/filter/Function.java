package com.oldratlee.io.core.filter;

public interface Function<From, To> {
    /**
     * @return return {@code null} to indicate ignore the from data. 
     */
    To map(From from);
}
