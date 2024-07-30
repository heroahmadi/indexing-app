package org.indexing.service;

public class MinimumCharacterRule implements IndexingRule {

    @Override
    public boolean isTrue(String input) {
        return input != null && input.length() > 5;
    }

}
