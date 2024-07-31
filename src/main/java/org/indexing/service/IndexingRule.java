package org.indexing.service;

import org.indexing.model.IndexingOutput;

public interface IndexingRule {

    boolean isMeetCriteria(String input);
    IndexingOutput getOutput(String input);

}
