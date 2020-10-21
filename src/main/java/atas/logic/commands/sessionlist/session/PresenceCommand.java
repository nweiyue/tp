package atas.logic.commands.sessionlist.session;

import static java.util.Objects.requireNonNull;

import atas.commons.core.Messages;
import atas.logic.commands.Command;
import atas.logic.commands.CommandResult;
import atas.logic.commands.exceptions.CommandException;
import atas.model.Model;
import atas.model.session.IndexRange;
import atas.model.session.SessionName;

/**
 * Toggles the participation status of certain students in particular sessions.
 */
public class PresenceCommand extends Command {

    public static final String COMMAND_WORD = "presence";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Toggles the presence status of students in a "
            + "session.\n"
            + "Parameters: INDEX_RANGE\n"
            + "Example 1: " + COMMAND_WORD + " 3\n"
            + "Example 2: " + COMMAND_WORD + " 1-4";


    public static final String MESSAGE_SUCCESS = "Presence status changed";

    private final IndexRange range;
    private final SessionName sessionName;

    /**
     * Creates an PresenceCommand to add the specified {@code IndexRange}
     */
    public PresenceCommand(SessionName sessionName, IndexRange range) {
        requireNonNull(range);
        this.range = range;
        this.sessionName = sessionName;
    }

    /**
     * Creates an PresenceCommand to add the specified {@code IndexRange}
     */
    public PresenceCommand(IndexRange range) {
        requireNonNull(range);
        this.range = range;
        this.sessionName = new SessionName("DEFAULT");
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.returnCurrentSessionEnabledStatus()) {
            throw new CommandException(Messages.MESSAGE_NOT_IN_SESSION_TAB);
        }
        model.updatePresenceBySessionName(sessionName, range);
        model.commit();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PresenceCommand // instanceof handles nulls
                && range.equals(((PresenceCommand) other).range)
                && sessionName.equals(((PresenceCommand) other).sessionName));
    }
}
