package me.khalidzahra.mevaluator.parse.codeshovel;

import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.body.MethodDeclaration;
import me.khalidzahra.mevaluator.analysis.MethodMetrics;

import java.util.List;
import java.util.Map;

/**
 * The CSMethod class is a data structure for Gson to load the JSON CodeShovel outputs.
 */
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
    /*
        Both source and declaration are transient because they are computed after Gson loads the data.
        They are set to transient so that Gson ignores them.
    */
    private transient String initialMethodSource;
    private transient MethodDeclaration methodDeclaration;

    /**
     * The load method computes the initial source and declaration. It also checks if the method has history issues
     * or any parsing issues and updates the associated MethodMetrics object.
     * @param methodMetrics MethodMetrics object which holds all metrics associated with the method.
     */
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
            this.initialMethodSource = initialMethodBody;
        } catch (ParseProblemException e) {
            methodMetrics.setParsable(false);
        }
    }

    public String getInitialMethodSource() {
        return initialMethodSource;
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
