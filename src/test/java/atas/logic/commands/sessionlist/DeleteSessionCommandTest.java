package atas.logic.commands.sessionlist;

import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static atas.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.logic.commands.exceptions.CommandException;
import atas.model.Model;
import atas.model.ModelManager;
import atas.model.UserPrefs;
import atas.model.attendance.Session;
import atas.model.attendance.SessionList;
import atas.model.attendance.SessionName;
import atas.model.attendance.exceptions.SessionNotFoundException;
import atas.testutil.ModelManagerBuilder;
import atas.testutil.SessionBuilder;
import atas.testutil.TypicalSessions;

public class DeleteSessionCommandTest {

    private Model model = ModelManagerBuilder.buildTypicalModelManager();

    @Test
    public void execute_validSessionName_success() {

        SessionName sessionName = TypicalSessions.TUT1.getSessionName();
        DeleteSessionCommand deleteSessionCommand = new DeleteSessionCommand(sessionName);

        String expectedMessage = DeleteSessionCommand.MESSAGE_SUCCESS;

        ModelManager expectedModel = new ModelManager(TypicalSessions.getTypicalSessionListMinusTut1(
                model.getAddressBook().getPersonList()), model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(deleteSessionCommand, model, expectedMessage, expectedModel);
        assertFalse(model.hasSession(TypicalSessions.TUT1));
    }

    @Test
    public void execute_sessionNotFound_throwsCommandException() {

        SessionName sessionName = TypicalSessions.TUT1.getSessionName();
        DeleteSessionCommand deleteSessionCommand = new DeleteSessionCommand(sessionName);

        String expectedMessage = DeleteSessionCommand.MESSAGE_SESSION_NOT_FOUND;

        ModelManager expectedModel = new ModelManager(TypicalSessions.getTypicalSessionListMinusTut1(
                model.getAddressBook().getPersonList()), model.getAddressBook(), new UserPrefs());

        assertThrows(CommandException.class, expectedMessage, () -> deleteSessionCommand.execute(expectedModel));
    }

    @Test
    public void delete_sessionNotFound_throwsSessionNotFoundException() {
        SessionList emptySessionList = new SessionList();
        SessionBuilder sessionBuilder = new SessionBuilder();
        assertThrows(SessionNotFoundException.class, () -> emptySessionList.deleteSession(sessionBuilder.build()));
    }

    @Test
    public void equals() {
        Session tut = new SessionBuilder().withSessionName("tut").build();
        Session lab = new SessionBuilder().withSessionName("lab").build();

        DeleteSessionCommand deleteTutCommand = new DeleteSessionCommand(tut.getSessionName());
        DeleteSessionCommand deleteLabCommand = new DeleteSessionCommand(lab.getSessionName());

        // same object -> returns true
        assertTrue(deleteTutCommand.equals(deleteTutCommand));

        // same sessioName -> returns true
        DeleteSessionCommand deleteTutCommandCopy = new DeleteSessionCommand(tut.getSessionName());
        assertTrue(deleteTutCommand.equals(deleteTutCommandCopy));

        // different types -> returns false
        assertFalse(deleteTutCommand.equals(1));

        // null -> returns false
        assertFalse(deleteTutCommand.equals(null));

        // different sessionName -> returns false
        assertFalse(deleteTutCommand.equals(deleteLabCommand));
    }

    @Test
    public void toString_one() {
        SessionName sessionName = TypicalSessions.TUT1.getSessionName();
        DeleteSessionCommand deleteSessionCommand = new DeleteSessionCommand(sessionName);
        String expectedString = String.format(DeleteSessionCommand.MESSAGE_COMMAND_TO_STRING,
                TypicalSessions.TUT1.getSessionName().value);
        assertEquals(expectedString, deleteSessionCommand.toString());
    }

    @Test
    public void toString_two() {
        SessionName sessionName = TypicalSessions.TUT2.getSessionName();
        DeleteSessionCommand deleteSessionCommand = new DeleteSessionCommand(sessionName);
        String expectedString = String.format(DeleteSessionCommand.MESSAGE_COMMAND_TO_STRING,
                TypicalSessions.TUT2.getSessionName().value);

        assertEquals(expectedString, deleteSessionCommand.toString());
    }

}
