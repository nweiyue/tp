package atas.logic.commands.atas;

import static atas.logic.commands.CommandTestUtil.assertCommandFailure;
import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static atas.testutil.TypicalTabNames.ATAS_TAB_NAME;
import static atas.testutil.TypicalTabNames.CURRENT_SESSION_TAB_NAME;
import static atas.testutil.TypicalTabNames.INVALID_TAB_NAME;
import static atas.testutil.TypicalTabNames.MEMO_TAB_NAME;
import static atas.testutil.TypicalTabNames.SESSIONS_TAB_NAME;
import static atas.testutil.TypicalTabNames.STUDENTS_TAB_NAME;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.logic.commands.CommandResult;
import atas.model.Model;
import atas.model.ModelManager;
import atas.ui.Tab;

public class SwitchCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_switchAtasTab_success() {
        String expectedMessage = String.format(SwitchCommand.MESSAGE_SWITCH_TAB_SUCCESS,
                Tab.ATAS.toDisplayName().toLowerCase());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, Tab.ATAS,
                false, false, false);
        SwitchCommand switchCommand = new SwitchCommand(ATAS_TAB_NAME);

        assertCommandSuccess(switchCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_switchStudentsTab_success() {
        String expectedMessage = String.format(SwitchCommand.MESSAGE_SWITCH_TAB_SUCCESS,
                Tab.STUDENTS.toString().toLowerCase());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, Tab.STUDENTS,
                false, false, false);
        SwitchCommand switchCommand = new SwitchCommand(STUDENTS_TAB_NAME);

        assertCommandSuccess(switchCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_switchSessionsTab_success() {
        String expectedMessage = String.format(SwitchCommand.MESSAGE_SWITCH_TAB_SUCCESS,
                Tab.SESSIONS.toString().toLowerCase());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, Tab.SESSIONS,
                false, false, false);
        SwitchCommand switchCommand = new SwitchCommand(SESSIONS_TAB_NAME);

        assertCommandSuccess(switchCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_switchCurrentTab_success() {
        String expectedMessage = String.format(SwitchCommand.MESSAGE_SWITCH_TAB_SUCCESS, Tab.CURRENT.toDisplayName());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, Tab.CURRENT,
                false, false, false);
        SwitchCommand switchCommand = new SwitchCommand(CURRENT_SESSION_TAB_NAME);

        assertCommandSuccess(switchCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_switchMemoTab_success() {
        String expectedMessage = String.format(SwitchCommand.MESSAGE_SWITCH_TAB_SUCCESS,
                Tab.MEMO.toString().toLowerCase());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, Tab.MEMO,
                false, false, false);
        SwitchCommand switchCommand = new SwitchCommand(MEMO_TAB_NAME);

        assertCommandSuccess(switchCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidTabName_throwsCommandException() {
        SwitchCommand switchCommand = new SwitchCommand(INVALID_TAB_NAME);

        assertCommandFailure(switchCommand, model, SwitchCommand.MESSAGE_INVALID_TAB);
    }

    @Test
    public void equals() {
        SwitchCommand switchToStudentsCommand = new SwitchCommand(STUDENTS_TAB_NAME);
        SwitchCommand switchToSessionsCommand = new SwitchCommand(SESSIONS_TAB_NAME);
        SwitchCommand switchToCurrentSessionCommand = new SwitchCommand(CURRENT_SESSION_TAB_NAME);
        SwitchCommand switchToMemoCommand = new SwitchCommand(MEMO_TAB_NAME);
        SwitchCommand switchToInvalidTabCommand = new SwitchCommand(INVALID_TAB_NAME);

        // same object -> returns true
        assertTrue(switchToStudentsCommand.equals(switchToStudentsCommand));
        assertTrue(switchToSessionsCommand.equals(switchToSessionsCommand));
        assertTrue(switchToCurrentSessionCommand.equals(switchToCurrentSessionCommand));
        assertTrue(switchToMemoCommand.equals(switchToMemoCommand));

        // same values -> returns true
        SwitchCommand switchToStudentsCommandCopy = new SwitchCommand(STUDENTS_TAB_NAME);
        assertTrue(switchToStudentsCommand.equals(switchToStudentsCommandCopy));
        SwitchCommand switchToSessionsCommandCopy = new SwitchCommand(SESSIONS_TAB_NAME);
        assertTrue(switchToSessionsCommand.equals(switchToSessionsCommandCopy));
        SwitchCommand switchToCurrentSessionCommandCopy = new SwitchCommand(CURRENT_SESSION_TAB_NAME);
        assertTrue(switchToCurrentSessionCommand.equals(switchToCurrentSessionCommandCopy));
        SwitchCommand switchToMemoCommandCopy = new SwitchCommand(MEMO_TAB_NAME);
        assertTrue(switchToMemoCommand.equals(switchToMemoCommandCopy));

        // different types -> returns false
        assertFalse(switchToStudentsCommand.equals(1));
        assertFalse(switchToSessionsCommand.equals(1));

        // null -> returns false
        assertFalse(switchToStudentsCommand.equals(null));
        assertFalse(switchToSessionsCommand.equals(null));

        // invalid tabs -> returns false
        assertFalse(switchToStudentsCommand.equals(switchToInvalidTabCommand));
        assertFalse(switchToSessionsCommand.equals(switchToInvalidTabCommand));

        // different tabs -> returns false
        assertFalse(switchToStudentsCommand.equals(switchToSessionsCommand));
    }
}
