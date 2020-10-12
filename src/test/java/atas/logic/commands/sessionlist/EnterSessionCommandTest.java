package atas.logic.commands.sessionlist;
  
import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static atas.testutil.TypicalSessions.SESSION_WEEK_ONE;
import static atas.testutil.TypicalSessions.getTypicalSessionList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.commons.core.index.Index;
import atas.model.AddressBook;
import atas.model.Model;
import atas.model.ModelManager;
import atas.model.UserPrefs;
import atas.testutil.ModelManagerBuilder;

class EnterSessionCommandTest {

    private Model model = ModelManagerBuilder.buildTypicalModelManager();

    @Test
    void execute_validSessionIndex_success() {
        model.addSession(SESSION_WEEK_ONE);

        EnterSessionCommand enterSessionCommand = new EnterSessionCommand(Index.fromZeroBased(1));

        // expected model
        String expectedMessage = String.format(EnterSessionCommand.MESSAGE_SUCCESS,
            SESSION_WEEK_ONE);

        Model expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
            new AddressBook(model.getAddressBook()), new UserPrefs());

        expectedModel.addSession(SESSION_WEEK_ONE);
        expectedModel.enterSession(Index.fromZeroBased(1));

        assertCommandSuccess(enterSessionCommand, model, expectedMessage, expectedModel);
    }

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

    public void execute_validSessionIndex_success() {

        Index index = Index.fromOneBased(1);
        EnterSessionCommand enterSessionCommand = new EnterSessionCommand(index);

        String expectedMessage = String.format(EnterSessionCommand.MESSAGE_SUCCESS, index.getOneBased());

        ModelManager expectedModel = ModelManagerBuilder.buildTypicalModelManager();
        expectedModel.enterSession(index);

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
        EnterSessionCommand enterSessionCommand = new EnterSessionCommand(index);

        String expectedMessage = MESSAGE_INVALID_SESSION_DISPLAYED_INDEX;

        assertThrows(CommandException.class, expectedMessage, () -> enterSessionCommand.execute(model));
    }

    @Test
    public void equals() {
        Index index1 = Index.fromOneBased(1);
        Index index2 = Index.fromOneBased(2);

        EnterSessionCommand enterSessionCommand1 = new EnterSessionCommand(index1);
        EnterSessionCommand enterSessionCommand2 = new EnterSessionCommand(index2);

        // same object -> returns true
        assertTrue(enterSessionCommand1.equals(enterSessionCommand1));

        // same sessioName -> returns true
        EnterSessionCommand enterSessionCommandCopy = new EnterSessionCommand(index1);
        assertTrue(enterSessionCommand1.equals(enterSessionCommandCopy));

        // different types -> returns false
        assertFalse(enterSessionCommand1.equals(1));

        // null -> returns false
        assertFalse(enterSessionCommand1.equals(null));

        // different sessionName -> returns false
        assertFalse(enterSessionCommand1.equals(enterSessionCommand2));
    }
}
