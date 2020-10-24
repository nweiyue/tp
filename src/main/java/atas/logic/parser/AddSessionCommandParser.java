package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.logic.commands.sessionlist.AddSessionCommand.MESSAGE_USAGE;
import static atas.logic.parser.CliSyntax.PREFIX_SESSION_DATE;
import static atas.logic.parser.CliSyntax.PREFIX_SESSION_NAME;
import static atas.model.session.SessionDate.MESSAGE_CONSTRAINTS;

import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import atas.logic.commands.sessionlist.AddSessionCommand;
import atas.logic.parser.exceptions.ParseException;
import atas.model.session.Session;
import atas.model.session.SessionDate;
import atas.model.session.SessionName;

/**
 * Parses input arguments and creates a new AddSessionCommand object
 */
public class AddSessionCommandParser implements Parser<AddSessionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddSessionCommand
     * and returns an AddSessionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddSessionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SESSION_DATE, PREFIX_SESSION_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_SESSION_DATE, PREFIX_SESSION_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        SessionName sessionName = ParserUtil.parseSessionName(argMultimap.getValue(PREFIX_SESSION_NAME).get());
        SessionDate sessionDate;
        try {
            sessionDate = ParserUtil.parseSessionDate(argMultimap.getValue(PREFIX_SESSION_DATE).get());
        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }

        Session session = new Session(sessionName, sessionDate);

        return new AddSessionCommand(session);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
