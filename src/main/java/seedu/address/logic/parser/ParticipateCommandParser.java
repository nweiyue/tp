package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSIONNAME;

import seedu.address.logic.commands.ParticipateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attendance.IndexRange;
import seedu.address.model.attendance.SessionName;

/**
 * Parses input arguments and creates a new ParticipateCommand object
 */
public class ParticipateCommandParser implements Parser<ParticipateCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ParticipateCommand
     * and returns an ParticipateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ParticipateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SESSIONNAME);

        IndexRange indexRange;

        try {
            indexRange = ParserUtil.parseIndexRange(argMultimap.getPreamble());
        } catch (IllegalArgumentException e) {
            throw new ParseException(IndexRange.MESSAGE_CONSTRAINTS);
        }

        // TODO: supposed to get sessionName internally
        SessionName sessionName = new SessionName(argMultimap.getValue(PREFIX_SESSIONNAME).get());

        return new ParticipateCommand(sessionName, indexRange);
    }

}
