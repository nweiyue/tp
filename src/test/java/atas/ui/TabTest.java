package atas.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.commons.core.index.Index;

public class TabTest {

    @Test
    public void testGetIndex() {
        // actual index -> true
        assertEquals(Tab.STUDENTS.getIndex().getZeroBased(), Index.fromZeroBased(0).getZeroBased());
        assertEquals(Tab.SESSIONS.getIndex().getZeroBased(), Index.fromZeroBased(1).getZeroBased());

        // zeroBased index but different values -> false
        assertNotEquals(Tab.STUDENTS.getIndex(), Index.fromZeroBased(3).getZeroBased());
        assertNotEquals(Tab.SESSIONS.getIndex(), Index.fromZeroBased(4).getZeroBased());

        // oneBased index but same values -> false
        assertNotEquals(Tab.STUDENTS.getIndex(), Index.fromZeroBased(0).getOneBased());
        assertNotEquals(Tab.SESSIONS.getIndex(), Index.fromZeroBased(1).getOneBased());

        // oneBased index and different values -> false
        assertNotEquals(Tab.STUDENTS.getIndex(), Index.fromZeroBased(3).getOneBased());
        assertNotEquals(Tab.SESSIONS.getIndex(), Index.fromZeroBased(4).getOneBased());
    }

    @Test
    public void equals() {
        Tab studentsTab = Tab.STUDENTS;
        Tab sessionsTab = Tab.SESSIONS;

        // same object -> returns true
        assertTrue(studentsTab.equals(studentsTab));
        assertTrue(sessionsTab.equals(sessionsTab));

        // same values -> returns true
        Tab studentsTabCopy = Tab.STUDENTS;
        assertTrue(studentsTab.equals(studentsTabCopy));
        Tab sessionsTabCopy = Tab.SESSIONS;
        assertTrue(sessionsTab.equals(sessionsTabCopy));

        // different types -> returns false
        assertFalse(studentsTab.equals(1));
        assertFalse(sessionsTab.equals(1));

        // null -> returns false
        assertFalse(studentsTab.equals(null));
        assertFalse(sessionsTab.equals(null));

        // different tabs -> returns false
        assertFalse(studentsTab.equals(sessionsTab));
    }
}
