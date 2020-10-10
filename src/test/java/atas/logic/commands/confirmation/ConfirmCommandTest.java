package atas.logic.commands.confirmation;

import static atas.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static atas.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.logic.commands.studentlist.ClearCommand;
import atas.logic.commands.studentlist.DeleteCommand;
import atas.logic.commands.studentlist.EditCommand;
import atas.model.person.Person;
import atas.testutil.EditPersonDescriptorBuilder;
import atas.testutil.PersonBuilder;

public class ConfirmCommandTest {
    @Test
    public void equals() {
        DeleteCommand firstDeleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        DeleteCommand secondDeleteCommand = new DeleteCommand(INDEX_SECOND_PERSON);

        Person editedPerson = new PersonBuilder().build();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        ClearCommand clearCommand = new ClearCommand();

        ConfirmCommand confirmationDeleteCommand = new ConfirmationCommand(firstDeleteCommand);
        ConfirmCommand confirmationEditCommand = new ConfirmationCommand(editCommand);
        ConfirmCommand confirmationClearCommand = new ConfirmationCommand(clearCommand);

        ConfirmCommand confirmationAcceptDeleteCommand = new ConfirmationAcceptCommand(firstDeleteCommand);
        ConfirmCommand confirmationAcceptEditCommand = new ConfirmationAcceptCommand(editCommand);
        ConfirmCommand confirmationAcceptClearCommand = new ConfirmationAcceptCommand(clearCommand);

        ConfirmCommand confirmationRejectDeleteCommand = new ConfirmationRejectCommand(firstDeleteCommand);
        ConfirmCommand confirmationRejectEditCommand = new ConfirmationRejectCommand(editCommand);
        ConfirmCommand confirmationRejectClearCommand = new ConfirmationRejectCommand(clearCommand);

        assertTrue(confirmationDeleteCommand.equals(confirmationDeleteCommand));
        assertTrue(confirmationEditCommand.equals(confirmationEditCommand));
        assertTrue(confirmationClearCommand.equals(confirmationClearCommand));

        assertFalse(confirmationDeleteCommand.equals(new ConfirmationCommand(secondDeleteCommand)));

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
