package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.logic.commands.sessions.EnterSessionCommand.MESSAGE_USAGE;
import static atas.logic.parser.CliSyntax.PREFIX_SESSIONINDEX;

import java.util.stream.Stream;

import atas.commons.core.index.Index;
import atas.logic.commands.sessions.EnterSessionCommand;
import atas.logic.parser.exceptions.ParseException;

public class EnterSessionCommandParser implements Parser <EnterSessionCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EnterSessionCommand
     * and returns an EnterSessionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EnterSessionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SESSIONINDEX);
        if (!arePrefixesPresent(argMultimap, PREFIX_SESSIONINDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        try {
            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_SESSIONINDEX).get());
            return new EnterSessionCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }
    }
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
