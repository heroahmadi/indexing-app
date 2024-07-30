package org.indexing;

import org.indexing.service.IndexingService;

import java.util.concurrent.ExecutionException;

import static java.util.Arrays.asList;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        IndexingService service = new IndexingService();
        service.run(asList(args));
    }

}