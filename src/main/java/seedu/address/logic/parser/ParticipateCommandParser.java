package seedu.address.logic.parser;

import seedu.address.logic.commands.sessioncommands.ParticipateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attendance.IndexRange;

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
       // ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SESSIONNAME);
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

/*
        try {
            //getting the index of session here
            char[] findInteger = args.toCharArray();
            int indexOfInteger = 0;
            while (indexOfInteger < findInteger.length && !Character.isDigit(findInteger[indexOfInteger])) {
                indexOfInteger++;
            }
            args = args.substring(indexOfInteger);
            IndexRange indexRange = ParserUtil.parseIndexRange(args);
            return new ParticipateCommand(indexRange);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EnterSessionCommand.MESSAGE_USAGE), pe);
        }*/
    }

}
