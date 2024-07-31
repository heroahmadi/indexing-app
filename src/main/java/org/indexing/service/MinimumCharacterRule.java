package org.indexing.service;

import org.indexing.model.IndexingResult;
import org.indexing.model.IndexingRuleType;

import java.util.ArrayList;
import java.util.List;

public class MinimumCharacterRule implements IndexingRule {

    @Override
    public boolean isMeetCriteria(String input) {
        return input != null && input.length() > 5;
    }

    @Override
    public IndexingResult getIndexingResult(String input) {
        return new IndexingResult(input, IndexingRuleType.MINIMUM_CHAR);
    }

    @Override
    public String getOutputString(List<IndexingResult> indexingResults) {
        List<String> wordsLongerThan5Chars = new ArrayList<>();
        for (IndexingResult indexingResult : indexingResults) {
            if (IndexingRuleType.MINIMUM_CHAR.equals(indexingResult.indexingRuleType())) {
                wordsLongerThan5Chars.add(indexingResult.word());
            }
        }
        return "\n- List of words longer than 5 chars:\n" + String.join("\n", wordsLongerThan5Chars);
    }

}
