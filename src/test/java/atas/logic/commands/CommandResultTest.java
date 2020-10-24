package atas.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.ui.Tab;

public class CommandResultTest {
    @Test
    public void equals() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, null, false, false, false)));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, null, false, false, false)));

        // different switchTab value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, Tab.STUDENTS, false, false, false)));

        // different editMemo value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, null, true, false, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, null, false, true, false)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, null, false,
                false, false).hashCode());

        // different switchTab value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, Tab.STUDENTS, false,
                false, false).hashCode());

        // different editMemo value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, null, true,
                false, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, null, false,
                true, false).hashCode());

        // different isEnterSession value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, null, false,
                false, true).hashCode());
    }

    @Test
    public void getters() {
        CommandResult commandResult = new CommandResult("feedback", true, Tab.CURRENT, true, true, true);

        assertTrue(commandResult.isShowHelp());
        assertTrue(commandResult.isSwitchTab());
        assertTrue(commandResult.isExit());
        assertTrue(commandResult.isEditMemo());
        assertTrue(commandResult.isEnterSession());
    }
}
