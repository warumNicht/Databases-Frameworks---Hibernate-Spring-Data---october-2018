package app.exam.io.interfaces;

import java.io.*;

public class FileUtilImpl implements FileIO {
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
    public void write(String fileContent, String file) throws IOException {
        BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(file))));
        writer.write(fileContent);
        writer.close();
    }
}
