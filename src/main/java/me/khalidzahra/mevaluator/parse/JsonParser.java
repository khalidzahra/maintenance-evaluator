package me.khalidzahra.mevaluator.parse;

import com.google.gson.Gson;
import me.khalidzahra.mevaluator.analysis.MethodMetrics;
import me.khalidzahra.mevaluator.analysis.analyzer.Analyzer;
import me.khalidzahra.mevaluator.analysis.analyzer.SizeAnalyzer;
import me.khalidzahra.mevaluator.parse.codeshovel.CSMethod;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    private Gson gson;
    private List<Analyzer> analyzers;

    public JsonParser() {
        this.gson = new Gson();
        this.analyzers = new ArrayList<>();
    }

    public void registerAnalyzer(Analyzer analyzer) {
        this.analyzers.add(analyzer);
    }

    public void registerAnalyzers(Analyzer... analyzers) {
        this.analyzers.addAll(List.of(analyzers));
    }

    public void parse(String folderPath) {
        int parseProblem = 0, historyProblem = 0, bothProblems = 0;
        File dir = new File(folderPath);
        for (String jfile : dir.list()) {
            try {
                Reader reader = new FileReader(folderPath + File.separator + jfile);
                CSMethod method = gson.fromJson(reader, CSMethod.class);
                MethodMetrics metrics = new MethodMetrics(jfile);
                method.load(metrics);
                parseProblem += !metrics.isParsable() ? 1 : 0;
                historyProblem += metrics.hasHistoryIssues() ? 1 : 0;
                parseProblem += !metrics.isParsable() && metrics.hasHistoryIssues() ? 1 : 0;
                analyzers.forEach((analyzer -> {
                    if (metrics.isParsable() && !metrics.hasHistoryIssues()) {
                        analyzer.analyze(method, metrics);
                    }
                }));
                // TODO do something with metrics
            } catch (FileNotFoundException e) {
                System.out.println("Could not load method in " + jfile);
            }
        }
        System.out.println("========== " + folderPath + " ==========");
        System.out.printf("Unparsable methods: %d\n" +
                        "Methods with history issues: %d\n" +
                        "Methods with both problems: %d\n" +
                        "Number of unique problematic methods: %d\n",
                parseProblem, historyProblem, bothProblems, (parseProblem + historyProblem - bothProblems));
    }
}
