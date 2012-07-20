package com.oldratlee.io.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.oldratlee.io.core.Output;
import com.oldratlee.io.core.Receiver;
import com.oldratlee.io.core.Sender;

/**
 * Utils of {@link Output}.
 * 
 * @author oldratlee
 */
public class Outputs {
    static class TextOutput implements Output<String, IOException> {
        final File destination;
        final Writer writer;

        public TextOutput(File destination) throws IOException {
            this.destination = destination;
            writer = new FileWriter(destination);

        }

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

        public TextFileReceiver(Writer writer) throws IOException {
            this.writer = writer;
        }

        public void receive(String item) throws IOException {
            writer.write(item);
        }

        public void finished() throws IOException {
        }
    }

    public static Output<String, IOException> text(File destination) throws IOException {
        return new TextOutput(destination);
    }

    private Outputs() {}
}
