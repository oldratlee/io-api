package com.oldratlee.io.demo;

import com.oldratlee.io.core.Input;
import com.oldratlee.io.core.Output;
import com.oldratlee.io.core.filter.Filters;
import com.oldratlee.io.core.filter.Specification;
import com.oldratlee.io.utils.Inputs;
import com.oldratlee.io.utils.Outputs;

import java.io.File;
import java.io.IOException;

/**
 * @author oldratlee
 */
public class Demo_Intercept_FilterLine {
    public static void main(String[] args) throws IOException {
        File source = new File("in.txt");
        File destination = new File("out.tmp");

        Input<String, IOException> input = Inputs.text(source);

        Output<String, IOException> output = Outputs.text(destination);

        // 过滤空行
        Specification<String> specification = item -> !item.trim().isEmpty();

        input.transferTo(Filters.filter(specification, output));
    }
}
