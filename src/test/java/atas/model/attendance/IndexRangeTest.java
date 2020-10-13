package atas.model.attendance;

import static atas.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void hashcode() {
        IndexRange actual = new IndexRange("1-3");
        IndexRange expected = new IndexRange("1-3");
        assertEquals(expected.hashCode(), actual.hashCode());
    }

    @Test
    public void to_string() {
        IndexRange actual = new IndexRange("1-3");
        IndexRange expected = new IndexRange("1-3");
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void oneBased() {
        IndexRange actual = new IndexRange("1-3");
        IndexRange expected = new IndexRange("1-3");
        assertEquals(expected.getOneBasedLower(), actual.getOneBasedLower());
        assertEquals(expected.getOneBasedUpper(), actual.getOneBasedUpper());
    }
}
