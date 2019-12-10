package com.zhili.index.analysis;

import com.zhili.analyzer.lucene.ZWTokenizer;
import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenizerFactory;

public class ZwTokenizerFactory extends AbstractTokenizerFactory {

    public ZwTokenizerFactory(IndexSettings indexSettings, Environment env, String name, Settings settings)
    {
        super(indexSettings, settings);
    }

    public Tokenizer create() {
        return new ZWTokenizer();
    }
}
