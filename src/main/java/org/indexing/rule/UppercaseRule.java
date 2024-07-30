package org.indexing.rule;

public class UppercaseRule implements IndexingRule {

    @Override
    public boolean isTrue(String input) {
        return input != null && !input.isEmpty() && Character.isUpperCase(input.charAt(0));
    }
}
