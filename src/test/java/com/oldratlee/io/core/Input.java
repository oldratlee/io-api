package com.oldratlee.io.core;

public interface Input<T, SenderThrowableType extends Throwable> {
    <ReceiverThrowableType extends Throwable>
    void transferTo(Output<T, ReceiverThrowableType> output)
            throws SenderThrowableType, ReceiverThrowableType;
}
