package atas.logic.commands.sessionlist;

import static java.util.Objects.requireNonNull;

import atas.logic.commands.Command;
import atas.logic.commands.CommandResult;
import atas.model.Model;

/**
 * Clears the session list.
 */
public class ClearSessionsCommand extends Command {

    public static final String COMMAND_WORD = "clearses";
    public static final String MESSAGE_SUCCESS = "ATAS has cleared all sessions from your list!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetSessionList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
