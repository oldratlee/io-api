package com.oldratlee.io.core.filter;

import com.oldratlee.io.core.Output;
import com.oldratlee.io.core.Receiver;
import com.oldratlee.io.core.Sender;

/**
 * @author oldratlee
 */
public class Filters {
    static class SpecificationOutputWrapper<T, ReceiverThrowableType extends Throwable>
            implements Output<T, ReceiverThrowableType> {
        
        final Output<T, ? extends ReceiverThrowableType> output;
        final Specification<? super T> specification;
        
        public SpecificationOutputWrapper(Output<T, ? extends ReceiverThrowableType> output, Specification<? super T> specification) {
            this.output = output;
            this.specification = specification;
        }
        
        @Override
        public <SenderThrowableType extends Throwable> void receiveFrom(Sender<T, SenderThrowableType> sender)
                throws ReceiverThrowableType, SenderThrowableType {
            output.receiveFrom(new SpecificationSenderWrapper<>(sender, specification));
        }
    }

    static class SpecificationSenderWrapper<T, SenderThrowableType extends Throwable>
            implements Sender<T, SenderThrowableType> {
        
        final Sender<? extends T, ? extends SenderThrowableType> sender;
        final Specification<? super T> specification;
        
        public SpecificationSenderWrapper(Sender<? extends T, ? extends SenderThrowableType> sender, Specification<? super T> specification) {
            this.sender = sender;
            this.specification = specification;
        }
        
        @Override
        public <ReceiverThrowableType extends Throwable> void sendTo(Receiver<? super T, ReceiverThrowableType> receiver)
                throws ReceiverThrowableType, SenderThrowableType {
            sender.sendTo(new SpecificationReceiverWrapper<T, ReceiverThrowableType>(receiver, specification));
        }
    }

    static class SpecificationReceiverWrapper<T, ReceiverThrowableType extends Throwable>
            implements Receiver<T, ReceiverThrowableType> {
        
        final Receiver<? super T, ? extends ReceiverThrowableType> receiver;
        final Specification<? super T> specification;
        
        public SpecificationReceiverWrapper(Receiver<? super T, ? extends ReceiverThrowableType> receiver, Specification<? super T> specification) {
            this.receiver = receiver;
            this.specification = specification;
        }
        
        @Override
        public void receive(T item) throws ReceiverThrowableType {
            if(specification.test(item)) {
                receiver.receive(item);
            }
        }

        @Override
        public void finished() throws ReceiverThrowableType {
            receiver.finished();
        }
    }

    public static <T, ReceiverThrowableType extends Throwable>
    Output<T, ReceiverThrowableType> filter(Specification<? super T> specification, final Output<T, ? extends ReceiverThrowableType> output) {
       return new SpecificationOutputWrapper<>(output, specification);
    }
    

    static class FunctionOutputWrapper<From, To, ReceiverThrowableType extends Throwable>
            implements Output<From, ReceiverThrowableType> {
        
        final Output<? super To, ? extends ReceiverThrowableType> output;
        final Function<? super From, ? extends To> function;
        
        public FunctionOutputWrapper(Output<? super To, ? extends ReceiverThrowableType> output, Function<? super From, ? extends To> function) {
            this.output = output;
            this.function = function;
        }

        @Override
        public <SenderThrowableType extends Throwable> void receiveFrom(Sender<From, SenderThrowableType> sender)
                throws ReceiverThrowableType, SenderThrowableType {
            output.receiveFrom(new FunctionSenderWrapper<>(sender, function));
        }
    }
    
    static class FunctionSenderWrapper<From, To, SenderThrowableType extends Throwable> implements Sender<To, SenderThrowableType> {
        final Sender<? extends From, ? extends SenderThrowableType> sender;
        final Function<? super From, ? extends To> function;
        
        public FunctionSenderWrapper(Sender<? extends From, ? extends SenderThrowableType> sender, Function<? super From, ? extends To> function) {
            this.sender = sender;
            this.function = function;
        }

        @Override
        public <ReceiverThrowableType extends Throwable> void sendTo(Receiver<? super To, ReceiverThrowableType> receiver)
                throws ReceiverThrowableType, SenderThrowableType {
            sender.sendTo(new FunctionReceiverWrapper<From, To, ReceiverThrowableType>(receiver, function));
        }
    }
    
    static class FunctionReceiverWrapper<From, To, ReceiverThrowableType extends Throwable>
            implements Receiver<From, ReceiverThrowableType> {
        
        final Receiver<? super To, ? extends ReceiverThrowableType> receiver;
        final Function<? super From, ? extends To> function;
        
        public FunctionReceiverWrapper(Receiver<? super To, ? extends ReceiverThrowableType> receiver, Function<? super From, ? extends To> function) {
            this.receiver = receiver;
            this.function = function;
        }
        
        @Override
        public void receive(From item) throws ReceiverThrowableType {
            receiver.receive(function.map(item));
        }

        @Override
        public void finished() throws ReceiverThrowableType {
            receiver.finished();
        }
    }
    
    public static <From, To, ReceiverThrowableType extends Throwable>
    Output<From, ReceiverThrowableType> filter(Function<? super From, ? extends To> function, final Output<To, ? extends ReceiverThrowableType> output) {
        return new FunctionOutputWrapper<>(output, function);
     }

    private Filters() {}
}
