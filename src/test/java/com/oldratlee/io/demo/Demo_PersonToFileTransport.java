package com.oldratlee.io.demo;

import com.oldratlee.io.core.Input;
import com.oldratlee.io.core.Output;
import com.oldratlee.io.core.Receiver;
import com.oldratlee.io.core.Sender;
import com.oldratlee.io.utils.Outputs;

import java.io.File;
import java.io.IOException;

/**
 * @author ding.lid
 */
public class Demo_PersonToFileTransport {
    static class Person {
        private String name;
        private int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    static class PersonTextInput implements Input<String, IOException> {
        Person person;

        PersonTextInput(Person person) {
            this.person = person;
        }

        public <ReceiverThrowableType extends Throwable> void transferTo(Output<String, ReceiverThrowableType> output)
                throws IOException, ReceiverThrowableType {
            final PersonTextSender sender = new PersonTextSender(person);
            output.receiveFrom(sender);
        }

    }

    static class PersonTextSender implements Sender<String, IOException> {
        private static final String LINE_SEPARATOR = System.getProperty("line.separator");

        private Person person;

        PersonTextSender(Person person) {
            this.person = person;
        }

        public <ReceiverThrowableType extends Throwable> void sendTo(Receiver<String, ReceiverThrowableType> receiver)
                throws ReceiverThrowableType, IOException {
            receiver.receive("name: " + person.name + LINE_SEPARATOR);
            receiver.receive("age: " + person.age + LINE_SEPARATOR);
        }
    }

    public static void main(String[] args) throws Exception {
        Person person = new Person("Jerry", 42);
        File destination = new File("out.tmp");

        new PersonTextInput(person).transferTo(Outputs.text(destination));
    }
}
