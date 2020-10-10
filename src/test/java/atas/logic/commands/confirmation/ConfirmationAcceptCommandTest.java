package atas.logic.commands.confirmation;

import static atas.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static atas.logic.commands.CommandTestUtil.VALID_NAME_BOB;
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
import atas.logic.commands.studentlist.ClearStudentsCommand;
import atas.logic.commands.studentlist.DeleteStudentCommand;
import atas.logic.commands.studentlist.EditStudentCommand;
import atas.model.AddressBook;
import atas.model.Model;
import atas.model.ModelManager;
import atas.model.UserPrefs;
import atas.model.person.Person;
import atas.testutil.EditPersonDescriptorBuilder;
import atas.testutil.ModelManagerBuilder;
import atas.testutil.PersonBuilder;

public class ConfirmationAcceptCommandTest {
    private Model model = ModelManagerBuilder.buildTypicalModelManager();

    @Test
    public void execute_acceptValidDeleteStudentCommand_success() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_PERSON_SUCCESS, personToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete, INDEX_FIRST_PERSON);

        ConfirmationAcceptCommand confirmationAcceptCommand = new ConfirmationAcceptCommand(deleteStudentCommand);
        assertCommandSuccess(confirmationAcceptCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidDeleteStudentCommand_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(outOfBoundIndex);

        ConfirmationAcceptCommand confirmationAcceptCommand = new ConfirmationAcceptCommand(deleteStudentCommand);
        assertCommandFailure(confirmationAcceptCommand, model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_acceptValidEditStudentCommand_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personInFilteredList = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_PERSON,
            new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditStudentCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        ConfirmationAcceptCommand confirmationAcceptCommand = new ConfirmationAcceptCommand(editStudentCommand);
        assertCommandSuccess(confirmationAcceptCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidEditStudentCommand_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        EditStudentCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName(VALID_NAME_BOB).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(outOfBoundIndex, descriptor);

        ConfirmationAcceptCommand confirmationAcceptCommand = new ConfirmationAcceptCommand(editStudentCommand);
        assertCommandFailure(confirmationAcceptCommand, model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_acceptClearStudentsCommand_success() {
        ClearStudentsCommand clearStudentsCommand = new ClearStudentsCommand();
        ModelManager expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
            model.getAddressBook(), new UserPrefs());

        ConfirmationAcceptCommand confirmationAcceptCommand = new ConfirmationAcceptCommand(clearStudentsCommand);
        assertCommandSuccess(confirmationAcceptCommand, model, ClearStudentsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        DeleteStudentCommand firstDeleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);
        DeleteStudentCommand secondDeleteStudentCommand = new DeleteStudentCommand(INDEX_SECOND_PERSON);

        Person editedPerson = new PersonBuilder().build();
        EditStudentCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_PERSON, descriptor);

        ClearStudentsCommand clearStudentsCommand = new ClearStudentsCommand();

        ConfirmationAcceptCommand confirmationAcceptDeleteStudentCommand = new ConfirmationAcceptCommand(
                firstDeleteStudentCommand);
        ConfirmationAcceptCommand confirmationAcceptEditStudentCommand = new ConfirmationAcceptCommand(
                editStudentCommand);
        ConfirmationAcceptCommand confirmationAcceptClearStudentsCommand = new ConfirmationAcceptCommand(
                clearStudentsCommand);

        // Check if confirmationAcceptDangerousCommand equals confirmationAcceptSameDangerousCommand
        assertTrue(confirmationAcceptDeleteStudentCommand.equals(confirmationAcceptDeleteStudentCommand));
        assertTrue(confirmationAcceptEditStudentCommand.equals(confirmationAcceptEditStudentCommand));
        assertTrue(confirmationAcceptClearStudentsCommand.equals(confirmationAcceptClearStudentsCommand));

        // Check if confirmationAcceptDangerousCommand equals confirmationAcceptAnotherDangerousCommand
        assertFalse(confirmationAcceptDeleteStudentCommand.equals(confirmationAcceptClearStudentsCommand));
        assertFalse(confirmationAcceptDeleteStudentCommand.equals(confirmationAcceptEditStudentCommand));
        assertFalse(confirmationAcceptEditStudentCommand.equals(confirmationAcceptClearStudentsCommand));

        // Check if confirmationAcceptDeleteStudentCommand equals confirmationAcceptAnotherDeleteCommand
        assertFalse(confirmationAcceptDeleteStudentCommand.equals(new ConfirmationAcceptCommand(
                secondDeleteStudentCommand)));

        // Check if confirmationAcceptCommand equals another subclass of ConfirmCommand
        assertFalse(confirmationAcceptDeleteStudentCommand.equals(new ConfirmationRejectCommand(
                firstDeleteStudentCommand)));
    }
}
