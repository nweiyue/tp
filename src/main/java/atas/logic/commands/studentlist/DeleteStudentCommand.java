package atas.logic.commands.studentlist;

import static atas.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static java.util.Objects.requireNonNull;

import java.util.List;

import atas.commons.core.index.Index;
import atas.logic.commands.CommandResult;
import atas.logic.commands.confirmation.DangerousCommand;
import atas.logic.commands.exceptions.CommandException;
import atas.model.Model;
import atas.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeleteStudentCommand extends DangerousCommand {

    public static final String COMMAND_WORD = "deletestu";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted student: %1$s";

    private final Index targetIndex;

    public DeleteStudentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePerson(personToDelete, targetIndex);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteStudentCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteStudentCommand) other).targetIndex)); // state check
    }

    @Override
    public String toString() {
        String oneBasedIndex = String.valueOf(targetIndex.getOneBased());
        return "Delete " + oneBasedIndex;
    }
}
