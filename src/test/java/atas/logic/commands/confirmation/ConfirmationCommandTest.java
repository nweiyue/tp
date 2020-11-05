package atas.logic.commands.confirmation;

import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static atas.testutil.TypicalIndexes.INDEX_FIRST_SESSION;
import static atas.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static atas.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static atas.testutil.TypicalMemoContents.EMPTY_MEMO_CONTENT;
import static atas.testutil.TypicalSessions.SESSIONNAME_WEEK_ONE;
import static atas.testutil.TypicalSessions.getTypicalSessionList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.logic.commands.CommandTestUtil;
import atas.logic.commands.sessionlist.ClearSessionsCommand;
import atas.logic.commands.sessionlist.DeleteSessionCommand;
import atas.logic.commands.sessionlist.EditSessionCommand;
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

public class ConfirmationCommandTest {
    private Model model = ModelManagerBuilder.buildTypicalModelManager();
    private Model expectedModel = new ModelManager(getTypicalSessionList(model.getStudentList().getStudentList()),
        model.getStudentList(), new UserPrefs(), EMPTY_MEMO_CONTENT);

    @Test
    public void execute_deleteCommandConfirmation_success() {
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_STUDENT);

        String expectedMessage = String.format(ConfirmationCommand.MESSAGE_CONFIRMATION_DELETE_STUDENT,
                INDEX_FIRST_STUDENT.getOneBased());

        ConfirmationCommand confirmationCommand = new ConfirmationCommand(deleteStudentCommand);
        assertCommandSuccess(confirmationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editCommandConfirmation_success() {
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_STUDENT,
            new EditStudentDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        String expectedMessage = String.format(ConfirmationCommand.MESSAGE_CONFIRMATION_EDIT_STUDENT,
                INDEX_FIRST_STUDENT.getOneBased());

        ConfirmationCommand confirmationCommand = new ConfirmationCommand(editStudentCommand);
        assertCommandSuccess(confirmationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_clearStudentListCommandConfirmation_success() {
        ClearStudentListCommand clearStudentListCommand = new ClearStudentListCommand();

        String expectedMessage = ConfirmationCommand.MESSAGE_CONFIRMATION_CLEAR_STUDENT_LIST;

        ConfirmationCommand confirmationCommand = new ConfirmationCommand(clearStudentListCommand);
        assertCommandSuccess(confirmationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteSessionCommandConfirmation_success() {
        DeleteSessionCommand deleteSessionCommand = new DeleteSessionCommand(INDEX_FIRST_SESSION);

        String expectedMessage = String.format(ConfirmationCommand.MESSAGE_CONFIRMATION_DELETE_SESSION,
                INDEX_FIRST_SESSION.getOneBased());

        ConfirmationCommand confirmationCommand = new ConfirmationCommand(deleteSessionCommand);
        assertCommandSuccess(confirmationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editSessionCommandConfirmation_success() {
        EditSessionCommand.EditSessionDescriptor editSessionDescriptor =
                new EditSessionCommand.EditSessionDescriptor();
        editSessionDescriptor.setSessionName(SESSIONNAME_WEEK_ONE);

        EditSessionCommand editSessionCommand = new EditSessionCommand(INDEX_FIRST_SESSION,
            editSessionDescriptor);

        String expectedMessage = String.format(ConfirmationCommand.MESSAGE_CONFIRMATION_EDIT_SESSION,
                INDEX_FIRST_SESSION.getOneBased());

        ConfirmationCommand confirmationCommand = new ConfirmationCommand(editSessionCommand);
        assertCommandSuccess(confirmationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_clearSessionCommandConfirmation_success() {
        ClearSessionsCommand clearSessionsCommand = new ClearSessionsCommand();
        String expectedMessage = String.format(ConfirmationCommand.MESSAGE_CONFIRMATION_CLEAR_SESSION_LIST,
            INDEX_FIRST_SESSION.getOneBased());

        ConfirmationCommand confirmationCommand = new ConfirmationCommand(clearSessionsCommand);
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

        ConfirmationCommand confirmationDeleteStudentCommand = new ConfirmationCommand(firstDeleteStudentCommand);
        ConfirmationCommand confirmationEditStudentCommand = new ConfirmationCommand(editStudentCommand);
        ConfirmationCommand confirmationClearStudentListCommand = new ConfirmationCommand(clearStudentListCommand);

        // Check if confirmationDangerousCommand equals confirmationSameDangerousCommand
        assertTrue(confirmationDeleteStudentCommand.equals(confirmationDeleteStudentCommand));
        assertTrue(confirmationEditStudentCommand.equals(confirmationEditStudentCommand));
        assertTrue(confirmationClearStudentListCommand.equals(confirmationClearStudentListCommand));

        // Check if confirmationDangerousCommand equals confirmationAnotherDangerousCommand
        assertFalse(confirmationDeleteStudentCommand.equals(confirmationClearStudentListCommand));
        assertFalse(confirmationDeleteStudentCommand.equals(confirmationEditStudentCommand));
        assertFalse(confirmationEditStudentCommand.equals(confirmationClearStudentListCommand));

        // Check if confirmationDeleteCommand equals another subclass of ConfirmCommand
        assertFalse(confirmationDeleteStudentCommand.equals(new ConfirmationRejectCommand(secondDeleteStudentCommand)));
        assertFalse(confirmationDeleteStudentCommand.equals(new ConfirmationAcceptCommand(secondDeleteStudentCommand)));

    }
}
