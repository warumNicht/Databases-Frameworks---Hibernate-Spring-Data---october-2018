package hiberspring.util;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileUtil {
    String read(String filePath) throws IOException;
    void write (String content,String filePath) throws IOException;
}