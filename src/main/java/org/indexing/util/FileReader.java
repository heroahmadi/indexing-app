package org.indexing.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public static List<File> read(List<String> fileNames) throws FileNotFoundException {
        List<File> files = new ArrayList<>();
        for (String fileName: fileNames) {
            File file = new File(fileName);
            validateFileExists(file);
            files.add(file);
        }
        return files;
    }

    private static void validateFileExists(File file) throws FileNotFoundException {
        if (!file.exists()) {
            String errorMessage = "File '" + file.getName() + "' does not exists.";
            throw new FileNotFoundException(errorMessage);
        }
    }

}
