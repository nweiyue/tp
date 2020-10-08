package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.atascommands.SwitchCommand.MESSAGE_INVALID_TAB;
import static seedu.address.logic.commands.atascommands.SwitchCommand.MESSAGE_SWITCH_TAB_SUCCESS;
import static seedu.address.testutil.TypicalTabNames.ATTENDANCE_TAB_NAME;
import static seedu.address.testutil.TypicalTabNames.CLASSES_TAB_NAME;
import static seedu.address.testutil.TypicalTabNames.INVALID_TAB_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.atascommands.SwitchCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.ui.Tab;

public class SwitchCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_switchClassesTab_success() {
        String expectedMessage = String.format(MESSAGE_SWITCH_TAB_SUCCESS,
                Tab.CLASSES.toString().toLowerCase());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, Tab.CLASSES, false);
        SwitchCommand switchCommand = new SwitchCommand(CLASSES_TAB_NAME);

        assertCommandSuccess(switchCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_switchAttendanceTab_success() {
        String expectedMessage = String.format(MESSAGE_SWITCH_TAB_SUCCESS,
                Tab.ATTENDANCE.toString().toLowerCase());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, Tab.ATTENDANCE, false);
        SwitchCommand switchCommand = new SwitchCommand(ATTENDANCE_TAB_NAME);

        assertCommandSuccess(switchCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidTabName_throwsCommandException() {
        SwitchCommand switchCommand = new SwitchCommand(INVALID_TAB_NAME);

        assertCommandFailure(switchCommand, model, MESSAGE_INVALID_TAB);
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
