package me.khalidzahra.mevaluator.analysis;

public class MethodMetrics {

    private final String id;
    private int size;
    private int complexity;
    private double readability;
    private int numberOfRevisions;
    private boolean parsable;
    private boolean historyIssues;

    public MethodMetrics(String id) {
        this.id = id;
        this.parsable = true;
        this.historyIssues = false;
    }

    public String[] asArray() {
        return new String[]{
                id,
                String.valueOf(size),
                String.valueOf(complexity),
                String.valueOf(readability),
                String.valueOf(numberOfRevisions)
        };
    }

    public boolean hasHistoryIssues() {
        return historyIssues;
    }

    public void setHistoryIssues(boolean historyIssues) {
        this.historyIssues = historyIssues;
    }

    public boolean isParsable() {
        return parsable;
    }

    public void setParsable(boolean parsable) {
        this.parsable = parsable;
    }

    public String getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public double getReadability() {
        return readability;
    }

    public void setReadability(double readability) {
        this.readability = readability;
    }

    public int getNumberOfRevisions() {
        return numberOfRevisions;
    }

    public void setNumberOfRevisions(int numberOfRevisions) {
        this.numberOfRevisions = numberOfRevisions;
    }
}
