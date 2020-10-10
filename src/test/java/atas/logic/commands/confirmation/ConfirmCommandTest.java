package atas.logic.commands.confirmation;

import static atas.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static atas.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.logic.commands.studentlist.ClearStudentListCommand;
import atas.logic.commands.studentlist.DeleteStudentCommand;
import atas.logic.commands.studentlist.EditStudentCommand;
import atas.model.student.Student;
import atas.testutil.EditStudentDescriptorBuilder;
import atas.testutil.StudentBuilder;

public class ConfirmCommandTest {
    @Test
    public void equals() {
        DeleteStudentCommand firstDeleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);
        DeleteStudentCommand secondDeleteStudentCommand = new DeleteStudentCommand(INDEX_SECOND_PERSON);

        Student editedStudent = new StudentBuilder().build();
        EditStudentCommand.EditPersonDescriptor descriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_PERSON, descriptor);

        ClearStudentListCommand clearStudentListCommand = new ClearStudentListCommand();

        ConfirmCommand confirmationDeleteCommand = new ConfirmationCommand(firstDeleteStudentCommand);
        ConfirmCommand confirmationEditCommand = new ConfirmationCommand(editStudentCommand);
        ConfirmCommand confirmationClearCommand = new ConfirmationCommand(clearStudentListCommand);

        ConfirmCommand confirmationAcceptDeleteCommand = new ConfirmationAcceptCommand(firstDeleteStudentCommand);
        ConfirmCommand confirmationAcceptEditCommand = new ConfirmationAcceptCommand(editStudentCommand);
        ConfirmCommand confirmationAcceptClearCommand = new ConfirmationAcceptCommand(clearStudentListCommand);

        ConfirmCommand confirmationRejectDeleteCommand = new ConfirmationRejectCommand(firstDeleteStudentCommand);
        ConfirmCommand confirmationRejectEditCommand = new ConfirmationRejectCommand(editStudentCommand);
        ConfirmCommand confirmationRejectClearCommand = new ConfirmationRejectCommand(clearStudentListCommand);

        assertTrue(confirmationDeleteCommand.equals(confirmationDeleteCommand));
        assertTrue(confirmationEditCommand.equals(confirmationEditCommand));
        assertTrue(confirmationClearCommand.equals(confirmationClearCommand));

        assertFalse(confirmationDeleteCommand.equals(new ConfirmationCommand(secondDeleteStudentCommand)));

        assertFalse(confirmationDeleteCommand.equals(confirmationClearCommand));
        assertFalse(confirmationDeleteCommand.equals(confirmationEditCommand));
        assertFalse(confirmationEditCommand.equals(confirmationClearCommand));

        // Make sure there is a clear distinction between accept and reject confirmation command.
        assertFalse(confirmationAcceptDeleteCommand.equals(confirmationRejectDeleteCommand));
        assertFalse(confirmationAcceptClearCommand.equals(confirmationRejectClearCommand));
        assertFalse(confirmationAcceptEditCommand.equals(confirmationRejectEditCommand));

        // Make sure there is clear distinction between confirmation and accept/reject confirmation command.
        assertFalse(confirmationClearCommand.equals(confirmationAcceptClearCommand));
        assertFalse(confirmationClearCommand.equals(confirmationRejectClearCommand));
        assertFalse(confirmationDeleteCommand.equals(confirmationAcceptDeleteCommand));
        assertFalse(confirmationDeleteCommand.equals(confirmationRejectDeleteCommand));
        assertFalse(confirmationEditCommand.equals(confirmationAcceptEditCommand));
        assertFalse(confirmationEditCommand.equals(confirmationRejectEditCommand));
    }
}
