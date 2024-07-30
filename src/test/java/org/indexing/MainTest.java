package org.indexing;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

public class MainTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        String[] args = {"input.txt"};
        Main.main(args);
    }

}
