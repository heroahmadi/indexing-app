package org.indexing.service;

import org.indexing.model.IndexingResult;
import org.indexing.model.IndexingRuleType;

import java.util.List;

public class UppercaseRule implements IndexingRule {

    @Override
    public boolean isMeetCriteria(String input) {
        return input != null && !input.isEmpty() && Character.isUpperCase(input.charAt(0));
    }

    @Override
    public IndexingResult getIndexingResult(String input) {
        return new IndexingResult(input, IndexingRuleType.UPPERCASE);
    }

    @Override
    public String getOutputString(List<IndexingResult> indexingResults) {
        int count = 0;
        for (IndexingResult indexingResult : indexingResults) {
            if (IndexingRuleType.UPPERCASE.equals(indexingResult.indexingRuleType())) {
                count++;
            }
        }
        return "\n- Number of words starting with uppercase letter: " + count + "\n";
    }
}
