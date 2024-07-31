package org.indexing;

import org.indexing.model.IndexingOutput;
import org.indexing.service.IndexingService;
import org.indexing.util.OutputWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.Arrays.asList;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        try {
            IndexingService service = new IndexingService();
            List<IndexingOutput> output = service.run(asList(args));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}