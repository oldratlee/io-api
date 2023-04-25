package com.oldratlee.io.core;

/**
 * @param <T>                     data type
 * @param <ReceiverThrowableType> Exception when receive data
 */
public interface Receiver<T, ReceiverThrowableType extends Throwable> {
    void receive(T item) throws ReceiverThrowableType;

    void finished() throws ReceiverThrowableType;
}
