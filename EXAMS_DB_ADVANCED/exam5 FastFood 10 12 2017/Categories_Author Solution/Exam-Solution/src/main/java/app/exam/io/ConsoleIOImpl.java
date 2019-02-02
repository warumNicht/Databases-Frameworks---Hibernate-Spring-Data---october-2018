package app.exam.io;

import org.springframework.stereotype.Component;
import app.exam.io.interfaces.ConsoleIO;

@Component
public class ConsoleIOImpl implements ConsoleIO {
    @Override
    public void write(String line) {
        System.out.println(line);
    }
}
