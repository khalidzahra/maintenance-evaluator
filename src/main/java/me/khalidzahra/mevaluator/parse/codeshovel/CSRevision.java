package me.khalidzahra.mevaluator.parse.codeshovel;

public class CSRevision {

    private String type;
    private String commitMessage;
    private String commitDate;
    private String commitName;
    private String commitAuthor;
    private String diff;
    private String actualSource;
    private String path;

    public String getType() {
        return type;
    }

    public String getCommitMessage() {
        return commitMessage;
    }

    public String getCommitDate() {
        return commitDate;
    }

    public String getCommitName() {
        return commitName;
    }

    public String getCommitAuthor() {
        return commitAuthor;
    }

    public String getDiff() {
        return diff;
    }

    public String getActualSource() {
        return actualSource;
    }

    public String getPath() {
        return path;
    }
}
