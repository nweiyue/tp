package atas.logic.commands.confirmation;

import static atas.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static atas.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static atas.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static atas.testutil.TypicalSessions.getTypicalSessionList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.logic.commands.studentlist.ClearStudentListCommand;
import atas.logic.commands.studentlist.DeleteStudentCommand;
import atas.logic.commands.studentlist.EditStudentCommand;
import atas.model.Model;
import atas.model.ModelManager;
import atas.model.UserPrefs;
import atas.model.student.Student;
import atas.testutil.EditStudentDescriptorBuilder;
import atas.testutil.ModelManagerBuilder;
import atas.testutil.StudentBuilder;

public class ConfirmationRejectCommandTest {
    private Model model = ModelManagerBuilder.buildTypicalModelManager();

    @Test
    public void execute_rejectDeleteCommand_success() {
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);
        String expectedMessage = String.format(ConfirmationRejectCommand.MESSAGE_REJECT_COMMAND, deleteStudentCommand);

        ModelManager expectedModel = new ModelManager(getTypicalSessionList(model.getStudentList().getStudentList()),
                model.getStudentList(), new UserPrefs());

        ConfirmationRejectCommand confirmationRejectCommand = new ConfirmationRejectCommand(deleteStudentCommand);
        assertCommandSuccess(confirmationRejectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_rejectEditCommand_success() {
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_PERSON,
            new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(ConfirmationRejectCommand.MESSAGE_REJECT_COMMAND, editStudentCommand);

        ModelManager expectedModel = new ModelManager(getTypicalSessionList(model.getStudentList().getStudentList()),
                model.getStudentList(), new UserPrefs());

        ConfirmationRejectCommand confirmationRejectCommand = new ConfirmationRejectCommand(editStudentCommand);
        assertCommandSuccess(confirmationRejectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_rejectClearCommand_success() {
        ClearStudentListCommand clearStudentListCommand = new ClearStudentListCommand();
        Model expectedModel = new ModelManager(getTypicalSessionList(model.getStudentList().getStudentList()),
                model.getStudentList(), new UserPrefs());
        String expectedMessage = String.format(ConfirmationRejectCommand.MESSAGE_REJECT_COMMAND,
                clearStudentListCommand);

        ConfirmationRejectCommand confirmationRejectCommand =
                new ConfirmationRejectCommand(clearStudentListCommand);
        assertCommandSuccess(confirmationRejectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteStudentCommand firstDeleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);
        DeleteStudentCommand secondDeleteStudentCommand = new DeleteStudentCommand(INDEX_SECOND_PERSON);

        Student editedStudent = new StudentBuilder().build();
        EditStudentCommand.EditPersonDescriptor descriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_PERSON, descriptor);

        ClearStudentListCommand clearStudentListCommand = new ClearStudentListCommand();

        ConfirmationRejectCommand confirmationRejectDeleteCommand =
                new ConfirmationRejectCommand(firstDeleteStudentCommand);
        ConfirmationRejectCommand confirmationRejectEditCommand =
                new ConfirmationRejectCommand(editStudentCommand);
        ConfirmationRejectCommand confirmationRejectClearCommand =
                new ConfirmationRejectCommand(clearStudentListCommand);

        // Check if confirmationRejectDangerousCommand equals confirmationRejectSameDangerousCommand
        assertTrue(confirmationRejectDeleteCommand.equals(confirmationRejectDeleteCommand));
        assertTrue(confirmationRejectEditCommand.equals(confirmationRejectEditCommand));
        assertTrue(confirmationRejectClearCommand.equals(confirmationRejectClearCommand));

        // Check if confirmationRejectDangerousCommand equals confirmationRejectAnotherDangerousCommand
        assertFalse(confirmationRejectDeleteCommand.equals(confirmationRejectClearCommand));
        assertFalse(confirmationRejectDeleteCommand.equals(confirmationRejectEditCommand));
        assertFalse(confirmationRejectEditCommand.equals(confirmationRejectClearCommand));

        // Check if confirmationRejectDeleteCommand equals confirmationRejectAnotherDeleteCommand
        assertFalse(confirmationRejectDeleteCommand.equals(new ConfirmationRejectCommand(secondDeleteStudentCommand)));

        // Check if ConfirmationRejectCommand equals another subclass of ConfirmCommand
        assertFalse(confirmationRejectDeleteCommand.equals(new ConfirmationAcceptCommand(firstDeleteStudentCommand)));
    }
}
