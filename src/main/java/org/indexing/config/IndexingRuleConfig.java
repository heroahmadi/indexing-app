package org.indexing.config;

import org.indexing.model.IndexingRuleType;
import org.indexing.service.IndexingRule;
import org.indexing.service.MinimumCharacterRule;
import org.indexing.service.UppercaseRule;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class IndexingRuleConfig {

    private static final MinimumCharacterRule minimumCharacterRule = new MinimumCharacterRule();
    private static final UppercaseRule uppercaseRule = new UppercaseRule();

    private static final Map<IndexingRuleType, IndexingRule> indexingRuleMapType = Map.of(
            IndexingRuleType.MINIMUM_CHAR, minimumCharacterRule,
            IndexingRuleType.UPPERCASE, uppercaseRule
    );

    public static final List<IndexingRule> ACTIVE_INDEXING_RULES = asList(
            minimumCharacterRule,
            uppercaseRule
    );

    public static IndexingRule getIndexingRuleByType(IndexingRuleType indexingRuleType) {
        return indexingRuleMapType.get(indexingRuleType);
    }

}
