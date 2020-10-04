package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class IndexRangeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new IndexRange(null));
    }

    @Test
    public void constructor_invalidIndexRange_throwsIllegalArgumentException() {
        String invalidIndexRange = "a";
        assertThrows(IllegalArgumentException.class, () -> new IndexRange(invalidIndexRange));
    }

    @Test
    public void isValidIndexRange() {
        // null IndexRange number
        assertThrows(NullPointerException.class, () -> IndexRange.isValidIndexRange(null));

        // invalid IndexRange characters
        assertFalse(IndexRange.isValidIndexRange(""));
        assertFalse(IndexRange.isValidIndexRange(" "));
        assertFalse(IndexRange.isValidIndexRange("1-"));
        assertFalse(IndexRange.isValidIndexRange("-3-5"));
        assertFalse(IndexRange.isValidIndexRange("as"));
        assertFalse(IndexRange.isValidIndexRange("ad-"));
        assertFalse(IndexRange.isValidIndexRange("1-a"));

        // valid IndexRange numbers
        assertTrue(IndexRange.isValidIndexRange("1"));
        assertTrue(IndexRange.isValidIndexRange("12-12"));
        assertTrue(IndexRange.isValidIndexRange("13-2"));
    }
}
