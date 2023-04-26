package com.oldratlee.io.utils;

import com.oldratlee.io.core.Input;
import com.oldratlee.io.core.Output;
import com.oldratlee.io.core.Receiver;
import com.oldratlee.io.core.Sender;

import java.io.*;

/**
 * Utils of {@link Input}.
 *
 * @author oldratlee
 */
public class Inputs {

    static class TextInput implements Input<String, IOException> {
        private final Reader reader;

        public TextInput(File source) throws IOException {
            reader = new FileReader(source);
        }

        @Override
        public <ReceiverThrowableType extends Throwable>
        void transferTo(Output<String, ReceiverThrowableType> output) throws IOException, ReceiverThrowableType {
            final TextSender sender = new TextSender(reader);
            output.receiveFrom(sender);

            try {
                reader.close();
            } catch (Exception e) {
                // ignore close exception :)
            }
        }

    }

    static class TextSender implements Sender<String, IOException> {
        private final BufferedReader bufferReader;

        public TextSender(Reader reader) {
            this.bufferReader = new BufferedReader(reader);
        }

        @Override
        public <ReceiverThrowableType extends Throwable>
        void sendTo(Receiver<? super String, ReceiverThrowableType> receiver)
                throws ReceiverThrowableType, IOException {
            String readLine;
            while ((readLine = bufferReader.readLine()) != null) {
                receiver.receive(readLine + "\n");
            }
        }
    }

    public static Input<String, IOException> text(File source) throws IOException {
        return new TextInput(source);
    }

    private Inputs() {
    }
}
