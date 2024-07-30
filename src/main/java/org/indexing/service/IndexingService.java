package org.indexing.service;

import org.indexing.rule.IndexingRule;
import org.indexing.rule.MinimumCharacterRule;
import org.indexing.rule.UppercaseRule;
import org.indexing.util.FileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.util.Arrays.asList;

public class IndexingService {
    private final List<IndexingRule> INDEXING_RULES = asList(
            new MinimumCharacterRule(),
            new UppercaseRule());

    public List<String> run(List<String> fileNames) throws InterruptedException, ExecutionException {
        List<File> files = FileReader.read(fileNames);
        int numThread = Math.min(files.size(), 10);
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

    private Callable<List<String>> createTask(File file) {
        return () -> runIndexing(file);
    }

    private List<String> runIndexing(File file) {
        List<String> output = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" +|(\r?\n)+");
                for (String word: words) {
                    for (IndexingRule indexingRule: INDEXING_RULES) {
                        if (indexingRule.isTrue(word)) {
                            System.out.println(word);
                            output.add(word);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return output;
    }
}
