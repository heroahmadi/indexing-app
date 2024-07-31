package org.indexing.service;

import org.indexing.model.IndexingResult;
import org.indexing.util.FileReader;
import org.indexing.util.OutputWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.indexing.config.IndexingRuleConfig.ACTIVE_INDEXING_RULES;

public class IndexingService {

    public List<IndexingResult> run(List<String> fileNames) throws InterruptedException, ExecutionException, FileNotFoundException {
        List<File> files = FileReader.read(fileNames);
        int numThread = Math.min(files.size(), 10);
        ExecutorService executorService = Executors.newFixedThreadPool(numThread);
        CompletionService<List<IndexingResult>> service = new ExecutorCompletionService<>(executorService);
        createTasks(files, service);
        List<IndexingResult> results = getTasksOutput(files, service);

        executorService.shutdown();

        return results;
    }

    private List<IndexingResult> getTasksOutput(List<File> files, CompletionService<List<IndexingResult>> service) throws InterruptedException, ExecutionException {
        List<IndexingResult> output = new ArrayList<>();
        for (int i=0; i < files.size(); i++) {
            Future<List<IndexingResult>> result = service.take();
            output.addAll(result.get());
        }
        return output;
    }

    private void createTasks(List<File> files, CompletionService<List<IndexingResult>> service) {
        for (File file: files) {
            Callable<List<IndexingResult>> task = () -> runIndexing(file);
            service.submit(task);
        }
    }

    private List<IndexingResult> runIndexing(File file) {
        List<IndexingResult> indexingResults = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" +|(\r?\n)+");
                for (String word: words) {
                    for (IndexingRule indexingRule: ACTIVE_INDEXING_RULES) {
                        if (indexingRule.isMeetCriteria(word)) {
                            indexingResults.add(indexingRule.getIndexingResult(word));
                        }
                    }
                }
            }
            OutputWriter.printOutput(file.getName(), indexingResults);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return indexingResults;
    }
}
