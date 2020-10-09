package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.logic.commands.sessionlistcommands.DeleteSessionCommand.MESSAGE_USAGE;

import atas.logic.commands.sessionlistcommands.DeleteSessionCommand;
import atas.logic.parser.exceptions.ParseException;
import atas.model.attendance.SessionName;

/**
 * Parses input arguments and creates a new DeleteSessionCommand object
 */
public class DeleteSessionCommandParser implements Parser<DeleteSessionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteSessionCommand
     * and returns a DeleteSessionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteSessionCommand parse(String args) throws ParseException {
        try {
            SessionName sessionName = ParserUtil.parseSessionName(args);
            return new DeleteSessionCommand(sessionName);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }

    }
}
