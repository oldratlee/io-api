package com.oldratlee.io.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.oldratlee.io.core.Output;
import com.oldratlee.io.core.Receiver;
import com.oldratlee.io.core.Sender;

/**
 * Factory Utils of {@link Output}.
 * 
 * @author oldratlee
 */
public class Outputs {
    static class TextOutput implements Output<String, IOException> {
        final File destination;
        final Writer writer;
        final TextFileReceiver receiver;

        public TextOutput(File destination) throws IOException {
            this.destination = destination;
            writer = new FileWriter(destination);
            receiver = new TextFileReceiver(writer);
        }

        public <SenderThrowableType extends Throwable> void receiveFrom(Sender<String, SenderThrowableType> sender)
                throws IOException, SenderThrowableType {
            sender.sendTo(receiver);
            receiver.close();
            try {
                writer.close();
            } catch (Exception e) {
                // ignore writer exception!
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

        public void close() throws IOException {
        }
    }

    public static Output<String, IOException> text(File destination) throws IOException {
        return new TextOutput(destination);
    }
}
