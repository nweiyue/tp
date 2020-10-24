package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.logic.commands.sessionlist.session.ParticipateCommand.MESSAGE_USAGE;
import static java.util.Objects.requireNonNull;

import atas.logic.commands.sessionlist.session.ParticipateCommand;
import atas.logic.parser.exceptions.ParseException;
import atas.model.session.IndexRange;

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
        IndexRange indexRange;

        try {
            indexRange = ParserUtil.parseIndexRange(args);
        } catch (IllegalArgumentException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), e);
        }

        // TODO: supposed to get sessionName internally
        //SessionName sessionName = new SessionName(argMultimap.getValue(PREFIX_SESSIONNAME).get());

        return new ParticipateCommand(indexRange);
    }

}
