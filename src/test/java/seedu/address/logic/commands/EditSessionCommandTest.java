package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CON;
import static seedu.address.logic.commands.CommandTestUtil.DESC_REC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SESSIONNAME_CON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SESSIONNAME_REC;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalSessions.SESSIONDATE_WEEK_ONE;
import static seedu.address.testutil.TypicalSessions.SESSIONDATE_WEEK_TWO;
import static seedu.address.testutil.TypicalSessions.SESSIONDATE_WEEK_TWO_STRING;
import static seedu.address.testutil.TypicalSessions.SESSIONNAME_WEEK_ONE;
import static seedu.address.testutil.TypicalSessions.SESSIONNAME_WEEK_THREE_STRING;
import static seedu.address.testutil.TypicalSessions.SESSIONNAME_WEEK_TWO;
import static seedu.address.testutil.TypicalSessions.SESSIONNAME_WEEK_TWO_STRING;
import static seedu.address.testutil.TypicalSessions.SESSION_WEEK_ONE;
import static seedu.address.testutil.TypicalSessions.SESSION_WEEK_THREE;
import static seedu.address.testutil.TypicalSessions.SESSION_WEEK_TWO;
import static seedu.address.testutil.TypicalSessions.getTypicalSessionList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.attendance.Session;
import seedu.address.model.attendance.SessionName;
import seedu.address.testutil.EditSessionDescriptorBuilder;
import seedu.address.testutil.ModelManagerBuilder;



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

        Model expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                new AddressBook(model.getAddressBook()), new UserPrefs());

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

        Model expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                new AddressBook(model.getAddressBook()), new UserPrefs());

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

        Model expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                new AddressBook(model.getAddressBook()), new UserPrefs());

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

        Model expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                new AddressBook(model.getAddressBook()), new UserPrefs());

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

        assertCommandFailure(editSessionCommand, model, Messages.MESSAGE_SESSION_DOES_NOT_EXIST);
    }

    @Test
    public void equals() {
        final SessionName sessionName = new SessionName(VALID_SESSIONNAME_REC);
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
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different session name -> returns false
        SessionName name = new SessionName(VALID_SESSIONNAME_CON);
        assertFalse(standardCommand.equals(new EditSessionCommand(name, DESC_REC)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditSessionCommand(sessionName, DESC_CON)));
    }
}
