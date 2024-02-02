package me.khalidzahra.mevaluator.analysis.analyzer;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.ConditionalExpr;
import com.github.javaparser.ast.stmt.*;
import me.khalidzahra.mevaluator.analysis.MethodMetrics;
import me.khalidzahra.mevaluator.parse.codeshovel.CSMethod;

public class McCabeAnalyzer implements Analyzer {

    @Override
    public void analyze(CSMethod csMethod, MethodMetrics methodMetrics) {
        MethodDeclaration declaration = csMethod.getMethodDeclaration();
        int ifCount = declaration.findAll(IfStmt.class).size(),
                forCount = declaration.findAll(ForStmt.class).size(),
                forEachCount = declaration.findAll(ForEachStmt.class).size(),
                whileCount = declaration.findAll(WhileStmt.class).size(),
                caseCount = declaration.findAll(SwitchEntry.class).size(),
                catchCount = declaration.findAll(CatchClause.class).size(),
                ternaryCount = declaration.findAll(ConditionalExpr.class).size();
        int mccabeComplexity = 1 + ifCount + forCount + forEachCount + whileCount + caseCount + caseCount + catchCount + ternaryCount;
        methodMetrics.setComplexity(mccabeComplexity);
    }

}
