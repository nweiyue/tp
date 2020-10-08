package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSIONINDEX;

public class EnterSessionCommand extends Command {

    public static final String COMMAND_WORD = "entersession";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Enters a particular session. "
            + "Parameters: "
            + PREFIX_SESSIONINDEX + "SESSION_INDEX "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SESSIONINDEX + "1 ";


    public static final String MESSAGE_SUCCESS = "Session entered: %1$s";

    //sessionIndex should be zero-based here due to parser
    private final Index sessionIndex;

    /**
     * Creates an AddSessionCommand to add the specified {@code Session}
     */
    public EnterSessionCommand(Index sessionIndex) {
        requireNonNull(sessionIndex);
        this.sessionIndex = sessionIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (sessionIndex.getZeroBased() >= model.getFilteredSessionList().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
        }
        model.enterSession(sessionIndex);
        return new CommandResult(String.format(MESSAGE_SUCCESS, sessionIndex.getOneBased()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EnterSessionCommand // instanceof handles nulls
                && sessionIndex.equals(((EnterSessionCommand) other).sessionIndex));
    }

}
