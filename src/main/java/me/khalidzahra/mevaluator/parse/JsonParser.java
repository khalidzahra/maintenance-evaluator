package me.khalidzahra.mevaluator.parse;

import com.google.gson.Gson;
import me.khalidzahra.mevaluator.parse.codeshovel.CSMethod;

import java.io.*;
import java.util.Arrays;

public class JsonParser {

    private String folderPath;
    private Gson gson;
    public JsonParser(String folderPath) {
        this.folderPath = folderPath;
        this.gson = new Gson();
    }

    public void parse() {
        File dir = new File(folderPath);
        for (String jfile : dir.list()) {
            try {
                Reader reader = new FileReader(folderPath + File.separator + jfile);
                CSMethod method = gson.fromJson(reader, CSMethod.class);
                // TODO use method for analysis
            } catch (FileNotFoundException e) {
                System.out.println("Could not load method in " + jfile);
            }
        }
    }


}
