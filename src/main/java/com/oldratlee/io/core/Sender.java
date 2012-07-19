package com.oldratlee.io.core;

/**
 * @param <T> data type
 * @param <SenderThrowableType> Exception when send data
 */
public interface Sender<T, SenderThrowableType extends Throwable> {
    /**
     * @param receiver
     * @param <ReceiverThrowableType> Exception when receive data
     * @throws ReceiverThrowableType
     * @throws SenderThrowableType
     */
    <ReceiverThrowableType extends Throwable>
    void sendTo(Receiver<T, ReceiverThrowableType> receiver)
            throws ReceiverThrowableType, SenderThrowableType;
}
