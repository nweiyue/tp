package atas.model.session;

import static atas.testutil.Assert.assertThrows;
import static atas.testutil.TypicalSessions.SESSIONNAME_WEEK_ONE;
import static atas.testutil.TypicalSessions.SESSIONNAME_WEEK_ONE_STRING;
import static atas.testutil.TypicalSessions.SESSIONNAME_WEEK_THREE_STRING;
import static atas.testutil.TypicalSessions.SESSIONNAME_WEEK_TWO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SessionNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SessionName(null));
    }

    @Test
    public void isValidSessionName() {
        // null sessionName
        assertThrows(NullPointerException.class, () -> SessionName.isValidSessionName(null));

        // invalid sessionName
        assertFalse(SessionName.isValidSessionName(""));
        assertFalse(SessionName.isValidSessionName(" "));
        assertFalse(SessionName.isValidSessionName("@tut"));
        assertFalse(SessionName.isValidSessionName("tut=1"));
        assertFalse(SessionName.isValidSessionName("!tut"));
        assertFalse(SessionName.isValidSessionName("#1"));

        // valid sessionName
        assertTrue(SessionName.isValidSessionName("1"));
        assertTrue(SessionName.isValidSessionName("tut1"));
        assertTrue(SessionName.isValidSessionName("tut 1"));
    }

    @Test
    public void equals() {

        // same values -> returns true
        SessionName sessionName = new SessionName(SESSIONNAME_WEEK_ONE_STRING);
        assertTrue(SESSIONNAME_WEEK_ONE.equals(sessionName));

        // same object -> returns true
        assertTrue(SESSIONNAME_WEEK_ONE.equals(SESSIONNAME_WEEK_ONE));

        // null -> returns false
        assertFalse(SESSIONNAME_WEEK_ONE.equals(null));

        // different type -> returns false
        assertFalse(SESSIONNAME_WEEK_ONE.equals(5));

        // different session date -> returns false
        assertFalse(SESSIONNAME_WEEK_ONE.equals(SESSIONNAME_WEEK_TWO));

        // different date -> returns false
        SessionName sessionDateDiff = new SessionName(SESSIONNAME_WEEK_THREE_STRING);
        assertFalse(SESSIONNAME_WEEK_ONE.equals(sessionDateDiff));
    }

    @Test
    public void hashcode() {
        SessionName actual = new SessionName("a");
        SessionName expected = new SessionName("a");
        assertEquals(expected.hashCode(), actual.hashCode());
    }

}
