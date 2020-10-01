package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TabTest {

    @Test
    public void testGetIndex() {
        // actual index -> true
        assertEquals(Tab.CLASSES.getIndex(), 0);
        assertEquals(Tab.ATTENDANCE.getIndex(), 1);

        // wrong index -> false
        assertNotEquals(Tab.CLASSES.getIndex(), 3);
        assertNotEquals(Tab.ATTENDANCE.getIndex(), 4);
    }

    @Test
    public void equals() {
        Tab classesTab = Tab.CLASSES;
        Tab attendanceTab = Tab.ATTENDANCE;

        // same object -> returns true
        assertTrue(classesTab.equals(classesTab));
        assertTrue(attendanceTab.equals(attendanceTab));

        // same values -> returns true
        Tab classesTabCopy = Tab.CLASSES;
        assertTrue(classesTab.equals(classesTabCopy));
        Tab attendanceTabCopy = Tab.ATTENDANCE;
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
