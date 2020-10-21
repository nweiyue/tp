package atas.logic.commands.sessionlist;

import static java.util.Objects.requireNonNull;

import atas.logic.commands.CommandResult;
import atas.logic.commands.confirmation.DangerousCommand;
import atas.model.Model;

/**
 * Clears the session list.
 */
public class ClearSessionsCommand extends DangerousCommand {

    public static final String COMMAND_WORD = "clearses";
    public static final String MESSAGE_SUCCESS = "ATAS has cleared all sessions from your list!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetSessionList();
        model.commit();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ClearSessionsCommand;
    }

    @Override
    public String toString() {
        return "Clear session list";
    }
}
