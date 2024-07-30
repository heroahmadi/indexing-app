package org.indexing.rule;

public class MinimumCharacterRule implements IndexingRule {

    @Override
    public boolean isTrue(String input) {
        return input != null && input.length() > 5;
    }

}
