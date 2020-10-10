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
        Tab classesTab = Tab.STUDENTS;
        Tab attendanceTab = Tab.SESSIONS;

        // same object -> returns true
        assertTrue(classesTab.equals(classesTab));
        assertTrue(attendanceTab.equals(attendanceTab));

        // same values -> returns true
        Tab classesTabCopy = Tab.STUDENTS;
        assertTrue(classesTab.equals(classesTabCopy));
        Tab attendanceTabCopy = Tab.SESSIONS;
        assertTrue(attendanceTab.equals(attendanceTabCopy));

        // different types -> returns false
        assertFalse(classesTab.equals(1));
        assertFalse(attendanceTab.equals(1));

        // null -> returns false
        assertFalse(classesTab.equals(null));
        assertFalse(attendanceTab.equals(null));

        // different tabs -> returns false
        assertFalse(classesTab.equals(attendanceTab));
    }
}
