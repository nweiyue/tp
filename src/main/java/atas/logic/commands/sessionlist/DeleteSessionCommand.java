package atas.logic.commands.sessionlist;

import static java.util.Objects.requireNonNull;

import atas.logic.commands.CommandResult;
import atas.logic.commands.confirmation.DangerousCommand;
import atas.logic.commands.exceptions.CommandException;
import atas.model.Model;
import atas.model.attendance.Session;
import atas.model.attendance.SessionDate;
import atas.model.attendance.SessionName;

public class DeleteSessionCommand extends DangerousCommand {

    public static final String COMMAND_WORD = "deleteses";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a session from the session list. \n"
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
