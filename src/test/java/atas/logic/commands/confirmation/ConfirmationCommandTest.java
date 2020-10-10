package atas.logic.commands.confirmation;

import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static atas.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static atas.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static atas.testutil.TypicalSessions.getTypicalSessionList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.logic.commands.CommandTestUtil;
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

public class ConfirmationCommandTest {
    private Model model = ModelManagerBuilder.buildTypicalModelManager();

    @Test
    public void execute_deleteStudentCommandConfirmation_success() {
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ConfirmationCommand.MESSAGE_CONFIRMATION_DELETE,
                INDEX_FIRST_PERSON.getOneBased());

        ModelManager expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                model.getAddressBook(), new UserPrefs());

        ConfirmationCommand confirmationCommand = new ConfirmationCommand(deleteStudentCommand);
        assertCommandSuccess(confirmationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editStudentCommandConfirmation_success() {
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_PERSON,
            new EditPersonDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        String expectedMessage = String.format(ConfirmationCommand.MESSAGE_CONFIRMATION_EDIT,
                INDEX_FIRST_PERSON.getOneBased());

        Model expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                new AddressBook(model.getAddressBook()), new UserPrefs());

        ConfirmationCommand confirmationCommand = new ConfirmationCommand(editStudentCommand);
        assertCommandSuccess(confirmationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_clearStudentsCommandConfirmation_success() {
        ClearStudentsCommand clearStudentsCommand = new ClearStudentsCommand();
        Model expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                model.getAddressBook(), new UserPrefs());

        String expectedMessage = ConfirmationCommand.MESSAGE_CONFIRMATION_CLEAR;

        ConfirmationCommand confirmationCommand = new ConfirmationCommand(clearStudentsCommand);
        assertCommandSuccess(confirmationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteStudentCommand firstDeleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);
        DeleteStudentCommand secondDeleteStudentCommand = new DeleteStudentCommand(INDEX_SECOND_PERSON);

        Person editedPerson = new PersonBuilder().build();
        EditStudentCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_PERSON, descriptor);

        ClearStudentsCommand clearStudentsCommand = new ClearStudentsCommand();

        ConfirmationCommand confirmationDeleteStudentCommand = new ConfirmationCommand(firstDeleteStudentCommand);
        ConfirmationCommand confirmationEditStudentCommand = new ConfirmationCommand(editStudentCommand);
        ConfirmationCommand confirmationClearStudentsCommand = new ConfirmationCommand(clearStudentsCommand);

        // Check if confirmationDangerousCommand equals confirmationSameDangerousCommand
        assertTrue(confirmationDeleteStudentCommand.equals(confirmationDeleteStudentCommand));
        assertTrue(confirmationEditStudentCommand.equals(confirmationEditStudentCommand));
        assertTrue(confirmationClearStudentsCommand.equals(confirmationClearStudentsCommand));

        // Check if confirmationDangerousCommand equals confirmationAnotherDangerousCommand
        assertFalse(confirmationDeleteStudentCommand.equals(confirmationClearStudentsCommand));
        assertFalse(confirmationDeleteStudentCommand.equals(confirmationEditStudentCommand));
        assertFalse(confirmationEditStudentCommand.equals(confirmationClearStudentsCommand));

        // Check if confirmationDeleteStudentCommand equals another subclass of ConfirmCommand
        assertFalse(confirmationDeleteStudentCommand.equals(new ConfirmationRejectCommand(secondDeleteStudentCommand)));
        assertFalse(confirmationDeleteStudentCommand.equals(new ConfirmationAcceptCommand(secondDeleteStudentCommand)));
    }
}
