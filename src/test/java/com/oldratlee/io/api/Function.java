package com.oldratlee.io.api;

interface Function<From, To> {
    To map(From from);
}
