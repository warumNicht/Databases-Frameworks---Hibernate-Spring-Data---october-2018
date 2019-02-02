package photography.util;

import java.io.*;

public class FileUtilImpl implements FileUtil {
    @Override
    public String read(String filePath) throws IOException {
        File file=new File(filePath);
        BufferedReader reader;
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuilder res=new StringBuilder();
        String line;
        while ((line=reader.readLine())!=null){
            res.append(line).append(System.lineSeparator());
        }
        return res.toString().trim();
    }

    @Override
    public void write(String content, String filePath) throws IOException {
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath))));
            writer.write(content);
            writer.close();
    }
}
