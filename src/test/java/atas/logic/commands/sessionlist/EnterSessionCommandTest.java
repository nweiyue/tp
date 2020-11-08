package atas.logic.commands.sessionlist;

import static atas.commons.core.Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX;
import static atas.logic.commands.sessionlist.EnterSessionCommand.MESSAGE_ALREADY_IN_SESSION;
import static atas.testutil.Assert.assertThrows;
import static atas.testutil.TypicalIndexes.INDEX_FIRST_SESSION;
import static atas.testutil.TypicalIndexes.INDEX_SECOND_SESSION;
import static atas.testutil.TypicalSessions.SESSION_WEEK_ONE;
import static atas.testutil.TypicalSessions.SESSION_WEEK_TWO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.commons.core.index.Index;
import atas.logic.commands.CommandResult;
import atas.logic.commands.exceptions.CommandException;
import atas.model.Model;
import atas.model.ModelManager;
import atas.testutil.ModelManagerBuilder;

public class EnterSessionCommandTest {

    private Model model = ModelManagerBuilder.buildTypicalModelManager();
    private Index indexOne = Index.fromOneBased(1);
    private EnterSessionCommand enterSessionCommand = new EnterSessionCommand(indexOne);

    @Test
    void testEquals() {
        EnterSessionCommand enterSessionCommand = new EnterSessionCommand(Index.fromZeroBased(1));
        EnterSessionCommand enterSessionCommandCopy = new EnterSessionCommand(Index.fromZeroBased(1));
        EnterSessionCommand enterSessionCommandNextCopy = new EnterSessionCommand(Index.fromZeroBased(2));

        // same object -> returns true
        assertTrue(enterSessionCommand.equals(enterSessionCommandCopy));

        // different types -> returns false
        assertFalse(enterSessionCommand.equals(1));

        // null -> returns false
        assertFalse(enterSessionCommand.equals(null));

        // different session index -> returns false
        assertFalse(enterSessionCommand.equals(enterSessionCommandNextCopy));
    }

    @Test
    public void execute_validSessionIndex_success() {

        String expectedMessage = String.format(EnterSessionCommand.MESSAGE_SUCCESS, indexOne.getOneBased());

        ModelManager expectedModel = ModelManagerBuilder.buildTypicalModelManager();

        try {
            CommandResult commandResult = enterSessionCommand.execute(model);
            assertTrue(commandResult.isEnterSession());
            assertEquals(expectedMessage, commandResult.getFeedbackToUser());
            assertEquals(expectedModel, model);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    @Test
    public void execute_invalidSessionIndex_throwsCommandException() {

        Index index = Index.fromOneBased(6);
        EnterSessionCommand enterSessionSixCommand = new EnterSessionCommand(index);

        String expectedMessage = MESSAGE_INVALID_SESSION_DISPLAYED_INDEX;

        assertThrows(CommandException.class, expectedMessage, () -> enterSessionSixCommand.execute(model));
    }

    @Test
    public void execute_alreadyInSession_throwsCommandException() {
        Index index = Index.fromOneBased(2);
        EnterSessionCommand enterSessionTwoCommand = new EnterSessionCommand(index);
        model.addSession(SESSION_WEEK_ONE);
        model.addSession(SESSION_WEEK_TWO);
        model.enterSession(index);
        String expectedMessage = String.format(MESSAGE_ALREADY_IN_SESSION, 2);

        assertThrows(CommandException.class, expectedMessage, () -> enterSessionTwoCommand.execute(model));
    }

    @Test
    public void equals() {
        Index index1 = Index.fromOneBased(1);
        Index index2 = Index.fromOneBased(2);

        EnterSessionCommand enterSessionCommand1 = new EnterSessionCommand(index1);
        EnterSessionCommand enterSessionCommand2 = new EnterSessionCommand(index2);

        // same object -> returns true
        assertTrue(enterSessionCommand1.equals(enterSessionCommand1));

        // same sessionName -> returns true
        EnterSessionCommand enterSessionCommandNext = new EnterSessionCommand(index1);
        assertTrue(enterSessionCommand1.equals(enterSessionCommandNext));

        // different types -> returns false
        assertFalse(enterSessionCommand1.equals(1));

        // null -> returns false
        assertFalse(enterSessionCommand1.equals(null));

        // different sessionName -> returns false
        assertFalse(enterSessionCommand1.equals(enterSessionCommand2));
    }

    @Test
    public void toStringTest() {
        EnterSessionCommand enterSessionCommand1 = new EnterSessionCommand(INDEX_FIRST_SESSION);
        EnterSessionCommand enterSessionCommand2 = new EnterSessionCommand(INDEX_SECOND_SESSION);

        assertEquals(enterSessionCommand1.toString(), "Enter 1");
        assertEquals(enterSessionCommand2.toString(), "Enter 2");
    }
}
