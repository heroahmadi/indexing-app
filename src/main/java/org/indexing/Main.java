package org.indexing;

import org.indexing.service.IndexingService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static java.util.Arrays.asList;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        try {
            IndexingService service = new IndexingService();
            long startMillis = System.currentTimeMillis();
            service.run(asList(args));
            long endMillis = System.currentTimeMillis();
            float runtimeSeconds = (float) (endMillis - startMillis) / 1000;
            System.out.printf("Runtime in seconds: %f\nFinished successfully. Check the output file in the output_***.txt\n",runtimeSeconds);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}