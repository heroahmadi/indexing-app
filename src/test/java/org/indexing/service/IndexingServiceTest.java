package org.indexing.service;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IndexingServiceTest {

    @Test
    public void testWithSmallFile() throws ExecutionException, InterruptedException {
        List<String> fileNames = asList("input.txt");
        IndexingService service = new IndexingService();
        List<String> expected = asList("<qe23oasfasjk", "Asa", "qeqioroi2u12321", "Aq");
        List<String> output = service.run(fileNames);
        assertNotNull(output);
        expected.sort(Comparator.naturalOrder());
        output.sort(Comparator.naturalOrder());

        assertEquals(expected, output);
    }

    @Test
    public void testWithLargeFile() throws ExecutionException, InterruptedException {
        List<String> fileNames = asList("test_file1.txt", "test_file2.txt");
        IndexingService service = new IndexingService();
        List<String> output = service.run(fileNames);
        assertNotNull(output);
    }

}
