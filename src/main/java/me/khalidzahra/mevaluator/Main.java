package me.khalidzahra.mevaluator;

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import me.khalidzahra.mevaluator.analysis.analyzer.SizeAnalyzer;
import me.khalidzahra.mevaluator.parse.JsonParser;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        ParserConfiguration config = new ParserConfiguration().setAttributeComments(false).setLanguageLevel(ParserConfiguration.LanguageLevel.RAW);
        StaticJavaParser.setConfiguration(config);

        JsonParser jsonParser = new JsonParser();
        jsonParser.registerAnalyzers(new SizeAnalyzer());
        jsonParser.parse("src/main/resources/checkstyle");
//        jsonParser.parse("src/main/resources/hadoop");
    }
}
