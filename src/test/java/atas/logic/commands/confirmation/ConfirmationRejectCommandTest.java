package atas.logic.commands.confirmation;

import static atas.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static atas.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static atas.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static atas.testutil.TypicalSessions.getTypicalSessionList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.logic.commands.students.ClearCommand;
import atas.logic.commands.students.DeleteCommand;
import atas.logic.commands.students.EditCommand;
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
    public void execute_rejectDeleteCommand_success() {
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        String expectedMessage = String.format(ConfirmationRejectCommand.MESSAGE_REJECT_COMMAND, deleteCommand);

        ModelManager expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                model.getAddressBook(), new UserPrefs());

        ConfirmationRejectCommand confirmationRejectCommand = new ConfirmationRejectCommand(deleteCommand);
        assertCommandSuccess(confirmationRejectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_rejectEditCommand_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
            new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(ConfirmationRejectCommand.MESSAGE_REJECT_COMMAND, editCommand);

        ModelManager expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                model.getAddressBook(), new UserPrefs());

        ConfirmationRejectCommand confirmationRejectCommand = new ConfirmationRejectCommand(editCommand);
        assertCommandSuccess(confirmationRejectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_rejectClearCommand_success() {
        ClearCommand clearCommand = new ClearCommand();
        Model expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                model.getAddressBook(), new UserPrefs());
        String expectedMessage = String.format(ConfirmationRejectCommand.MESSAGE_REJECT_COMMAND, clearCommand);

        ConfirmationRejectCommand confirmationRejectCommand = new ConfirmationRejectCommand(clearCommand);
        assertCommandSuccess(confirmationRejectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteCommand firstDeleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        DeleteCommand secondDeleteCommand = new DeleteCommand(INDEX_SECOND_PERSON);

        Person editedPerson = new PersonBuilder().build();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        ClearCommand clearCommand = new ClearCommand();

        ConfirmationRejectCommand confirmationRejectDeleteCommand = new ConfirmationRejectCommand(firstDeleteCommand);
        ConfirmationRejectCommand confirmationRejectEditCommand = new ConfirmationRejectCommand(editCommand);
        ConfirmationRejectCommand confirmationRejectClearCommand = new ConfirmationRejectCommand(clearCommand);

        // Check if confirmationRejectDangerousCommand equals confirmationRejectSameDangerousCommand
        assertTrue(confirmationRejectDeleteCommand.equals(confirmationRejectDeleteCommand));
        assertTrue(confirmationRejectEditCommand.equals(confirmationRejectEditCommand));
        assertTrue(confirmationRejectClearCommand.equals(confirmationRejectClearCommand));

        // Check if confirmationRejectDangerousCommand equals confirmationRejectAnotherDangerousCommand
        assertFalse(confirmationRejectDeleteCommand.equals(confirmationRejectClearCommand));
        assertFalse(confirmationRejectDeleteCommand.equals(confirmationRejectEditCommand));
        assertFalse(confirmationRejectEditCommand.equals(confirmationRejectClearCommand));

        // Check if confirmationRejectDeleteCommand equals confirmationRejectAnotherDeleteCommand
        assertFalse(confirmationRejectDeleteCommand.equals(new ConfirmationRejectCommand(secondDeleteCommand)));

        // Check if ConfirmationRejectCommand equals another subclass of ConfirmCommand
        assertFalse(confirmationRejectDeleteCommand.equals(new ConfirmationAcceptCommand(firstDeleteCommand)));
    }
}
