package org.indexing.service;

import org.indexing.rule.IndexingRule;

public class MinimumCharacterRule implements IndexingRule {

    @Override
    public boolean isTrue(String input) {
        return input != null && input.length() > 5;
    }

}
