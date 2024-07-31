package org.indexing.service;

import org.indexing.model.IndexingOutput;
import org.indexing.model.IndexingRuleType;

public class MinimumCharacterRule implements IndexingRule {

    @Override
    public boolean isMeetCriteria(String input) {
        return input != null && input.length() > 5;
    }

    @Override
    public IndexingOutput getOutput(String input) {
        return new IndexingOutput(input, IndexingRuleType.MINIMUM_CHAR);
    }

}
