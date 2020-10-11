package atas.model.attendance;

import static atas.testutil.Assert.assertThrows;
import static atas.testutil.TypicalSessions.SESSIONDATE_WEEK_ONE;
import static atas.testutil.TypicalSessions.SESSIONDATE_WEEK_ONE_STRING;
import static atas.testutil.TypicalSessions.SESSIONDATE_WEEK_THREE_STRING;
import static atas.testutil.TypicalSessions.SESSIONDATE_WEEK_TWO;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class SessionDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SessionDate(null));
    }

    @Test
    public void constructor_invalidSessionDate_throwsDateTimeParseException() {
        String invalidSessionDateDay = "32/12/2020";
        String invalidSessionDateMonth = "12/13/2020";

        assertThrows(DateTimeParseException.class, () -> new SessionDate(invalidSessionDateDay));
        assertThrows(DateTimeParseException.class, () -> new SessionDate(invalidSessionDateMonth));
    }

    @Test
    public void isValidSessionDate() {
        // null date
        assertThrows(NullPointerException.class, () -> SessionDate.isValidSessionDate(null));

        // invalid date, only tests format but not existance of such date
        assertFalse(SessionDate.isValidSessionDate(""));
        assertFalse(SessionDate.isValidSessionDate(" "));
        assertFalse(SessionDate.isValidSessionDate("12"));
        assertFalse(SessionDate.isValidSessionDate("123/123/123"));
        assertFalse(SessionDate.isValidSessionDate("12/13/202"));
        assertFalse(SessionDate.isValidSessionDate("40/200/2019"));

        // valid date
        assertTrue(SessionDate.isValidSessionDate("1/1/2020"));
        assertTrue(SessionDate.isValidSessionDate("11/11/2020"));
        assertTrue(SessionDate.isValidSessionDate("12/9/2019"));
    }

    @Test
    public void equals() {

        // same values -> returns true
        SessionDate sessionDate = new SessionDate(SESSIONDATE_WEEK_ONE_STRING);
        assertTrue(SESSIONDATE_WEEK_ONE.equals(sessionDate));

        // same object -> returns true
        assertTrue(SESSIONDATE_WEEK_ONE.equals(SESSIONDATE_WEEK_ONE));

        // null -> returns false
        assertFalse(SESSIONDATE_WEEK_ONE.equals(null));

        // different type -> returns false
        assertFalse(SESSIONDATE_WEEK_ONE.equals(5));

        // different session date -> returns false
        assertFalse(SESSIONDATE_WEEK_ONE.equals(SESSIONDATE_WEEK_TWO));

        // different date -> returns false
        SessionDate sessionDateDiff = new SessionDate(SESSIONDATE_WEEK_THREE_STRING);
        assertFalse(SESSIONDATE_WEEK_ONE.equals(sessionDateDiff));
    }


}