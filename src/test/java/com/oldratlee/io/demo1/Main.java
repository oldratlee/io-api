package com.oldratlee.io.demo1;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File source = new File("in");
        File destination = new File("out");
        Inputs.text(source).transferTo(Outputs.text(destination));
    }
}
