package me.khalidzahra.mevaluator;

import me.khalidzahra.mevaluator.parse.JsonParser;

/**
 * Hello world!
 */
public class Main {
    public static void main(String[] args) {
        JsonParser jsonParser = new JsonParser("src/main/resources/checkstyle");
        jsonParser.parse();
    }
}
