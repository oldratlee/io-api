package com.oldratlee.io.core;

public interface Receiver<T, ReceiverThrowableType extends Throwable> {
    void receive(T item) throws ReceiverThrowableType;
}
