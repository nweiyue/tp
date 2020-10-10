package atas.logic.parser;

import atas.logic.commands.sessionlist.session.ParticipateCommand;
import atas.logic.parser.exceptions.ParseException;
import atas.model.attendance.IndexRange;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        IndexRange indexRange;

        try {
            indexRange = ParserUtil.parseIndexRange(argMultimap.getPreamble());
        } catch (IllegalArgumentException e) {
            throw new ParseException(IndexRange.MESSAGE_CONSTRAINTS);
        }

        // TODO: supposed to get sessionName internally
        //SessionName sessionName = new SessionName(argMultimap.getValue(PREFIX_SESSIONNAME).get());

        return new ParticipateCommand(indexRange);
    }

}
