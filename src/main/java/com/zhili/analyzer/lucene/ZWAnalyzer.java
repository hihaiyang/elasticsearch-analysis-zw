package com.zhili.analyzer.lucene;

import org.apache.lucene.analysis.Analyzer;

public class ZWAnalyzer extends Analyzer {

    public ZWAnalyzer() {}

    protected TokenStreamComponents createComponents(String s) {
        return new TokenStreamComponents(new ZWTokenizer());
    }
}
