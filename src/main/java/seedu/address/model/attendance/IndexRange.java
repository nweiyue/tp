package seedu.address.model.attendance;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the range of index of students in a session.
 * Guarantees: immutability
 */
public class IndexRange {

    public static final String MESSAGE_CONSTRAINTS = "Index range should be either a number or value range. "
            + "It should adhere to the following constraint:\n"
            + "1. Number or range should be greater than 0 and smaller or equal to the largest index. Any value "
            + "out of range will be rounded to the nearest range boundary\n"
            + "2. Range of values should be in the form of VALUE1-VALUE2.";

    public static final String VALIDATION_REGEX_1 = "[0-9]{1,}";
    public static final String VALIDATION_REGEX_2 = "[0-9]{1,}[-]{1}[0-9]{1,}";

    public final String value;

    private int lower;
    private int upper;

    /**
     * Constructs an {@code IndexRange}.
     *
     * @param range Range of value in the form of a string
     */
    public IndexRange(String range) {
        requireNonNull(range);
        checkArgument(isValidIndexRange(range), MESSAGE_CONSTRAINTS);
        value = range;
        fillParams(range);
    }

    /**
     * Returns if a given string is a valid index range.
     */
    public static boolean isValidIndexRange(String test) {
        return test.matches(VALIDATION_REGEX_1) || test.matches(VALIDATION_REGEX_2);
    }

    private void fillParams(String range) {
        int[] rangeValues = new int[2];

        if (range.matches(VALIDATION_REGEX_1)) {
            rangeValues[0] = Integer.parseInt(range);
            rangeValues[1] = rangeValues[0];
        } else {
            String[] splitRangeString = range.split("-");
            int firstNumber = Integer.parseInt(splitRangeString[0]);
            int secondNumber = Integer.parseInt(splitRangeString[1]);

            rangeValues[0] = Math.min(firstNumber, secondNumber);
            rangeValues[1] = Math.max(firstNumber, secondNumber);
        }

        if (rangeValues[0] < 1) {
            rangeValues[0] = 1;
        }
        if (rangeValues[1] < 1) {
            rangeValues[1] = 1;
        }

        this.lower = rangeValues[0];
        this.upper = rangeValues[1];
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
