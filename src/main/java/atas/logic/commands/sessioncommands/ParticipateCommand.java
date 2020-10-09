package atas.logic.commands.sessioncommands;

import static java.util.Objects.requireNonNull;

import atas.logic.commands.Command;
import atas.logic.commands.CommandResult;
import atas.logic.commands.exceptions.CommandException;
import atas.model.Model;
import atas.model.attendance.IndexRange;
import atas.model.attendance.SessionName;

/**
 * Toggles the participation status of certain students in particular sessions.
 */
public class ParticipateCommand extends Command {

    public static final String COMMAND_WORD = "participate";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Toggles the participation status of students in a "
            + "session.\n"
            + "Parameters: INDEX_RANGE\n"
            + "Example 1: " + COMMAND_WORD + " 3\n"
            + "Example 2: " + COMMAND_WORD + " 1-4";


    public static final String MESSAGE_SUCCESS = "Participation status changed";

    private final IndexRange range;
    private final SessionName sessionName;

    /**
     * Creates an ParticipateCommand to add the specified {@code IndexRange}
     */
    public ParticipateCommand(SessionName sessionName, IndexRange range) {
        requireNonNull(range);
        this.range = range;
        this.sessionName = sessionName;
    }

    /**
     * Creates an ParticipateCommand to add the specified {@code IndexRange}
     */
    public ParticipateCommand(IndexRange range) {
        this.range = range;
        this.sessionName = new SessionName("DEFAULT");
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.returnCurrentSessionEnabledStatus()) {
            throw new CommandException("You have to be in the session tab to use this!");
        }
        model.updateParticipationBySessionName(sessionName, range);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ParticipateCommand // instanceof handles nulls
                && range.equals(((ParticipateCommand) other).range)
                && sessionName.equals(((ParticipateCommand) other).sessionName));
    }
}
