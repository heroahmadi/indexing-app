package org.indexing;

import org.indexing.util.FileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<File> files = FileReader.read(Arrays.asList(args));
        int numThread = Math.max(files.size(), 10);
        ExecutorService executorService = Executors.newFixedThreadPool(numThread);
        CompletionService<Object> service = new ExecutorCompletionService<>(executorService);
        for (File file: files) {
            Callable<Object> task = createTask(file);
            service.submit(task);
        }

        for (int i=0; i < files.size(); i++) {
            Future<Object> result = service.take();
            result.get();
        }
    }

    private static Callable<Object> createTask(File file) {
        return () -> runIndexing(file);
    }

    private static Object runIndexing(File file) {
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(" +");

            while (scanner.hasNext()) {
                System.out.println(scanner.next());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}