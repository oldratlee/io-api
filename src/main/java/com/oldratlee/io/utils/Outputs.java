package com.oldratlee.io.utils;

import com.oldratlee.io.core.Output;
import com.oldratlee.io.core.Receiver;
import com.oldratlee.io.core.Sender;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Utils of {@link Output}.
 *
 * @author oldratlee
 */
public class Outputs {
    static class TextOutput implements Output<String, IOException> {
        private final Writer writer;

        public TextOutput(File destination) throws IOException {
            writer = new FileWriter(destination);
        }

        @Override
        public <SenderThrowableType extends Throwable> void receiveFrom(Sender<String, SenderThrowableType> sender)
                throws IOException, SenderThrowableType {
            final TextFileReceiver receiver = new TextFileReceiver(writer);
            sender.sendTo(receiver);
            receiver.finished();

            try {
                writer.close();
            } catch (Exception e) {
                // ignore close exception
            }
        }
    }

    static class TextFileReceiver implements
            Receiver<String, IOException> {
        final Writer writer;

        public TextFileReceiver(Writer writer) {
            this.writer = writer;
        }

        @Override
        public void receive(String item) throws IOException {
            writer.write(item);
        }

        @Override
        public void finished() {
        }
    }

    public static Output<String, IOException> text(File destination) throws IOException {
        return new TextOutput(destination);
    }

    private Outputs() {
    }
}
