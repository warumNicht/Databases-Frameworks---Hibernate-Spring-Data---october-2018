package pb2_carsDealer.io;

import java.io.IOException;

public interface FileIOUtil {
    String read(String fileName) throws IOException;

    void write(String fileName, String content) throws IOException;

}
