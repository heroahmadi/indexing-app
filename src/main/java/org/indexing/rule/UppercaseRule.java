package org.indexing.rule;

public class UppercaseRule implements IndexingRule {

    @Override
    public boolean isTrue(String input) {
        return input != null && Character.isUpperCase(input.charAt(0));
    }
}
