package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalAttributes.ABSENT_BUT_HAS_PARTICIPATED;
import static seedu.address.testutil.TypicalAttributes.DEFAULT_PARTICIPATION;
import static seedu.address.testutil.TypicalAttributes.PRESENT_AND_HAS_PARTICIPATED;
import static seedu.address.testutil.TypicalAttributes.PRESENT_BUT_HAS_NOT_PARTICIPATED;

import org.junit.jupiter.api.Test;


class AttributesTest {

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(DEFAULT_PARTICIPATION.equals(DEFAULT_PARTICIPATION));

        // null -> returns false
        assertFalse(DEFAULT_PARTICIPATION.equals(null));
    }

    @Test
    public void studentIsPresentButHasNotParticipated() {
        assertEquals(PRESENT_BUT_HAS_NOT_PARTICIPATED, DEFAULT_PARTICIPATION.becomePresent());
    }

    @Test
    public void studentIsAbsentButHasParticipated() {
        assertEquals(ABSENT_BUT_HAS_PARTICIPATED, DEFAULT_PARTICIPATION.participate());
    }

    @Test
    public void studentIsPresentAndHasParticipated() {
        assertEquals(PRESENT_AND_HAS_PARTICIPATED, DEFAULT_PARTICIPATION.becomePresent().participate());
    }

}
