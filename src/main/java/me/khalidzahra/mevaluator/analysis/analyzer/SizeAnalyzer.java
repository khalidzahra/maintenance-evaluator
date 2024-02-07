package me.khalidzahra.mevaluator.analysis.analyzer;

import me.khalidzahra.mevaluator.analysis.MethodMetrics;
import me.khalidzahra.mevaluator.parse.codeshovel.CSMethod;

import java.util.Scanner;

public class SizeAnalyzer implements Analyzer {

    @Override
    public void analyze(CSMethod csMethod, MethodMetrics methodMetrics) {
        methodMetrics.setSize(getRealSize(csMethod));
    }

    private int getRealSize(CSMethod csMethod) {
        int size = 0;
        Scanner scanner = new Scanner(csMethod.getInitialMethodSource());
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().strip(); // Remove leading and trailing spaces
            if (line.isEmpty() || line.startsWith("//")) continue;
            if (line.startsWith("/*")) { // Check for multi-line comments
                /*
                    Only stop skipping when multi-line comment terminator is met.
                    This also handles the case where a multi-line comment is on one line.
                */
                while (!line.endsWith("*/")) {
                    line = scanner.nextLine().strip();
                }
                continue;
            }
            size++;
        }
        return size;
    }

}
