package org.indexing.service;

import org.indexing.util.FileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

import static java.util.Arrays.asList;

public class IndexingService {

    public List<String> run(List<String> fileNames) throws InterruptedException, ExecutionException {
        List<File> files = FileReader.read(fileNames);
        int numThread = Math.max(files.size(), 10);
        ExecutorService executorService = Executors.newFixedThreadPool(numThread);
        CompletionService<List<String>> service = new ExecutorCompletionService<>(executorService);
        for (File file: files) {
            Callable<List<String>> task = createTask(file);
            service.submit(task);
        }

        List<String> output = new ArrayList<>();
        for (int i=0; i < files.size(); i++) {
            Future<List<String>> result = service.take();
            output.addAll(result.get());
        }
        executorService.shutdown();

        return output;
    }

    private static Callable<List<String>> createTask(File file) {
        return () -> runIndexing(file);
    }

    private static List<String> runIndexing(File file) {
        List<String> output = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(" +|(\r?\n)+");

            while (scanner.hasNext()) {
                String word = scanner.next();
                List<IndexingRule> indexingRules = asList(new MinimumCharacterRule(), new UppercaseRule());
                for (IndexingRule indexingRule: indexingRules) {
                    if (indexingRule.isTrue(word)) {
                        System.out.println(word);
                        output.add(word);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return output;
    }
}
