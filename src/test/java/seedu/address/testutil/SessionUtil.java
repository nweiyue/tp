package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSIONDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSIONNAME;

import seedu.address.logic.commands.sessionlistcommands.AddSessionCommand;
import seedu.address.model.attendance.Session;


/**
 * A utility class for Session.
 */
public class SessionUtil {

    /**
     * Returns an add session command string for adding the {@code session}.
     */
    public static String getAddSessionCommand(Session session) {
        return AddSessionCommand.COMMAND_WORD + " " + getSessionDetails(session);
    }

    /**
     * Returns the part of command string for the given {@code session}'s details.
     */
    public static String getSessionDetails(Session session) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_SESSIONNAME + session.getSessionName().toString() + " ");
        sb.append(PREFIX_SESSIONDATE + session.getSessionDate().toString() + " ");
        return sb.toString();
    }
}
