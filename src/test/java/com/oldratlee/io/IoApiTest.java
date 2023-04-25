package com.oldratlee.io;

import com.oldratlee.io.demo.Demo_FileTransport;
import com.oldratlee.io.demo.Demo_Intercept_CountLine;
import com.oldratlee.io.demo.Demo_Intercept_FilterLine;
import com.oldratlee.io.demo.Demo_PersonToFileTransport;
import org.junit.jupiter.api.Test;

class IoApiTest {
    @Test
    void helloWorld() throws Exception {
        Demo_FileTransport.main(new String[0]);
        Demo_PersonToFileTransport.main(new String[0]);

        Demo_Intercept_CountLine.main(new String[0]);
        Demo_Intercept_FilterLine.main(new String[0]);
    }
}
