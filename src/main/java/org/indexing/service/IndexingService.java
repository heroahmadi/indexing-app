package org.indexing.service;

import org.indexing.model.IndexingOutput;
import org.indexing.util.FileReader;
import org.indexing.util.OutputWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.util.Arrays.asList;

public class IndexingService {
    private final List<IndexingRule> INDEXING_RULES = asList(
            new MinimumCharacterRule(),
            new UppercaseRule());

    public List<IndexingOutput> run(List<String> fileNames) throws InterruptedException, ExecutionException, FileNotFoundException {
        List<File> files = FileReader.read(fileNames);
        int numThread = Math.min(files.size(), 10);
        ExecutorService executorService = Executors.newFixedThreadPool(numThread);
        CompletionService<List<IndexingOutput>> service = new ExecutorCompletionService<>(executorService);
        createTasks(files, service);
        List<IndexingOutput> output = getTasksOutput(files, service);

        executorService.shutdown();

        return output;
    }

    private List<IndexingOutput> getTasksOutput(List<File> files, CompletionService<List<IndexingOutput>> service) throws InterruptedException, ExecutionException {
        List<IndexingOutput> output = new ArrayList<>();
        for (int i=0; i < files.size(); i++) {
            Future<List<IndexingOutput>> result = service.take();
            output.addAll(result.get());
        }
        return output;
    }

    private void createTasks(List<File> files, CompletionService<List<IndexingOutput>> service) {
        for (File file: files) {
            Callable<List<IndexingOutput>> task = () -> runIndexing(file);
            service.submit(task);
        }
    }

    private List<IndexingOutput> runIndexing(File file) {
        List<IndexingOutput> output = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" +|(\r?\n)+");
                for (String word: words) {
                    for (IndexingRule indexingRule: INDEXING_RULES) {
                        if (indexingRule.isMeetCriteria(word)) {
                            output.add(indexingRule.getOutput(word));
                        }
                    }
                }
            }
            OutputWriter.printOutput(file.getName(), output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return output;
    }
}
