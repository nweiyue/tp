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
    }
}
