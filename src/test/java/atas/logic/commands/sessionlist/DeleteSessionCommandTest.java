package atas.logic.commands.sessionlist;

import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static atas.logic.commands.CommandTestUtil.showSessionAtIndex;
import static atas.testutil.TypicalIndexes.INDEX_FIRST_SESSION;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.model.Model;
import atas.model.ModelManager;
import atas.model.UserPrefs;
import atas.model.attendance.Session;
import atas.testutil.ModelManagerBuilder;
import atas.testutil.TypicalSessions;

public class DeleteSessionCommandTest {

    private Model model = ModelManagerBuilder.buildTypicalModelManager();

    @Test
    public void execute_validIndex_success() {
        showSessionAtIndex(model, INDEX_FIRST_SESSION);
        Session sessionToDelete = model.getFilteredSessionList().get(INDEX_FIRST_SESSION.getZeroBased());
        DeleteSessionCommand deleteSessionCommand = new DeleteSessionCommand(INDEX_FIRST_SESSION);

        String expectedMessage = String.format(DeleteSessionCommand.MESSAGE_DELETE_SESSION_SUCCESS, sessionToDelete);

        ModelManager expectedModel = new ModelManager(TypicalSessions.getTypicalSessionList(
                model.getAddressBook().getPersonList()), model.getAddressBook(), new UserPrefs());

        expectedModel.deleteSession(sessionToDelete, INDEX_FIRST_SESSION);
        showNoSession(expectedModel);
        assertCommandSuccess(deleteSessionCommand, model, expectedMessage, expectedModel);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoSession (Model model) {
        model.updateFilteredSessionList(s -> false);

        assertTrue(model.getFilteredSessionList().isEmpty());
    }
}
