package me.khalidzahra.mevaluator;

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import com.google.gson.Gson;
import me.khalidzahra.mevaluator.analysis.MethodMetrics;
import me.khalidzahra.mevaluator.analysis.analyzer.McCabeAnalyzer;
import me.khalidzahra.mevaluator.analysis.analyzer.ReadabilityAnalyzer;
import me.khalidzahra.mevaluator.analysis.analyzer.RevisionAnalyzer;
import me.khalidzahra.mevaluator.analysis.analyzer.SizeAnalyzer;
import me.khalidzahra.mevaluator.parse.JsonParser;
import me.khalidzahra.mevaluator.parse.codeshovel.CSMethod;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ParserConfiguration config = new ParserConfiguration().setAttributeComments(false).setLanguageLevel(ParserConfiguration.LanguageLevel.RAW);
        StaticJavaParser.setConfiguration(config);

        JsonParser jsonParser = new JsonParser();
        jsonParser.registerAnalyzers(
                new SizeAnalyzer(),
                new McCabeAnalyzer(),
                new ReadabilityAnalyzer(),
                new RevisionAnalyzer());
        jsonParser.parse("src/main/resources/checkstyle", "checkstyle");
        jsonParser.parse("src/main/resources/hadoop", "hadoop");
    }
}
