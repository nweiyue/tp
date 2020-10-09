package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalSessions.getTypicalSessionList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.confirmationcommands.ConfirmationAcceptCommand;
import seedu.address.logic.commands.confirmationcommands.ConfirmationRejectCommand;
import seedu.address.logic.commands.studentlistcommands.ClearCommand;
import seedu.address.logic.commands.studentlistcommands.DeleteCommand;
import seedu.address.logic.commands.studentlistcommands.EditCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.ModelManagerBuilder;
import seedu.address.testutil.PersonBuilder;

public class ConfirmationAcceptCommandTest {
    private Model model = ModelManagerBuilder.buildTypicalModelManager();

    @Test
    public void execute_acceptValidDeleteCommand_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete, INDEX_FIRST_PERSON);

        ConfirmationAcceptCommand confirmationAcceptCommand = new ConfirmationAcceptCommand(deleteCommand);
        assertCommandSuccess(confirmationAcceptCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidDeleteCommand_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        ConfirmationAcceptCommand confirmationAcceptCommand = new ConfirmationAcceptCommand(deleteCommand);
        assertCommandFailure(confirmationAcceptCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_acceptValidEditCommand_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
            new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        ConfirmationAcceptCommand confirmationAcceptCommand = new ConfirmationAcceptCommand(editCommand);
        assertCommandSuccess(confirmationAcceptCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidEditCommand_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        ConfirmationAcceptCommand confirmationAcceptCommand = new ConfirmationAcceptCommand(editCommand);
        assertCommandFailure(confirmationAcceptCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_acceptClearCommand_success() {
        ClearCommand clearCommand = new ClearCommand();
        ModelManager expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
            model.getAddressBook(), new UserPrefs());

        ConfirmationAcceptCommand confirmationAcceptCommand = new ConfirmationAcceptCommand(clearCommand);
        assertCommandSuccess(confirmationAcceptCommand, model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        DeleteCommand firstDeleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        DeleteCommand secondDeleteCommand = new DeleteCommand(INDEX_SECOND_PERSON);

        Person editedPerson = new PersonBuilder().build();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        ClearCommand clearCommand = new ClearCommand();

        ConfirmationAcceptCommand confirmationAcceptDeleteCommand = new ConfirmationAcceptCommand(firstDeleteCommand);
        ConfirmationAcceptCommand confirmationAcceptEditCommand = new ConfirmationAcceptCommand(editCommand);
        ConfirmationAcceptCommand confirmationAcceptClearCommand = new ConfirmationAcceptCommand(clearCommand);

        // Check if confirmationAcceptDangerousCommand equals confirmationAcceptSameDangerousCommand
        assertTrue(confirmationAcceptDeleteCommand.equals(confirmationAcceptDeleteCommand));
        assertTrue(confirmationAcceptEditCommand.equals(confirmationAcceptEditCommand));
        assertTrue(confirmationAcceptClearCommand.equals(confirmationAcceptClearCommand));

        // Check if confirmationAcceptDangerousCommand equals confirmationAcceptAnotherDangerousCommand
        assertFalse(confirmationAcceptDeleteCommand.equals(confirmationAcceptClearCommand));
        assertFalse(confirmationAcceptDeleteCommand.equals(confirmationAcceptEditCommand));
        assertFalse(confirmationAcceptEditCommand.equals(confirmationAcceptClearCommand));

        // Check if confirmationAcceptDeleteCommand equals confirmationAcceptAnotherDeleteCommand
        assertFalse(confirmationAcceptDeleteCommand.equals(new ConfirmationAcceptCommand(secondDeleteCommand)));

        // Check if confirmationAcceptCommand equals another subclass of ConfirmCommand
        assertFalse(confirmationAcceptDeleteCommand.equals(new ConfirmationRejectCommand(firstDeleteCommand)));
    }
}
