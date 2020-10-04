package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalSessions.getTypicalSessionList;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.ModelManagerBuilder;
import seedu.address.testutil.PersonBuilder;

public class ConfirmationCommandTest {
    private Model model = ModelManagerBuilder.buildTypicalModelManager();

    @Test
    public void execute_deleteCommandConfirmation_success() {
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(ConfirmationCommand.MESSAGE_CONFIRMATION_DELETE,
                INDEX_FIRST_PERSON.getOneBased());

        ModelManager expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                model.getAddressBook(), new UserPrefs());

        ConfirmationCommand confirmationCommand = new ConfirmationCommand(deleteCommand);
        assertCommandSuccess(confirmationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_editCommandConfirmation_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
            new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(ConfirmationCommand.MESSAGE_CONFIRMATION_EDIT,
                INDEX_FIRST_PERSON.getOneBased());

        Model expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                new AddressBook(model.getAddressBook()), new UserPrefs());

        ConfirmationCommand confirmationCommand = new ConfirmationCommand(editCommand);
        assertCommandSuccess(confirmationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_clearCommandConfirmation_success() {
        ClearCommand clearCommand = new ClearCommand();
        Model expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                model.getAddressBook(), new UserPrefs());

        String expectedMessage = ConfirmationCommand.MESSAGE_CONFIRMATION_CLEAR;

        ConfirmationCommand confirmationCommand = new ConfirmationCommand(clearCommand);
        assertCommandSuccess(confirmationCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteCommand firstDeleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        DeleteCommand secondDeleteCommand = new DeleteCommand(INDEX_SECOND_PERSON);

        Person editedPerson = new PersonBuilder().build();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        ClearCommand clearCommand = new ClearCommand();

        ConfirmationCommand confirmationDeleteCommand = new ConfirmationCommand(firstDeleteCommand);
        ConfirmationCommand confirmationEditCommand = new ConfirmationCommand(editCommand);
        ConfirmationCommand confirmationClearCommand = new ConfirmationCommand(clearCommand);

        // Check if confirmationDangerousCommand equals confirmationSameDangerousCommand
        assertTrue(confirmationDeleteCommand.equals(confirmationDeleteCommand));
        assertTrue(confirmationEditCommand.equals(confirmationEditCommand));
        assertTrue(confirmationClearCommand.equals(confirmationClearCommand));

        // Check if confirmationDangerousCommand equals confirmationAnotherDangerousCommand
        assertFalse(confirmationDeleteCommand.equals(confirmationClearCommand));
        assertFalse(confirmationDeleteCommand.equals(confirmationEditCommand));
        assertFalse(confirmationEditCommand.equals(confirmationClearCommand));

        // Check if confirmationDeleteCommand equals another subclass of ConfirmCommand
        assertFalse(confirmationDeleteCommand.equals(new ConfirmationRejectCommand(secondDeleteCommand)));
        assertFalse(confirmationDeleteCommand.equals(new ConfirmationAcceptCommand(secondDeleteCommand)));
    }
}
