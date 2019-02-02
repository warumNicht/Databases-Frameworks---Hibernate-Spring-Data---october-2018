package com.example.demo.pb2UserSystem.util;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUtilImpl implements FileUtil {
    @Override
    public List<String> getFileContent(String filePath) throws IOException {

        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        List<String> lines = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null) {
            if(!line.equals("")){
                lines.add(line);
            }
        }
        return lines;
    }
}
