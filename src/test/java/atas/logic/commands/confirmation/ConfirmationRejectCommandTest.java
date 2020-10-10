package atas.logic.commands.confirmation;

import static atas.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static atas.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static atas.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static atas.testutil.TypicalSessions.getTypicalSessionList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.logic.commands.studentlist.ClearStudentsCommand;
import atas.logic.commands.studentlist.DeleteStudentCommand;
import atas.logic.commands.studentlist.EditStudentCommand;
import atas.model.Model;
import atas.model.ModelManager;
import atas.model.UserPrefs;
import atas.model.person.Person;
import atas.testutil.EditPersonDescriptorBuilder;
import atas.testutil.ModelManagerBuilder;
import atas.testutil.PersonBuilder;

public class ConfirmationRejectCommandTest {
    private Model model = ModelManagerBuilder.buildTypicalModelManager();

    @Test
    public void execute_rejectDeleteStudentCommand_success() {
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);
        String expectedMessage = String.format(ConfirmationRejectCommand.MESSAGE_REJECT_COMMAND, deleteStudentCommand);

        ModelManager expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                model.getAddressBook(), new UserPrefs());

        ConfirmationRejectCommand confirmationRejectCommand = new ConfirmationRejectCommand(deleteStudentCommand);
        assertCommandSuccess(confirmationRejectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_rejectEditStudentCommand_success() {
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_PERSON,
            new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(ConfirmationRejectCommand.MESSAGE_REJECT_COMMAND, editStudentCommand);

        ModelManager expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                model.getAddressBook(), new UserPrefs());

        ConfirmationRejectCommand confirmationRejectCommand = new ConfirmationRejectCommand(editStudentCommand);
        assertCommandSuccess(confirmationRejectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_rejectClearStudentsCommand_success() {
        ClearStudentsCommand clearStudentsCommand = new ClearStudentsCommand();
        Model expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                model.getAddressBook(), new UserPrefs());
        String expectedMessage = String.format(ConfirmationRejectCommand.MESSAGE_REJECT_COMMAND, clearStudentsCommand);

        ConfirmationRejectCommand confirmationRejectCommand = new ConfirmationRejectCommand(clearStudentsCommand);
        assertCommandSuccess(confirmationRejectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteStudentCommand firstDeleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);
        DeleteStudentCommand secondDeleteStudentCommand = new DeleteStudentCommand(INDEX_SECOND_PERSON);

        Person editedPerson = new PersonBuilder().build();
        EditStudentCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_PERSON, descriptor);

        ClearStudentsCommand clearStudentsCommand = new ClearStudentsCommand();

        ConfirmationRejectCommand confirmationRejectDeleteStudentCommand = new ConfirmationRejectCommand(
                firstDeleteStudentCommand);
        ConfirmationRejectCommand confirmationRejectEditStudentCommand = new ConfirmationRejectCommand(
                editStudentCommand);
        ConfirmationRejectCommand confirmationRejectClearStudentsCommand = new ConfirmationRejectCommand(
                clearStudentsCommand);

        // Check if confirmationRejectDangerousCommand equals confirmationRejectSameDangerousCommand
        assertTrue(confirmationRejectDeleteStudentCommand.equals(confirmationRejectDeleteStudentCommand));
        assertTrue(confirmationRejectEditStudentCommand.equals(confirmationRejectEditStudentCommand));
        assertTrue(confirmationRejectClearStudentsCommand.equals(confirmationRejectClearStudentsCommand));

        // Check if confirmationRejectDangerousCommand equals confirmationRejectAnotherDangerousCommand
        assertFalse(confirmationRejectDeleteStudentCommand.equals(confirmationRejectClearStudentsCommand));
        assertFalse(confirmationRejectDeleteStudentCommand.equals(confirmationRejectEditStudentCommand));
        assertFalse(confirmationRejectEditStudentCommand.equals(confirmationRejectClearStudentsCommand));

        // Check if confirmationRejectDeleteStudentCommand equals confirmationRejectAnotherDeleteStudentCommand
        assertFalse(confirmationRejectDeleteStudentCommand.equals(new ConfirmationRejectCommand(
                secondDeleteStudentCommand)));

        // Check if ConfirmationRejectCommand equals another subclass of ConfirmCommand
        assertFalse(confirmationRejectDeleteStudentCommand.equals(new ConfirmationAcceptCommand(
                firstDeleteStudentCommand)));
    }
}
