package com.oldratlee.io.core;

/**
 * @param <T>                   data type
 * @param <SenderThrowableType> Exception when send data
 */
@FunctionalInterface
public interface Sender<T, SenderThrowableType extends Throwable> {
    /**
     * @param <ReceiverThrowableType> Exception when receive data
     */
    <ReceiverThrowableType extends Throwable>
    void sendTo(Receiver<? super T, ReceiverThrowableType> receiver)
            throws ReceiverThrowableType, SenderThrowableType;
}
