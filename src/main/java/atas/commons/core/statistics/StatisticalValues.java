package atas.commons.core.statistics;

/**
 * A set of state values representing statistical states of certain data.
 */
public class StatisticalValues {

    public static final int STAT_NULL = -1;
    public static final int STAT_NOTFULL = 0;
    public static final int STAT_FULL = 1;
    public static final int STAT_INVALID = -2;

    public static int getValue(int actual, int expected) {
        if (actual < 0 || expected < 0) {
            return STAT_INVALID;
        }

        if (expected == 0) {
            return STAT_NULL;
        } else if (actual < expected) {
            return STAT_NOTFULL;
        } else if (actual == expected) {
            return STAT_FULL;
        } else {
            return STAT_INVALID;
        }
    }
}
