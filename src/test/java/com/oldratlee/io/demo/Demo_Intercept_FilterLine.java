package com.oldratlee.io.demo;

import java.io.File;
import java.io.IOException;

import com.oldratlee.io.core.Input;
import com.oldratlee.io.core.Output;
import com.oldratlee.io.core.filter.Filters;
import com.oldratlee.io.core.filter.Specification;
import com.oldratlee.io.utils.Inputs;
import com.oldratlee.io.utils.Outputs;

/**
 * @author oldratlee
 */
public class Demo_Intercept_FilterLine {
    public static void main(String[] args) throws IOException {
        File source = new File("in.txt");
        File destination = new File("out.tmp");

        Input<String, IOException> input = Inputs.text(source);
        
        Output<String, IOException> output = Outputs.text(destination);
        
        Specification<String> specification = new Specification<String>() {
            public boolean test(String item) {
                if(item.trim().length() == 0) return false; // 过滤空行
                return true;
            }
        };

        input.transferTo(Filters.filter(specification, output));
    }
}
