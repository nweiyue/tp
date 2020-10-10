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

public class ConfirmationAcceptCommandTest {
    private Model model = ModelManagerBuilder.buildTypicalModelManager();

    @Test
    public void execute_acceptValidDeleteCommand_success() {
        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_PERSON_SUCCESS, studentToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalSessionList(model.getStudentList().getStudentList()),
                model.getStudentList(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete, INDEX_FIRST_PERSON);

        ConfirmationAcceptCommand confirmationAcceptCommand = new ConfirmationAcceptCommand(deleteStudentCommand);
        assertCommandSuccess(confirmationAcceptCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidDeleteCommand_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(outOfBoundIndex);

        ConfirmationAcceptCommand confirmationAcceptCommand = new ConfirmationAcceptCommand(deleteStudentCommand);
        assertCommandFailure(confirmationAcceptCommand, model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_acceptValidEditCommand_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList).withName(VALID_NAME_BOB).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_PERSON,
            new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditStudentCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(getTypicalSessionList(model.getStudentList().getStudentList()),
                new StudentList(model.getStudentList()), new UserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);

        ConfirmationAcceptCommand confirmationAcceptCommand = new ConfirmationAcceptCommand(editStudentCommand);
        assertCommandSuccess(confirmationAcceptCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidEditCommand_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EditStudentCommand.EditPersonDescriptor descriptor = new EditStudentDescriptorBuilder()
                .withName(VALID_NAME_BOB).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(outOfBoundIndex, descriptor);

        ConfirmationAcceptCommand confirmationAcceptCommand = new ConfirmationAcceptCommand(editStudentCommand);
        assertCommandFailure(confirmationAcceptCommand, model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_acceptClearCommand_success() {
        ClearStudentListCommand clearStudentListCommand = new ClearStudentListCommand();
        ModelManager expectedModel = new ModelManager(getTypicalSessionList(model.getStudentList().getStudentList()),
            model.getStudentList(), new UserPrefs());

        ConfirmationAcceptCommand confirmationAcceptCommand = new ConfirmationAcceptCommand(clearStudentListCommand);
        assertCommandSuccess(confirmationAcceptCommand, model, ClearStudentListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        DeleteStudentCommand firstDeleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_PERSON);
        DeleteStudentCommand secondDeleteStudentCommand = new DeleteStudentCommand(INDEX_SECOND_PERSON);

        Student editedStudent = new StudentBuilder().build();
        EditStudentCommand.EditPersonDescriptor descriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditStudentCommand editStudentCommand = new EditStudentCommand(INDEX_FIRST_PERSON, descriptor);

        ClearStudentListCommand clearStudentListCommand = new ClearStudentListCommand();

        ConfirmationAcceptCommand confirmationAcceptDeleteCommand =
                new ConfirmationAcceptCommand(firstDeleteStudentCommand);
        ConfirmationAcceptCommand confirmationAcceptEditCommand =
                new ConfirmationAcceptCommand(editStudentCommand);
        ConfirmationAcceptCommand confirmationAcceptClearCommand =
                new ConfirmationAcceptCommand(clearStudentListCommand);

        // Check if confirmationAcceptDangerousCommand equals confirmationAcceptSameDangerousCommand
        assertTrue(confirmationAcceptDeleteCommand.equals(confirmationAcceptDeleteCommand));
        assertTrue(confirmationAcceptEditCommand.equals(confirmationAcceptEditCommand));
        assertTrue(confirmationAcceptClearCommand.equals(confirmationAcceptClearCommand));

        // Check if confirmationAcceptDangerousCommand equals confirmationAcceptAnotherDangerousCommand
        assertFalse(confirmationAcceptDeleteCommand.equals(confirmationAcceptClearCommand));
        assertFalse(confirmationAcceptDeleteCommand.equals(confirmationAcceptEditCommand));
        assertFalse(confirmationAcceptEditCommand.equals(confirmationAcceptClearCommand));

        // Check if confirmationAcceptDeleteCommand equals confirmationAcceptAnotherDeleteCommand
        assertFalse(confirmationAcceptDeleteCommand.equals(
                new ConfirmationAcceptCommand(secondDeleteStudentCommand)));

        // Check if confirmationAcceptCommand equals another subclass of ConfirmCommand
        assertFalse(confirmationAcceptDeleteCommand.equals(
                new ConfirmationRejectCommand(firstDeleteStudentCommand)));
    }
}
