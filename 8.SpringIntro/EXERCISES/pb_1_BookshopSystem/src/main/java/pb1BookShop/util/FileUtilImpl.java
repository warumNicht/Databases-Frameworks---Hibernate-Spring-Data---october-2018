package pb1BookShop.util;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUtilImpl implements FileUtil {
    @Override
    public String[] getFileContent(String filePath) throws IOException {

        File file=new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines=new ArrayList<>();
        String line;

        while ((line=reader.readLine())!=null){
            lines.add(line);
        }
        String[] objects =  lines.stream().filter(x -> !x.equals(""))
                .toArray(String[]::new);
        return objects;
    }
}
