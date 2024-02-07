package me.khalidzahra.mevaluator.util;

import me.khalidzahra.mevaluator.parse.codeshovel.CSMethod;
import raykernel.apps.readability.code.Function;
import raykernel.apps.readability.eval.PortableEvaluator;

public class ReadabilityUtil {

    private static final PortableEvaluator EVALUATOR = new PortableEvaluator();

    /**
     * Uses the TSE readability JAR to calculate the readability metric of the original method sourcecode.
     * @param csMethod CSMethod object loaded by Gson from the CodeShovel JSON files.
     * @return Double containing the readability metric calculated for the method.
     */
    public static double evaluate(CSMethod csMethod) {
        Function function = new Function(csMethod.getInitialMethodSource());
        return EVALUATOR.getReadability(function);
    }

}
