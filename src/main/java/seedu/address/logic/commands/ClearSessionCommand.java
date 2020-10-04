package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Clears the session list.
 */
public class ClearSessionCommand extends Command {

    public static final String COMMAND_WORD = "clearsession";
    public static final String MESSAGE_SUCCESS = "The session in the current session list has been cleared";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetSessionList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
