package com.zhili.plugin.analysis.zw;

import com.zhili.index.analysis.ZwAnalyzerProvider;
import com.zhili.index.analysis.ZwTokenizerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.index.analysis.TokenizerFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.Collections;
import java.util.Map;

public class AnalysisZwPlugin extends Plugin implements AnalysisPlugin {

    private final static Logger LOG = LogManager.getLogger(AnalysisZwPlugin.class);

    public AnalysisZwPlugin() {
        super();
        LOG.info("{} installed into elasticsearch", "");
    }

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> getTokenizers() {
        return Collections.singletonMap("zhili", ZwTokenizerFactory::new);
//        Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> extra = new HashMap<>();
//        extra.put("zhili", ZwTokenizerFactory::new);
//        return extra;
    }

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
        return Collections.singletonMap("zhili", ZwAnalyzerProvider::new);
    }

}
