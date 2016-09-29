package seedu.addressbook.common;

/**
 * Class used to define a range
 * Example 1 to 3 inclusive
 */
public class Range {
    private int startIndex;
    private int endIndex;
    
    public Range(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }
}
