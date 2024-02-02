package me.khalidzahra.mevaluator.parse.codeshovel;

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
