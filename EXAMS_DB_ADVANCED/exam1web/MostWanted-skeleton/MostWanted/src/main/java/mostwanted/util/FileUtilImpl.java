package mostwanted.util;

import java.io.*;

public class FileUtilImpl implements FileUtil {
    @Override
    public String readFile(String filePath) throws IOException {
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
}
