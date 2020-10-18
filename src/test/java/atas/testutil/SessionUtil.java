package atas.testutil;

import atas.logic.commands.sessionlist.AddSessionCommand;
import atas.logic.parser.CliSyntax;
import atas.model.session.Session;

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
        sb.append(CliSyntax.PREFIX_SESSION_NAME + session.getSessionName().toString() + " ");
        sb.append(CliSyntax.PREFIX_SESSION_DATE + session.getSessionDate().toString() + " ");
        return sb.toString();
    }
}
