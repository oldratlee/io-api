package com.oldratlee.io.core;

/**
 * Input.
 *
 * @param <T> data type
 * @param <SenderThrowableType> Exception when send data
 */
public interface Input<T, SenderThrowableType extends Throwable> {
    <ReceiverThrowableType extends Throwable>
    void transferTo(Output<T, ReceiverThrowableType> output)
            throws SenderThrowableType, ReceiverThrowableType;
}
