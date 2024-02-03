package me.khalidzahra.mevaluator.analysis.analyzer;

import me.khalidzahra.mevaluator.analysis.MethodMetrics;
import me.khalidzahra.mevaluator.parse.codeshovel.CSMethod;
import me.khalidzahra.mevaluator.util.ReadabilityUtil;

public class ReadabilityAnalyzer implements Analyzer {

    @Override
    public void analyze(CSMethod csMethod, MethodMetrics methodMetrics) {
        methodMetrics.setReadability(ReadabilityUtil.evaluate(csMethod));
    }

}
