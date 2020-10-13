package atas.logic.commands.confirmation;

import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static atas.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static atas.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static atas.testutil.TypicalSessions.getTypicalSessionList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.logic.commands.CommandTestUtil;
import atas.logic.commands.studentlist.ClearStudentListCommand;
import atas.logic.commands.studentlist.DeleteStudentCommand;
import atas.logic.commands.studentlist.EditStudentCommand;
import atas.model.Model;
import atas.model.ModelManager;
import atas.model.StudentList;
import atas.model.UserPrefs;
import atas.model.student.Student;
import atas.testutil.EditStudentDescriptorBuilder;
import atas.testutil.ModelManagerBuilder;
import atas.testutil.StudentBuilder;

public class ConfirmationCommandTest {
    private Model model = ModelManagerBuilder.buildTypicalModelManager();

    @Test
    public void execute_deleteCommandConfirmation_success() {
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(ConfirmationCommand.MESSAGE_CONFIRMATION_DELETE,
                INDEX_FIRST_STUDENT.getOneBased());

        ModelManager expectedModel = new ModelManager(getTypicalSessionList(model.getStudentList().getStudentList()),
                model.getStudentList(), new UserPrefs());

        ConfirmationCommand confirmationCommand = new ConfirmationCommand(deleteStudentCommand);
        assertCommandSuccess(confirmationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editCommandConfirmation_success() {
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_STUDENT,
            new EditStudentDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        String expectedMessage = String.format(ConfirmationCommand.MESSAGE_CONFIRMATION_EDIT,
                INDEX_FIRST_STUDENT.getOneBased());

        Model expectedModel = new ModelManager(getTypicalSessionList(model.getStudentList().getStudentList()),
                new StudentList(model.getStudentList()), new UserPrefs());

        ConfirmationCommand confirmationCommand = new ConfirmationCommand(editStudentCommand);
        assertCommandSuccess(confirmationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_clearCommandConfirmation_success() {
        ClearStudentListCommand clearStudentListCommand = new ClearStudentListCommand();
        Model expectedModel = new ModelManager(getTypicalSessionList(model.getStudentList().getStudentList()),
                model.getStudentList(), new UserPrefs());

        String expectedMessage = ConfirmationCommand.MESSAGE_CONFIRMATION_CLEAR;

        ConfirmationCommand confirmationCommand = new ConfirmationCommand(clearStudentListCommand);
        assertCommandSuccess(confirmationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteStudentCommand firstDeleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_STUDENT);
        DeleteStudentCommand secondDeleteStudentCommand = new DeleteStudentCommand(INDEX_SECOND_STUDENT);

        Student editedStudent = new StudentBuilder().build();
        EditStudentCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_STUDENT, descriptor);

        ClearStudentListCommand clearStudentListCommand = new ClearStudentListCommand();

        ConfirmationCommand confirmationDeleteCommand = new ConfirmationCommand(firstDeleteStudentCommand);
        ConfirmationCommand confirmationEditCommand = new ConfirmationCommand(editStudentCommand);
        ConfirmationCommand confirmationClearCommand = new ConfirmationCommand(clearStudentListCommand);

        // Check if confirmationDangerousCommand equals confirmationSameDangerousCommand
        assertTrue(confirmationDeleteCommand.equals(confirmationDeleteCommand));
        assertTrue(confirmationEditCommand.equals(confirmationEditCommand));
        assertTrue(confirmationClearCommand.equals(confirmationClearCommand));

        // Check if confirmationDangerousCommand equals confirmationAnotherDangerousCommand
        assertFalse(confirmationDeleteCommand.equals(confirmationClearCommand));
        assertFalse(confirmationDeleteCommand.equals(confirmationEditCommand));
        assertFalse(confirmationEditCommand.equals(confirmationClearCommand));

        // Check if confirmationDeleteCommand equals another subclass of ConfirmCommand
        assertFalse(confirmationDeleteCommand.equals(new ConfirmationRejectCommand(secondDeleteStudentCommand)));
        assertFalse(confirmationDeleteCommand.equals(new ConfirmationAcceptCommand(secondDeleteStudentCommand)));
    }
}
