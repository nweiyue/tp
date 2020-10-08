package seedu.address.logic.commands.sessionlistcommands;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.confirmationcommands.DangerousCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attendance.Session;
import seedu.address.model.attendance.SessionDate;
import seedu.address.model.attendance.SessionName;

import static java.util.Objects.requireNonNull;


public class DeleteSessionCommand extends DangerousCommand {

    public static final String COMMAND_WORD = "deletesession";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a session from the current class. "
            + "Parameters: SESSION_NAME "
            + "\n"
            + "Example: tut1 ";


    public static final String MESSAGE_SUCCESS = "Session deleted.";
    public static final String MESSAGE_SESSION_NOT_FOUND = "Session not found.";

    private final SessionName toDelete;

    /**
     * Creates an AddSessionCommand to add the specified {@code Session}
     */
    public DeleteSessionCommand(SessionName sessionName) {
        requireNonNull(sessionName);
        toDelete = sessionName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Session sessionToDelete = new Session(toDelete, SessionDate.getPlaceholderSessionDate());

        if (!model.hasSession(sessionToDelete)) {
            throw new CommandException(MESSAGE_SESSION_NOT_FOUND);
        }

        model.deleteSession(sessionToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteSessionCommand // instanceof handles nulls
                && toDelete.equals(((DeleteSessionCommand) other).toDelete));
    }

    @Override
    public String toString() {
        return String.format("Delete session %s", toDelete);
    }
}
