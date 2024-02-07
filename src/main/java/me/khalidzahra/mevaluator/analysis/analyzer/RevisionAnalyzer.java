package me.khalidzahra.mevaluator.analysis.analyzer;

import me.khalidzahra.mevaluator.analysis.MethodMetrics;
import me.khalidzahra.mevaluator.parse.codeshovel.CSMethod;

public class RevisionAnalyzer implements Analyzer {

    private final String RENAME_ID = "Yrename";
    private final String MOVE_ID = "Ymovefromfile";
    private final String MULTI_ID = "Ymultichange";

    @Override
    public void analyze(CSMethod csMethod, MethodMetrics methodMetrics) {
        // Exclude Yrename or Ymovefromfile if no other changes exist
        int revisions = 0;
        revisions += (int) csMethod.getChangeHistoryShort().values().stream()
                .filter(change -> {
                    if (change.startsWith(MULTI_ID)) {
                        return isValidMultiChange(change);
                    }
                    return isValidChange(change);
                })
                .count();
        methodMetrics.setNumberOfRevisions(revisions);
    }

    /**
     * Checks if the change is not Yrename or Ymovefromfile
     *
     * @param change The revision extracted from CodeShovel
     * @return True if the change is not a rename or a file move.
     */
    public boolean isValidChange(String change) {
        return !change.equalsIgnoreCase(RENAME_ID) && !change.equalsIgnoreCase(MOVE_ID);
    }

    /**
     * Handles the case where a revision is of the type Ymultichange. It makes sure that the multichange consists
     * of changes other than Yrename and Ymovefromfile exclusively.
     *
     * @param multiChange The revision where multiple changes occurred.
     * @return True if the multichange is valid.
     */
    private boolean isValidMultiChange(String multiChange) {
        String sub = multiChange.substring(MULTI_ID.length());
        sub = sub.substring(1, sub.length() - 1);
        String[] changes = sub.split(",");

        int unwantedChanges = 0;
        for (String change : changes) {
            unwantedChanges += change.equalsIgnoreCase(MOVE_ID) || change.equalsIgnoreCase(RENAME_ID) ? 1 : 0;
        }

        // Only true when changes are not exclusively rename and move
        return changes.length - unwantedChanges > 1;
    }

}
