package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSIONNAME;

import seedu.address.logic.commands.PresenceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attendance.IndexRange;
import seedu.address.model.attendance.SessionName;

/**
 * Parses input arguments and creates a new PresenceCommand object
 */
public class PresenceCommandParser implements Parser<PresenceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PresenceCommand
     * and returns an PresenceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PresenceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SESSIONNAME);

        IndexRange indexRange;

        try {
            indexRange = ParserUtil.parseIndexRange(argMultimap.getPreamble());
        } catch (IllegalArgumentException e) {
            throw new ParseException(IndexRange.MESSAGE_CONSTRAINTS);
        }

        SessionName sessionName = new SessionName(argMultimap.getValue(PREFIX_SESSIONNAME).get());

        return new PresenceCommand(sessionName, indexRange);
    }

}
