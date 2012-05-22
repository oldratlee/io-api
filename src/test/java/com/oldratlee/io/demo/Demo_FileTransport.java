package com.oldratlee.io.demo;

import java.io.File;
import java.io.IOException;


/**
 * @author oldratlee
 */
public class Demo_FileTransport {
    public static void main(String[] args) throws IOException {
        File source = new File("in");
        File destination = new File("out");
        
        Inputs.text(source).transferTo(Outputs.text(destination));
    }
}
