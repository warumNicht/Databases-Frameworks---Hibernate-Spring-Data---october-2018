package com.example.demo.pb2UserSystem.util;

import java.io.IOException;
import java.util.List;

public interface FileUtil {
    List<String> getFileContent(String filePath) throws IOException;
}
