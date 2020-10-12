package atas.model.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PresenceTest {

    private static final boolean IS_PRESENT = true;

    private static final Presence DEFAULT_PRESENCE = new Presence();
    private static final Presence POSITIVE_PRESENCE = new Presence(IS_PRESENT);

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(DEFAULT_PRESENCE.equals(DEFAULT_PRESENCE));

        // null -> returns false
        assertFalse(DEFAULT_PRESENCE.equals(null));
    }

    @Test
    public void becomePresent() {
        assertEquals(POSITIVE_PRESENCE, DEFAULT_PRESENCE.togglePresence());
        assertEquals(DEFAULT_PRESENCE, POSITIVE_PRESENCE.togglePresence());
    }

    @Test
    public void to_string() {
        Presence actual = new Presence(true);
        Presence expected = new Presence(true);
        assertEquals(expected.toString(), actual.toString());
    }
}
