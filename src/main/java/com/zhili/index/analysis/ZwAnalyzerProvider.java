package com.zhili.index.analysis;

import com.zhili.analyzer.lucene.ZWAnalyzer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;

public class ZwAnalyzerProvider extends AbstractIndexAnalyzerProvider<ZWAnalyzer> {

    private final ZWAnalyzer analyzer;

    public ZwAnalyzerProvider(IndexSettings indexSettings, Environment env, String name, Settings settings)
    {
        super(indexSettings, name, settings);
        this.analyzer = new ZWAnalyzer();
    }

    public ZWAnalyzer get() {
        return analyzer;
    }
}
