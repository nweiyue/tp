package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.sessioncommands.PresenceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attendance.IndexRange;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        IndexRange indexRange;

        try {
            indexRange = ParserUtil.parseIndexRange(argMultimap.getPreamble());
        } catch (IllegalArgumentException e) {
            throw new ParseException(IndexRange.MESSAGE_CONSTRAINTS);
        }

        // TODO: supposed to get sessionName internally
        //SessionName sessionName = new SessionName(argMultimap.getValue(PREFIX_SESSIONNAME).get());

        return new PresenceCommand(indexRange);

    }

}
