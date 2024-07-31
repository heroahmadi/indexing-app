package org.indexing.util;

import org.indexing.model.IndexingOutput;
import org.indexing.model.IndexingRuleType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OutputWriter {

    public static void printOutput(String inputFileName, List<IndexingOutput> indexingOutputs) throws IOException {
        int totalWordsStartingWithUppercase = 0;
        List<String> wordsLongerThan5Chars = new ArrayList<>();
        for (IndexingOutput indexingOutput: indexingOutputs) {
            if (IndexingRuleType.UPPERCASE.equals(indexingOutput.indexingRuleType())) {
                totalWordsStartingWithUppercase++;
            } else {
                wordsLongerThan5Chars.add(indexingOutput.word());
            }
        }

        String output = "File name: " + inputFileName +
                "\nNumber of words starting with uppercase letter: " + totalWordsStartingWithUppercase +
                "\nList of words longer than 5 chars: " + String.join("\n", wordsLongerThan5Chars) +
                "\n===================================\n";
        writeToFile(inputFileName, output);
    }

    private static void writeToFile(String inputFileName, String output) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("output_" + inputFileName));
        writer.write(output);
        writer.close();
    }

}
