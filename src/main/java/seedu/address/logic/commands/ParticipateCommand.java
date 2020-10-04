package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attendance.IndexRange;
import seedu.address.model.attendance.SessionName;



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

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

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
