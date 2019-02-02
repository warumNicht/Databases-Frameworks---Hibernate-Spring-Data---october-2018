package pb2_carsDealer.io;


import java.io.*;

public class FileIOUtilImpl implements FileIOUtil {
    @Override
    public String read(String fileName) throws IOException {
        InputStream stream = new FileInputStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder res = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            res.append(line);
        }
        return res.toString();
    }

    @Override
    public void write(String fileName, String content) throws IOException {
        FileWriter fileWriter=new FileWriter(fileName);
        fileWriter.write(content);
        fileWriter.close();
    }
}
