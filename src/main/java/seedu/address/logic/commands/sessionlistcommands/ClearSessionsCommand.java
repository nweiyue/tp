package seedu.address.logic.commands.sessionlistcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Clears the session list.
 */
public class ClearSessionsCommand extends Command {

    public static final String COMMAND_WORD = "clearsessions";
    public static final String MESSAGE_SUCCESS = "The session in the current session list has been cleared";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetSessionList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
