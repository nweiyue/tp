package atas.model.session;

import static atas.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testIsValidIndexRange() {
        // null IndexRange number
        assertThrows(NullPointerException.class, () -> new IndexRange(null));

        // invalid IndexRange characters
        assertThrows(IllegalArgumentException.class, () ->new IndexRange(""));
        assertThrows(IllegalArgumentException.class, () ->new IndexRange("1-"));
        assertThrows(IllegalArgumentException.class, () ->new IndexRange("0-2"));
        assertThrows(IllegalArgumentException.class, () ->new IndexRange("-3-5"));
        assertThrows(IllegalArgumentException.class, () ->new IndexRange("13-5"));
        assertThrows(IllegalArgumentException.class, () ->new IndexRange("as"));
        assertThrows(IllegalArgumentException.class, () ->new IndexRange("ad-"));
        assertThrows(IllegalArgumentException.class, () ->new IndexRange("1-a"));

        // valid IndexRange numbers
        assertEquals(1, new IndexRange("1").getOneBasedLower());
        assertEquals(1, new IndexRange("1").getOneBasedUpper());

        assertEquals(12, new IndexRange("12-12").getOneBasedLower());
        assertEquals(12, new IndexRange("12-12").getOneBasedUpper());

        assertEquals(2, new IndexRange("2-12").getOneBasedLower());
        assertEquals(12, new IndexRange("2-12").getOneBasedUpper());
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
