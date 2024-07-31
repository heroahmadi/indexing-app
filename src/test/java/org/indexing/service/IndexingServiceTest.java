package org.indexing.service;

import org.indexing.model.IndexingOutput;
import org.indexing.model.IndexingRuleType;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public class IndexingServiceTest {

    @Test
    public void testWithSmallFile() throws ExecutionException, InterruptedException, IOException {
        List<String> fileNames = asList("input.txt");
        IndexingService service = new IndexingService();
        List<IndexingOutput> expected = asList(
                new IndexingOutput("Test", IndexingRuleType.UPPERCASE),
                new IndexingOutput("<qe23oasfasjk", IndexingRuleType.MINIMUM_CHAR),
                new IndexingOutput("Asa", IndexingRuleType.UPPERCASE),
                new IndexingOutput("qeqioroi2u12321", IndexingRuleType.MINIMUM_CHAR),
                new IndexingOutput("Aq", IndexingRuleType.UPPERCASE));
        List<IndexingOutput> output = service.run(fileNames);
        assertNotNull(output);
        expected.sort(Comparator.comparing(IndexingOutput::word));
        output.sort(Comparator.comparing(IndexingOutput::word));

        assertEquals(expected, output);
    }

    @Test
    public void testFileNotFound() {
        List<String> fileNames = asList("asd.txt");
        IndexingService service = new IndexingService();
        assertThrowsExactly(FileNotFoundException.class, () -> service.run(fileNames));
    }

    @Test
    public void testWithLargeFile() throws ExecutionException, InterruptedException, IOException {
        int numFiles = 3;
        List<String> fileNames = new ArrayList<>();
        for (int i=1; i<=numFiles; i++) {
            fileNames.add("test_file" + i + ".txt");
        }
        IndexingService service = new IndexingService();
        long start = System.currentTimeMillis();
        List<IndexingOutput> output = service.run(fileNames);
        long end = System.currentTimeMillis();
        System.out.println("Finished. Duration: " + (end - start) + " millis");
        assertNotNull(output);
    }

}
