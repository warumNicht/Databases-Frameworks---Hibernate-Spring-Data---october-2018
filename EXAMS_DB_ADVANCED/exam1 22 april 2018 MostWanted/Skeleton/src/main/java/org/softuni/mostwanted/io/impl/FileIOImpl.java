package org.softuni.mostwanted.io.impl;

import org.softuni.mostwanted.io.interfaces.FileIO;

import java.io.*;

public class FileIOImpl implements FileIO {
    @Override
    public String read(String file) throws IOException {
        //TODO: Implement me ...
        InputStream stream = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder res = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            res.append(line);
        }
        return res.toString();

    }

    @Override
    public void write(String fileContent, String file) throws IOException {
        //TODO: Implement me ...
        FileWriter fileWriter=new FileWriter(file);
        fileWriter.write(fileContent);
        fileWriter.close();
    }
}
