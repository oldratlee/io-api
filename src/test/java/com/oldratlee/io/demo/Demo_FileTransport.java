package com.oldratlee.io.demo;

import java.io.File;
import java.io.IOException;

import com.oldratlee.io.utils.Inputs;
import com.oldratlee.io.utils.Outputs;


/**
 * @author oldratlee
 */
public class Demo_FileTransport {
    public static void main(String[] args) throws IOException {
        File source = new File("in.txt");
        File destination = new File("out.tmp");
        
        Inputs.text(source).transferTo(Outputs.text(destination));
    }
}
