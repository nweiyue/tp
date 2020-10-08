package seedu.address.logic.parser;

import seedu.address.logic.commands.AddSessionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attendance.Session;
import seedu.address.model.attendance.SessionDate;
import seedu.address.model.attendance.SessionName;

import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSIONDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSIONNAME;

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
                ArgumentTokenizer.tokenize(args, PREFIX_SESSIONDATE, PREFIX_SESSIONNAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_SESSIONDATE, PREFIX_SESSIONNAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSessionCommand.MESSAGE_USAGE));
        }

        SessionName sessionName = ParserUtil.parseSessionName(argMultimap.getValue(PREFIX_SESSIONNAME).get());
        SessionDate sessionDate;
        try {
            sessionDate = ParserUtil.parseSessionDate(argMultimap.getValue(PREFIX_SESSIONDATE).get());
        } catch (DateTimeParseException e) {
            throw new ParseException(SessionDate.MESSAGE_CONSTRAINTS);
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
