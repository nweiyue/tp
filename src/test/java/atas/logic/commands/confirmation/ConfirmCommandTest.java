package atas.logic.commands.confirmation;

import static atas.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static atas.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.logic.commands.studentlist.ClearStudentsCommand;
import atas.logic.commands.studentlist.DeleteStudentCommand;
import atas.logic.commands.studentlist.EditStudentCommand;
import atas.model.person.Person;
import atas.testutil.EditPersonDescriptorBuilder;
import atas.testutil.PersonBuilder;

public class ConfirmCommandTest {
    @Test
    public void equals() {
        DeleteStudentCommand firstDeleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);
        DeleteStudentCommand secondDeleteStudentCommand = new DeleteStudentCommand(INDEX_SECOND_PERSON);

        Person editedPerson = new PersonBuilder().build();
        EditStudentCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_PERSON, descriptor);

        ClearStudentsCommand clearStudentsCommand = new ClearStudentsCommand();

        ConfirmCommand confirmationDeleteStudentCommand = new ConfirmationCommand(firstDeleteStudentCommand);
        ConfirmCommand confirmationEditStudentCommand = new ConfirmationCommand(editStudentCommand);
        ConfirmCommand confirmationClearStudentsCommand = new ConfirmationCommand(clearStudentsCommand);

        ConfirmCommand confirmationAcceptDeleteStudentCommand = new ConfirmationAcceptCommand(
                firstDeleteStudentCommand);
        ConfirmCommand confirmationAcceptEditStudentCommand = new ConfirmationAcceptCommand(editStudentCommand);
        ConfirmCommand confirmationAcceptClearStudentsCommand = new ConfirmationAcceptCommand(clearStudentsCommand);

        ConfirmCommand confirmationRejectDeleteStudentCommand = new ConfirmationRejectCommand(
                firstDeleteStudentCommand);
        ConfirmCommand confirmationRejectEditStudentCommand = new ConfirmationRejectCommand(
                editStudentCommand);
        ConfirmCommand confirmationRejectClearStudentsCommand = new ConfirmationRejectCommand(clearStudentsCommand);

        assertTrue(confirmationDeleteStudentCommand.equals(confirmationDeleteStudentCommand));
        assertTrue(confirmationEditStudentCommand.equals(confirmationEditStudentCommand));
        assertTrue(confirmationClearStudentsCommand.equals(confirmationClearStudentsCommand));

        assertFalse(confirmationDeleteStudentCommand.equals(new ConfirmationCommand(secondDeleteStudentCommand)));

        assertFalse(confirmationDeleteStudentCommand.equals(confirmationClearStudentsCommand));
        assertFalse(confirmationDeleteStudentCommand.equals(confirmationEditStudentCommand));
        assertFalse(confirmationEditStudentCommand.equals(confirmationClearStudentsCommand));

        // Make sure there is a clear distinction between accept and reject confirmation command.
        assertFalse(confirmationAcceptDeleteStudentCommand.equals(confirmationRejectDeleteStudentCommand));
        assertFalse(confirmationAcceptClearStudentsCommand.equals(confirmationRejectClearStudentsCommand));
        assertFalse(confirmationAcceptEditStudentCommand.equals(confirmationRejectEditStudentCommand));

        // Make sure there is clear distinction between confirmation and accept/reject confirmation command.
        assertFalse(confirmationClearStudentsCommand.equals(confirmationAcceptClearStudentsCommand));
        assertFalse(confirmationClearStudentsCommand.equals(confirmationRejectClearStudentsCommand));
        assertFalse(confirmationDeleteStudentCommand.equals(confirmationAcceptDeleteStudentCommand));
        assertFalse(confirmationDeleteStudentCommand.equals(confirmationRejectDeleteStudentCommand));
        assertFalse(confirmationEditStudentCommand.equals(confirmationAcceptEditStudentCommand));
        assertFalse(confirmationEditStudentCommand.equals(confirmationRejectEditStudentCommand));
    }
}
