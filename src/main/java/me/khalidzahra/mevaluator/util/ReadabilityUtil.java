package me.khalidzahra.mevaluator.util;

import me.khalidzahra.mevaluator.parse.codeshovel.CSMethod;
import raykernel.apps.readability.code.Function;
import raykernel.apps.readability.eval.PortableEvaluator;

public class ReadabilityUtil {

    private static final PortableEvaluator EVALUATOR = new PortableEvaluator();

    public static double evaluate(CSMethod csMethod) {
        Function function = new Function(csMethod.getMethodSource());
        return EVALUATOR.getReadability(function);
    }

}
