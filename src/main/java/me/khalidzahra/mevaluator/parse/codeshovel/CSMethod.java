package me.khalidzahra.mevaluator.parse.codeshovel;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.MethodDeclaration;
import me.khalidzahra.mevaluator.analysis.MethodMetrics;

import java.util.List;
import java.util.Map;

public class CSMethod {

    private String origin;
    private String repositoryName;
    private String repositoryPath;
    private String startCommitName;
    private String sourceFileName;
    private String functionName;
    private String functionId;
    private String sourceFilePath;
    private String functionStartLine;
    private String functionEndLine;
    private String numCommitsSeen;
    private String timeTaken;
    private List<String> changeHistory;
    private Map<String, String> changeHistoryShort;
    private Map<String, CSRevision> changeHistoryDetails;
    private transient String methodSource;
    private transient MethodDeclaration methodDeclaration;

    public void load(MethodMetrics methodMetrics) {
        if (this.changeHistory.isEmpty()) {
            methodMetrics.setHistoryIssues(true);
            return;
        }
        String firstCommitHash = this.changeHistory.get(this.changeHistory.size() - 1);
        if (!this.changeHistoryShort.get(firstCommitHash).equalsIgnoreCase("YIntroduced")) {
            methodMetrics.setHistoryIssues(true);
            return;
        }
        String initialMethodBody = this.changeHistoryDetails.get(firstCommitHash).getActualSource();
        try {
            this.methodDeclaration = StaticJavaParser.parseMethodDeclaration(initialMethodBody);
            this.methodSource = initialMethodBody;
        } catch (ParseProblemException e) {
            methodMetrics.setParsable(false);
        }
    }

    public String getMethodSource() {
        return methodSource;
    }

    public MethodDeclaration getMethodDeclaration() {
        return methodDeclaration;
    }

    public String getOrigin() {
        return origin;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public String getRepositoryPath() {
        return repositoryPath;
    }

    public String getStartCommitName() {
        return startCommitName;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getFunctionId() {
        return functionId;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public String getFunctionStartLine() {
        return functionStartLine;
    }

    public String getFunctionEndLine() {
        return functionEndLine;
    }

    public String getNumCommitsSeen() {
        return numCommitsSeen;
    }

    public String getTimeTaken() {
        return timeTaken;
    }

    public List<String> getChangeHistory() {
        return changeHistory;
    }

    public Map<String, String> getChangeHistoryShort() {
        return changeHistoryShort;
    }

    public Map<String, CSRevision> getChangeHistoryDetails() {
        return changeHistoryDetails;
    }
}
