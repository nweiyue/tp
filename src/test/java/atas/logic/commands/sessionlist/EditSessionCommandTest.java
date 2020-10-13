package atas.logic.commands.sessionlist;

import static atas.commons.core.Messages.MESSAGE_SESSION_DOES_NOT_EXIST;
import static atas.logic.commands.CommandTestUtil.DESC_CON;
import static atas.logic.commands.CommandTestUtil.DESC_REC;
import static atas.logic.commands.CommandTestUtil.VALID_SESSIONNAME_CON;
import static atas.logic.commands.CommandTestUtil.assertCommandFailure;
import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static atas.testutil.TypicalSessions.SESSIONDATE_WEEK_ONE;
import static atas.testutil.TypicalSessions.SESSIONDATE_WEEK_TWO;
import static atas.testutil.TypicalSessions.SESSIONDATE_WEEK_TWO_STRING;
import static atas.testutil.TypicalSessions.SESSIONNAME_WEEK_ONE;
import static atas.testutil.TypicalSessions.SESSIONNAME_WEEK_THREE_STRING;
import static atas.testutil.TypicalSessions.SESSIONNAME_WEEK_TWO;
import static atas.testutil.TypicalSessions.SESSIONNAME_WEEK_TWO_STRING;
import static atas.testutil.TypicalSessions.SESSION_WEEK_ONE;
import static atas.testutil.TypicalSessions.SESSION_WEEK_THREE;
import static atas.testutil.TypicalSessions.SESSION_WEEK_TWO;
import static atas.testutil.TypicalSessions.getTypicalSessionList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.logic.commands.CommandTestUtil;
import atas.logic.commands.studentlist.ClearStudentListCommand;
import atas.model.Model;
import atas.model.ModelManager;
import atas.model.StudentList;
import atas.model.UserPrefs;
import atas.model.attendance.Session;
import atas.model.attendance.SessionName;
import atas.testutil.EditSessionDescriptorBuilder;
import atas.testutil.ModelManagerBuilder;

public class EditSessionCommandTest {

    private Model model = ModelManagerBuilder.buildTypicalModelManager();

    @Test
    public void execute_allFieldsSpecified_success() {
        // actual model
        model.addSession(SESSION_WEEK_ONE);

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder()
                .withSessionName(SESSIONNAME_WEEK_TWO_STRING)
                .withSessionDate(SESSIONDATE_WEEK_TWO_STRING)
                .build();

        EditSessionCommand editSessionCommand = new EditSessionCommand(SESSIONNAME_WEEK_ONE, descriptor);

        // expected model
        String expectedMessage = String.format(EditSessionCommand.MESSAGE_EDIT_SESSION_SUCCESS,
                SESSION_WEEK_ONE, SESSION_WEEK_TWO);

        Model expectedModel = new ModelManager(getTypicalSessionList(model.getStudentList().getStudentList()),
                new StudentList(model.getStudentList()), new UserPrefs());

        expectedModel.addSession(SESSION_WEEK_TWO);

        assertCommandSuccess(editSessionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editOnlySessionName_success() {
        // actual model
        model.addSession(SESSION_WEEK_ONE);

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder()
                .withSessionName(SESSIONNAME_WEEK_TWO_STRING).build();

        EditSessionCommand editSessionCommand = new EditSessionCommand(SESSIONNAME_WEEK_ONE, descriptor);

        // expected model
        Session expectedSession = new Session(SESSIONNAME_WEEK_TWO, SESSIONDATE_WEEK_ONE);
        String expectedMessage = String.format(EditSessionCommand.MESSAGE_EDIT_SESSION_SUCCESS,
                SESSION_WEEK_ONE, expectedSession);

        Model expectedModel = new ModelManager(getTypicalSessionList(model.getStudentList().getStudentList()),
                new StudentList(model.getStudentList()), new UserPrefs());

        expectedModel.addSession(expectedSession);

        assertCommandSuccess(editSessionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editOnlySessionDate_success() {
        // actual model
        model.addSession(SESSION_WEEK_ONE);

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder()
                .withSessionDate(SESSIONDATE_WEEK_TWO_STRING).build();

        EditSessionCommand editSessionCommand = new EditSessionCommand(SESSIONNAME_WEEK_ONE, descriptor);

        // expected model
        Session expectedSession = new Session(SESSIONNAME_WEEK_ONE, SESSIONDATE_WEEK_TWO);
        String expectedMessage = String.format(EditSessionCommand.MESSAGE_EDIT_SESSION_SUCCESS,
                SESSION_WEEK_ONE, expectedSession);

        Model expectedModel = new ModelManager(getTypicalSessionList(model.getStudentList().getStudentList()),
                new StudentList(model.getStudentList()), new UserPrefs());

        expectedModel.addSession(expectedSession);

        assertCommandSuccess(editSessionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        // actual model
        model.addSession(SESSION_WEEK_ONE);

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder().build();
        EditSessionCommand editSessionCommand = new EditSessionCommand(SESSIONNAME_WEEK_ONE, descriptor);

        // expected model (nothing changes)
        String expectedMessage = String.format(EditSessionCommand.MESSAGE_EDIT_SESSION_SUCCESS,
                SESSION_WEEK_ONE, SESSION_WEEK_ONE);

        Model expectedModel = new ModelManager(getTypicalSessionList(model.getStudentList().getStudentList()),
                new StudentList(model.getStudentList()), new UserPrefs());

        expectedModel.addSession(SESSION_WEEK_ONE);

        assertCommandSuccess(editSessionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateSessionAfterEdit_failure() {
        // actual model
        model.addSession(SESSION_WEEK_ONE);
        model.addSession(SESSION_WEEK_THREE);

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder()
                .withSessionName(SESSIONNAME_WEEK_THREE_STRING).build();

        EditSessionCommand editSessionCommand = new EditSessionCommand(SESSIONNAME_WEEK_ONE, descriptor);

        assertCommandFailure(editSessionCommand, model, EditSessionCommand.MESSAGE_DUPLICATE_SESSION);
    }

    @Test
    public void execute_sessionNameDoesNotExist_failure() {
        // actual model
        model.addSession(SESSION_WEEK_ONE);

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder()
                .withSessionName(SESSIONNAME_WEEK_TWO_STRING).build();

        EditSessionCommand editSessionCommand = new EditSessionCommand(SESSIONNAME_WEEK_TWO, descriptor);

        assertCommandFailure(editSessionCommand, model, MESSAGE_SESSION_DOES_NOT_EXIST);
    }

    @Test
    public void equals() {
        final SessionName sessionName = new SessionName(CommandTestUtil.VALID_SESSIONNAME_REC);
        final EditSessionCommand standardCommand = new EditSessionCommand(sessionName, DESC_REC);

        // same values -> returns true
        EditSessionCommand.EditSessionDescriptor copyDescriptor =
                new EditSessionCommand.EditSessionDescriptor(DESC_REC);
        EditSessionCommand commandWithSameValues = new EditSessionCommand(sessionName, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearStudentListCommand()));

        // different session name -> returns false
        SessionName name = new SessionName(VALID_SESSIONNAME_CON);
        assertFalse(standardCommand.equals(new EditSessionCommand(name, DESC_REC)));

        // same descriptor object -> returns true
        assertTrue(copyDescriptor.equals(copyDescriptor));

        // different type -> returns false
        assertFalse(copyDescriptor.equals(standardCommand));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditSessionCommand(sessionName, DESC_CON)));
    }
}
