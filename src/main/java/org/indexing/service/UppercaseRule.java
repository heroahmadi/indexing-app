package org.indexing.service;

import org.indexing.model.IndexingOutput;
import org.indexing.model.IndexingRuleType;

public class UppercaseRule implements IndexingRule {

    @Override
    public boolean isMeetCriteria(String input) {
        return input != null && !input.isEmpty() && Character.isUpperCase(input.charAt(0));
    }

    @Override
    public IndexingOutput getOutput(String input) {
        return new IndexingOutput(input, IndexingRuleType.UPPERCASE);
    }
}
