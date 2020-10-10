package atas.model.attendance;

import static atas.testutil.TypicalAttributes.ABSENT_BUT_HAS_PARTICIPATED;
import static atas.testutil.TypicalAttributes.DEFAULT_PARTICIPATION;
import static atas.testutil.TypicalAttributes.PRESENT_AND_HAS_PARTICIPATED;
import static atas.testutil.TypicalAttributes.PRESENT_BUT_HAS_NOT_PARTICIPATED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.model.person.Name;

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
        assertEquals(PRESENT_BUT_HAS_NOT_PARTICIPATED, DEFAULT_PARTICIPATION.togglePresence());
    }

    @Test
    public void studentIsAbsentButHasParticipated() {
        assertEquals(ABSENT_BUT_HAS_PARTICIPATED, DEFAULT_PARTICIPATION.toggleParticipation());
    }

    @Test
    public void studentIsPresentAndHasParticipated() {
        assertEquals(PRESENT_AND_HAS_PARTICIPATED, DEFAULT_PARTICIPATION.togglePresence().toggleParticipation());
    }

    @Test
    public void toggleParticipation() {
        Attributes actualAttributes = new Attributes();
        actualAttributes = actualAttributes.toggleParticipation();

        Attributes expectedAttributes =
            new Attributes(new Presence(false), new Participation(true), new Name("DEFAULT"));
        assertEquals(expectedAttributes, actualAttributes);
    }

    @Test
    public void togglePresence() {
        Attributes actualAttributes = new Attributes();
        actualAttributes = actualAttributes.togglePresence();

        Attributes expectedAttributes =
            new Attributes(new Presence(true), new Participation(false), new Name("DEFAULT"));
        assertEquals(expectedAttributes, actualAttributes);
    }
}
