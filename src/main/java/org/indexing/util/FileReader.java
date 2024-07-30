package org.indexing.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static List<File> read(List<String> fileNames) {
        List<File> files = new ArrayList<>();
        for (String fileName: fileNames) {
            files.add(read(fileName));
        }
        return files;
    }

    public static File read(String fileName) {
        File file = new File(fileName);
        return file;
    }

}
