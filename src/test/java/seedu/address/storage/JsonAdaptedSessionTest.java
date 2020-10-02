package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SESSIONDATE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SESSIONNAME;
import static seedu.address.logic.commands.CommandTestUtil.REC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SESSIONDATE_REC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SESSIONNAME_REC;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attendance.SessionDate;
import seedu.address.model.attendance.SessionName;



public class JsonAdaptedSessionTest {


    private static final String INVALID_NAME = INVALID_SESSIONNAME;
    private static final String INVALID_DATE = INVALID_SESSIONDATE;

    private static final String VALID_NAME = VALID_SESSIONNAME_REC;
    private static final String VALID_DATE = VALID_SESSIONDATE_REC;
    private static final List<JsonAdaptedAttributes> studentList = new ArrayList<>();

    @Test
    public void toModelType_validSessionDetails_returnsSession() throws Exception {
        JsonAdaptedSession session = new JsonAdaptedSession(VALID_NAME, VALID_DATE, studentList);
        assertEquals(REC, session.toModelType());
    }

    @Test
    public void toModelType_invalidSessionName_throwsIllegalValueException() {
        JsonAdaptedSession session = new JsonAdaptedSession(INVALID_NAME, VALID_DATE, studentList);
        String expectedMessage = SessionName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, session::toModelType);
    }

    @Test
    public void toModelType_nullSessionName_throwsIllegalValueException() {
        JsonAdaptedSession session = new JsonAdaptedSession(null, VALID_DATE, studentList);
        String expectedMessage = SessionName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, session::toModelType);
    }

    @Test
    public void toModelType_invalidSessionDate_throwsIllegalValueException() {
        JsonAdaptedSession session = new JsonAdaptedSession(VALID_NAME, INVALID_DATE, studentList);
        String expectedMessage = SessionDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, session::toModelType);
    }

    @Test
    public void toModelType_nullSessionDate_throwsIllegalValueException() {
        JsonAdaptedSession session = new JsonAdaptedSession(VALID_NAME, null, studentList);
        String expectedMessage = SessionDate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, session::toModelType);
    }

}
