package me.khalidzahra.mevaluator.parse;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;
import me.khalidzahra.mevaluator.analysis.MethodMetrics;
import me.khalidzahra.mevaluator.analysis.analyzer.Analyzer;
import me.khalidzahra.mevaluator.parse.codeshovel.CSMethod;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonParser {

    private Gson gson;
    private List<Analyzer> analyzers;

    public JsonParser() {
        this.gson = new Gson();
        this.analyzers = new ArrayList<>();
    }

    /**
     * Registers the specified Analyzer to JsonParser
     * @param analyzer Concrete class that implements the Analyzer interface
     */
    public void registerAnalyzer(Analyzer analyzer) {
        this.analyzers.add(analyzer);
    }

    /**
     * Registers a list of Analyzers to JsonParser
     * @param analyzers list of concrete classes that implement the Analyzer interface
     */
    public void registerAnalyzers(Analyzer... analyzers) {
        Arrays.stream(analyzers).forEach(this::registerAnalyzer);
    }

    public void parse(String folderPath, String output) {
        int parseProblem = 0, historyProblem = 0;
        File dir = new File(folderPath);
        try {
            // Create CSV file and write the column headers
            CSVWriter writer = new CSVWriter(new FileWriter(output + ".csv"));
            String[] header = new String[]{"Json_ID", "Size", "McCabe", "Readability", "#Revisions"};
            writer.writeNext(header);

            for (String jfile : dir.list()) {
                try {
                    // Load method from JSON
                    CSMethod method = readFile(folderPath, jfile);
                    MethodMetrics metrics = new MethodMetrics(jfile);
                    method.load(metrics);

                    // Update problem counter if method is problematic
                    parseProblem += !metrics.isParsable() ? 1 : 0;
                    historyProblem += metrics.hasHistoryIssues() ? 1 : 0;

                    // Analyze the method to calculate metrics and write them to CSV file
                    analyzeMethod(method, metrics);
                    writeMethodToCSV(writer, metrics);
                } catch (FileNotFoundException e) {
                    System.out.println("Could not load method in " + jfile);
                }
            }
            writer.close();
            System.out.println("Successfully created " + output + ".csv");
        } catch (IOException e) {
            System.out.println("Error writing CSV file.");
        }
        // Print the statistics for the folder JsonParser just analyzed
        printStatistics(folderPath, parseProblem, historyProblem);
    }

    /**
     * Loads method from JSON using Gson.
     * @param folderPath Path to folder containing JSON file
     * @param jfile Name of the JSON file
     * @return Returns CSMethod object
     * @throws FileNotFoundException
     */
    private CSMethod readFile(String folderPath, String jfile) throws FileNotFoundException {
        Reader reader = new FileReader(folderPath + File.separator + jfile);
        return gson.fromJson(reader, CSMethod.class);
    }

    /**
     * Runs all registered analyzers on the specified CSMethod and updates its MethodMetrics
     * @param method CSMethod object loaded from JSON
     * @param metrics MethodMetrics object that is associated with this CSMethod object
     */
    private void analyzeMethod(CSMethod method, MethodMetrics metrics) {
        analyzers.forEach((analyzer -> {
            if (metrics.isParsable() && !metrics.hasHistoryIssues()) {
                analyzer.analyze(method, metrics);
            }
        }));
    }

    /**
     * Writes the specified metrics to a CSV file using its CSVWriter.
     * @param writer CSVWriter object associated with the CSV file
     * @param metrics MethodMetrics object that hold metrics to be written
     */
    private void writeMethodToCSV(CSVWriter writer, MethodMetrics metrics) {
        if (metrics.isParsable() && !metrics.hasHistoryIssues()) {
            writer.writeNext(metrics.asArray());
        }
    }

    /**
     * Prints statistics associated with the folder JsonParser was run against.
     * @param folderPath String object containing the path to the folder.
     * @param parseProblem Integer containing the number of methods that had parsing problems
     * @param historyProblem Integer containing the number of methods that had history problems
     */
    private void printStatistics(String folderPath, int parseProblem, int historyProblem) {
        System.out.println("\n========== " + folderPath + " ==========");
        System.out.printf("Unparsable methods: %d\n" +
                        "Methods with history issues: %d\n" +
                        "Total number of problematic methods: %d\n\n",
                parseProblem, historyProblem, (parseProblem + historyProblem));
    }
}
