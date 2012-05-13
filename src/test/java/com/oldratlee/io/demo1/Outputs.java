package com.oldratlee.io.demo1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.oldratlee.io.api.Output;
import com.oldratlee.io.api.Receiver;
import com.oldratlee.io.api.Sender;

public class Outputs {
    static class TextOutput implements Output<String, IOException> {
        final File destination;
        
        final TextFileReceiver receiver;

        public TextOutput(File destination) throws IOException {
            this.destination = destination;
            receiver = new TextFileReceiver(destination);
        }

        @Override
        public <SenderThrowableType extends Throwable> void receiveFrom(Sender<String, SenderThrowableType> sender)
                throws IOException, SenderThrowableType {
            sender.sendTo(receiver);
            receiver.close();
        }

    }

    static class TextFileReceiver implements
            Receiver<String, IOException> {
        final Writer writer;

        public TextFileReceiver(File destination) throws IOException {
            writer = new FileWriter(destination);
        }

        @Override
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
