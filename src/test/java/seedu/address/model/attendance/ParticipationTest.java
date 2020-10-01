package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ParticipationTest {

    private static final boolean HAS_PARTICIPATED = true;

    private static final Participation DEFAULT_PARTICIPATION = new Participation();
    private static final Participation POSITIVE_PARTICIPATION = new Participation(HAS_PARTICIPATED);

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(DEFAULT_PARTICIPATION.equals(DEFAULT_PARTICIPATION));

        // null -> returns false
        assertFalse(DEFAULT_PARTICIPATION.equals(null));
    }

    @Test
    public void participate() {
        assertEquals(POSITIVE_PARTICIPATION, DEFAULT_PARTICIPATION.participate());
    }
}
