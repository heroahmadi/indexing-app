package org.indexing.util;

import org.indexing.config.IndexingRuleConfig;
import org.indexing.model.IndexingResult;
import org.indexing.model.IndexingRuleType;
import org.indexing.service.IndexingRule;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputWriter {

    public static void printOutput(String inputFileName, List<IndexingResult> indexingResults) throws IOException {
        Map<IndexingRuleType, List<IndexingResult>> indexingOutputsGrouped = indexingResults
                .stream().collect(Collectors.groupingBy(IndexingResult::indexingRuleType));

        BufferedWriter writer = new BufferedWriter(new FileWriter("output_" + inputFileName));
        writer.write("File name: " + inputFileName + "\n");

        for (Map.Entry<IndexingRuleType, List<IndexingResult>> entry: indexingOutputsGrouped.entrySet()) {
            String output = getOutput(entry.getKey(), entry.getValue());
            writer.write(output);
        }
        writer.close();
    }

    private static String getOutput(IndexingRuleType indexingRuleType, List<IndexingResult> indexingResults) {
        IndexingRule indexingRule = IndexingRuleConfig.getIndexingRuleByType(indexingRuleType);
        return indexingRule.getOutputString(indexingResults);
    }

}
