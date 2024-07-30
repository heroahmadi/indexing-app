package org.indexing.service;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IndexingServiceTest {

    @Test
    public void testWithSmallFile() throws ExecutionException, InterruptedException, IOException {
        List<String> fileNames = asList("input.txt");
        IndexingService service = new IndexingService();
        List<String> expected = asList("Test", "<qe23oasfasjk", "Asa", "qeqioroi2u12321", "Aq");
        List<String> output = service.run(fileNames);
        assertNotNull(output);
        expected.sort(Comparator.naturalOrder());
        output.sort(Comparator.naturalOrder());

        assertEquals(expected, output);
    }

    @Test
    public void testWithLargeFile() throws ExecutionException, InterruptedException, IOException {
        int numFiles = 15;
        List<String> fileNames = new ArrayList<>();
        for (int i=1; i<=numFiles; i++) {
            fileNames.add("test_file" + i + ".txt");
        }
        IndexingService service = new IndexingService();
        long start = System.currentTimeMillis();
        List<String> output = service.run(fileNames);
        long end = System.currentTimeMillis();
        System.out.println("Finished. Duration: " + (end - start) + " millis");
        assertNotNull(output);

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        for (String str : output) {
            writer.write(str + System.lineSeparator());
        }
    }

}
