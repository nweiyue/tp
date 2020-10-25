package atas.model.session;

import static atas.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents the range of index of students in a session.
 * Guarantees: immutability
 */
public class IndexRange {

    public static final String MESSAGE_CONSTRAINTS = "Index range should be either a number or value range. "
            + "It should adhere to the following constraints:\n"
            + "1. Number or range should be greater than 0 and smaller or equal to the largest index.\n"
            + "2. Range of values should be in the form of VALUE1-VALUE2.\n"
            + "3. Range must be in an increasing order,"
            + " where the second number is bigger than or equals to the first.";

    public static final String VALIDATION_REGEX_1 = "^0*[1-9][0-9]*$";
    public static final String VALIDATION_REGEX_2 = "^0*[1-9][0-9]*[-]0*[1-9][0-9]*$";

    private final String range;

    private int lower;
    private int upper;


    /**
     * Constructs an {@code IndexRange}.
     *
     * @param range Range of value in the form of a string
     */
    public IndexRange(String range) {
        requireNonNull(range);
        this.range = range;
        checkArgument(isValidIndexRange(), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns if a given string is a valid index range.
     */
    private boolean isValidIndexRange() {
        if (range.matches(VALIDATION_REGEX_1)) {
            lower = Integer.parseInt(range);
            upper = lower;
            return true;
        } else if (range.matches(VALIDATION_REGEX_2)) {
            return isIncreasingRange(range);
        } else {
            return false;
        }
    }

    /**
     * Checks if a given range of two numbers is in increasing order.
     *
     * @param range Range to be checked.
     */
    private boolean isIncreasingRange(String range) {
        String[] splitRangeString = range.split("-");
        lower = Integer.parseInt(splitRangeString[0]);
        upper = Integer.parseInt(splitRangeString[1]);
        return lower <= upper;
    }

    public int getOneBasedLower() {
        return lower;
    }

    public int getOneBasedUpper() {
        return upper;
    }

    public int getZeroBasedLower() {
        return lower - 1;
    }

    public int getZeroBasedUpper() {
        return upper - 1;
    }

    @Override
    public String toString() {
        return "Range: " + this.lower + "-" + this.upper;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IndexRange // instanceof handles nulls
                && lower == (((IndexRange) other).lower)
                && upper == (((IndexRange) other).upper));
    }

    @Override
    public int hashCode() {
        return lower + upper;
    }
}
