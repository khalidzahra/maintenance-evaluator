package me.khalidzahra.mevaluator.analysis.analyzer;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.MethodDeclaration;
import me.khalidzahra.mevaluator.analysis.MethodMetrics;
import me.khalidzahra.mevaluator.parse.codeshovel.CSMethod;

public class SizeAnalyzer implements Analyzer {

    @Override
    public void analyze(CSMethod csMethod, MethodMetrics methodMetrics) {
        int size = csMethod.getMethodDeclaration().getRange().map(methodRange -> methodRange.end.line - methodRange.begin.line + 1).orElse(0);
        methodMetrics.setSize(size);
    }

}
