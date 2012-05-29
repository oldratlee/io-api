package com.oldratlee.io.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.oldratlee.io.core.Input;
import com.oldratlee.io.core.Output;
import com.oldratlee.io.core.Receiver;
import com.oldratlee.io.core.Sender;

/**
 * Factory Utils of {@link Input}.
 * 
 * @author oldratlee
 */
public class Inputs {
    
    static class TextInput implements Input<String, IOException> {
        final File source;
        final BufferedReader reader;
        final TextSender sender; 
        
        public TextInput(File source) throws IOException {
            this.source = source;
            reader = new BufferedReader(new FileReader(source));
            sender = new TextSender(reader);
        }
        
        public <ReceiverThrowableType extends Throwable> void transferTo(Output<String, ReceiverThrowableType> output)
                throws IOException, ReceiverThrowableType {
            output.receiveFrom(sender);
            
            try {
                reader.close();
            } catch (Exception e) {
                // ignore close exception :)
            }
        }
        
    }
    
    static class TextSender implements Sender<String, IOException> {
        final BufferedReader reader;
        
        public TextSender(BufferedReader reader) throws FileNotFoundException {
            this.reader = reader;
        }

        public <ReceiverThrowableType extends Throwable> void sendTo(Receiver<String, ReceiverThrowableType> receiver)
                throws ReceiverThrowableType, IOException {
            String readLine;
            while((readLine = reader.readLine()) != null) {
                receiver.receive(readLine + "\n");
            }
        }
    }
    
    public static Input<String, IOException> text(File source) throws IOException {
        return new TextInput(source);
    }
}
