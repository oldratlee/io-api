package com.oldratlee.io.demo1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.oldratlee.io.api.Input;
import com.oldratlee.io.api.Output;
import com.oldratlee.io.api.Receiver;
import com.oldratlee.io.api.Sender;

public class Inputs {
    
    static class TextInput implements Input<String, IOException> {
        final File source;
        final TextSender sender; 
        
        public TextInput(File source) throws IOException {
            this.source = source;
            sender = new TextSender(source);
        }
        
        @Override
        public <ReceiverThrowableType extends Throwable> void transferTo(Output<String, ReceiverThrowableType> output)
                throws IOException, ReceiverThrowableType {
            output.receiveFrom(sender);
        }
        
    }
    
    static class TextSender implements Sender<String, IOException> {
        final BufferedReader reader;
        
        public TextSender(File source) throws FileNotFoundException {
            this.reader = new BufferedReader(new FileReader(source));
        }

        @Override
        public <ReceiverThrowableType extends Throwable> void sendTo(Receiver<String, ReceiverThrowableType> receiver)
                throws ReceiverThrowableType, IOException {
            String readLine;
            while((readLine = reader.readLine()) != null) {
                receiver.receive(readLine);
            }
        }
    }
    
    public static Input<String, IOException> text(File source) throws IOException {
        return new TextInput(source);
    }
}
