package org.indexing.service;

import org.indexing.model.IndexingResult;

import java.util.List;

public interface IndexingRule {

    boolean isMeetCriteria(String input);
    IndexingResult getIndexingResult(String input);
    String getOutputString(List<IndexingResult> indexingResults);
}
