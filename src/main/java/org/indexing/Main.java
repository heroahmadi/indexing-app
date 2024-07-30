package org.indexing;

import org.indexing.service.IndexingService;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.Arrays.asList;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        try {
            IndexingService service = new IndexingService();
            List<String> output = service.run(asList(args));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            for (String str : output) {
                writer.write(str + System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}