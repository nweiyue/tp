package atas.logic.commands.atas;

import static atas.logic.commands.CommandTestUtil.assertCommandFailure;
import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static atas.testutil.TypicalTabNames.ATTENDANCE_TAB_NAME;
import static atas.testutil.TypicalTabNames.CLASSES_TAB_NAME;
import static atas.testutil.TypicalTabNames.INVALID_TAB_NAME;
import static atas.testutil.TypicalTabNames.SESSIONS_TAB_NAME;
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
    public void execute_switchClassesTab_success() {
        String expectedMessage = String.format(SwitchCommand.MESSAGE_SWITCH_TAB_SUCCESS,
                Tab.STUDENTS.toString().toLowerCase());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, Tab.STUDENTS, false);
        SwitchCommand switchCommand = new SwitchCommand(CLASSES_TAB_NAME);

        assertCommandSuccess(switchCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_switchAttendanceTab_success() {
        String expectedMessage = String.format(SwitchCommand.MESSAGE_SWITCH_TAB_SUCCESS,
                Tab.SESSIONS.toString().toLowerCase());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, Tab.SESSIONS, false);
        SwitchCommand switchCommand = new SwitchCommand(ATTENDANCE_TAB_NAME);

        assertCommandSuccess(switchCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_switchCurrentTab_success() {
        String expectedMessage = String.format(SwitchCommand.MESSAGE_SWITCH_TAB_SUCCESS,
                Tab.CURRENT.toString().toLowerCase());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, Tab.CURRENT, false);
        SwitchCommand switchCommand = new SwitchCommand(SESSIONS_TAB_NAME);

        assertCommandSuccess(switchCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidTabName_throwsCommandException() {
        SwitchCommand switchCommand = new SwitchCommand(INVALID_TAB_NAME);

        assertCommandFailure(switchCommand, model, SwitchCommand.MESSAGE_INVALID_TAB);
    }

    @Test
    public void equals() {
        SwitchCommand switchToClassesCommand = new SwitchCommand(CLASSES_TAB_NAME);
        SwitchCommand switchToAttendanceCommand = new SwitchCommand(ATTENDANCE_TAB_NAME);
        SwitchCommand switchToInvalidTabCommand = new SwitchCommand(INVALID_TAB_NAME);

        // same object -> returns true
        assertTrue(switchToClassesCommand.equals(switchToClassesCommand));
        assertTrue(switchToAttendanceCommand.equals(switchToAttendanceCommand));

        // same values -> returns true
        SwitchCommand switchToClassesCommandCopy = new SwitchCommand(CLASSES_TAB_NAME);
        assertTrue(switchToClassesCommand.equals(switchToClassesCommandCopy));
        SwitchCommand switchToAttendanceCommandCopy = new SwitchCommand(ATTENDANCE_TAB_NAME);
        assertTrue(switchToAttendanceCommand.equals(switchToAttendanceCommandCopy));

        // different types -> returns false
        assertFalse(switchToClassesCommand.equals(1));
        assertFalse(switchToAttendanceCommand.equals(1));

        // null -> returns false
        assertFalse(switchToClassesCommand.equals(null));
        assertFalse(switchToAttendanceCommand.equals(null));

        // invalid tabs -> returns false
        assertFalse(switchToClassesCommand.equals(switchToInvalidTabCommand));
        assertFalse(switchToAttendanceCommand.equals(switchToInvalidTabCommand));

        // different tabs -> returns false
        assertFalse(switchToClassesCommand.equals(switchToAttendanceCommand));
    }
}
