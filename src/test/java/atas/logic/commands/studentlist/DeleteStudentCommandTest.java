package atas.logic.commands.studentlist;

import static atas.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static atas.logic.commands.CommandTestUtil.assertCommandFailure;
import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static atas.logic.commands.CommandTestUtil.showPersonAtIndex;
import static atas.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static atas.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static atas.testutil.TypicalSessions.getTypicalSessionList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.commons.core.index.Index;
import atas.model.Model;
import atas.model.ModelManager;
import atas.model.UserPrefs;
import atas.model.person.Person;
import atas.testutil.ModelManagerBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteStudentCommand}.
 */
public class DeleteStudentCommandTest {

    private Model model = ModelManagerBuilder.buildTypicalModelManager();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete, INDEX_FIRST_PERSON);

        assertCommandSuccess(deleteStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(outOfBoundIndex);

        assertCommandFailure(deleteStudentCommand, model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        Model expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete, INDEX_FIRST_PERSON);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(outOfBoundIndex);

        assertCommandFailure(deleteStudentCommand, model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteStudentCommand deleteFirstStudentCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);
        DeleteStudentCommand deleteSecondStudentCommand = new DeleteStudentCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstStudentCommand.equals(deleteFirstStudentCommand));

        // same values -> returns true
        DeleteStudentCommand deleteFirstStudentCommandCopy = new DeleteStudentCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstStudentCommand.equals(deleteFirstStudentCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstStudentCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstStudentCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstStudentCommand.equals(deleteSecondStudentCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
