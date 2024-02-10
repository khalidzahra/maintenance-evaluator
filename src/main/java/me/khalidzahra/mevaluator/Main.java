package me.khalidzahra.mevaluator;

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import me.khalidzahra.mevaluator.analysis.analyzer.McCabeAnalyzer;
import me.khalidzahra.mevaluator.analysis.analyzer.ReadabilityAnalyzer;
import me.khalidzahra.mevaluator.analysis.analyzer.RevisionAnalyzer;
import me.khalidzahra.mevaluator.analysis.analyzer.SizeAnalyzer;
import me.khalidzahra.mevaluator.parse.JsonParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ParserConfiguration config = new ParserConfiguration()
                .setAttributeComments(false)
                .setLanguageLevel(ParserConfiguration.LanguageLevel.RAW);
        StaticJavaParser.setConfiguration(config);

        // Create JsonParser object and register all analyzers
        JsonParser jsonParser = new JsonParser();
        jsonParser.registerAnalyzers(
                new SizeAnalyzer(),
                new McCabeAnalyzer(),
                new ReadabilityAnalyzer(),
                new RevisionAnalyzer());

        String outputDir = parseOutputDir(args);
        // Run parse for each input directory specified
        parseInputDirs(args).forEach(dir -> jsonParser.parse(dir, outputDir));
    }

    /**
     * Finds all directories that contain JSON files to be analyzed.
     * @param args String array containing command line arguments.
     * @return ArrayList containing all directories as String objects.
     */
    private static List<String> parseInputDirs(String[] args) {
        List<String> dirs = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) break; // Stop trying to find dirs when flag is encountered
            dirs.add(args[i]);
        }
        return dirs;
    }

    /**
     * Finds the output directory specified by the user using the -o flag.
     * @param args String array containing command line arguments.
     * @return String containing the output directory. Defaults to the current directory if no output directory found.
     */
    private static String parseOutputDir(String[] args) {
        String out = "." + File.separator;
        int i = 0;
        // Loop through all args until flag is found
        while (i < args.length && !args[i].equalsIgnoreCase("-o")) {
            i++;
        }
        if (args.length > i + 1) { // Make sure there is an argument after the flag
            out = args[i + 1];
        }
        return out;
    }
}
