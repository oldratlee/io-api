package com.oldratlee.io.core;

/**
 * Output.
 *
 * @param <T>                     data type
 * @param <ReceiverThrowableType> Exception when receive data
 */
@FunctionalInterface
public interface Output<T, ReceiverThrowableType extends Throwable> {
    <SenderThrowableType extends Throwable>
    void receiveFrom(Sender<T, SenderThrowableType> sender) throws ReceiverThrowableType, SenderThrowableType;
}
