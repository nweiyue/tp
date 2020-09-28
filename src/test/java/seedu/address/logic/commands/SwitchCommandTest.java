package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SwitchCommand.MESSAGE_INVALID_TAB;
import static seedu.address.logic.commands.SwitchCommand.MESSAGE_SWITCH_TAB_SUCCESS;
import static seedu.address.testutil.TypicalTabNames.ATTENDANCE_TAB_NAME;
import static seedu.address.testutil.TypicalTabNames.CLASSES_TAB_NAME;
import static seedu.address.testutil.TypicalTabNames.INVALID_TAB_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.ui.Tab;

public class SwitchCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_validTab_success() {
        String expectedMessage = String.format(MESSAGE_SWITCH_TAB_SUCCESS,
                Tab.CLASSES.toString().toLowerCase());
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, false, Tab.CLASSES, false);
        SwitchCommand switchCommand = new SwitchCommand(CLASSES_TAB_NAME);

        assertCommandSuccess(switchCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidTab_throwsCommandException() {
        SwitchCommand switchCommand = new SwitchCommand(INVALID_TAB_NAME);

        assertCommandFailure(switchCommand, model, MESSAGE_INVALID_TAB);
    }

    @Test
    public void equals() {
        SwitchCommand switchFirstCommand = new SwitchCommand(CLASSES_TAB_NAME);
        SwitchCommand switchSecondCommand = new SwitchCommand(ATTENDANCE_TAB_NAME);

        // same object -> returns true
        assertTrue(switchFirstCommand.equals(switchFirstCommand));

        // same values -> returns true
        SwitchCommand switchCommandCopy = new SwitchCommand(CLASSES_TAB_NAME);
        assertTrue(switchFirstCommand.equals(switchCommandCopy));

        // different types -> returns false
        assertFalse(switchFirstCommand.equals(1));

        // null -> returns false
        assertFalse(switchFirstCommand.equals(null));

        // different tabs -> returns false
        assertFalse(switchFirstCommand.equals(switchSecondCommand));
    }
}
