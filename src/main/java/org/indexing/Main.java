package org.indexing;

import org.indexing.service.IndexingService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.Arrays.asList;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        IndexingService service = new IndexingService();
        List<String> output = service.run(asList(args));
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            for (String str : output) {
                writer.write(str + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}