package atas.logic.commands.studentlist;

import static java.util.Objects.requireNonNull;

import atas.logic.commands.CommandResult;
import atas.logic.commands.confirmation.DangerousCommand;
import atas.model.AddressBook;
import atas.model.Model;

/**
 * Clears the address book.
 */
public class ClearStudentsCommand extends DangerousCommand {
    public static final String COMMAND_WORD = "clearstu";
    public static final String MESSAGE_SUCCESS = "ATAS has cleared all students from your list!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new AddressBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public String toString() {
        return "Clear list";
    }
}
