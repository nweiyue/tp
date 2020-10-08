package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.sessionlistcommands.EnterSessionCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSIONINDEX;

public class EnterSessionCommandParser implements Parser<EnterSessionCommand> { /**
 * Parses the given {@code String} of arguments in the context of the EnterSessionCommand
 * and returns an EnterSessionCommand object for execution.
 * @throws ParseException if the user input does not conform the expected format
 */
public EnterSessionCommand parse(String args) throws ParseException {

    /**
     * Parses the given {@code String} of arguments in the context of the EnterSessionCommand
     * and returns a EnterSessionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_SESSIONINDEX);

    if (!arePrefixesPresent(argMultimap, PREFIX_SESSIONINDEX)
            || !argMultimap.getPreamble().isEmpty()) {
        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EnterSessionCommand.MESSAGE_USAGE));
    }

    try {
        //getting the index of session here [original code]
       /* String[] argsArray = args.split("/");
        args = argsArray[1];
        Index index = ParserUtil.parseIndex(args);*/
        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_SESSIONINDEX).get());
        return new EnterSessionCommand(index);
    } catch (ParseException pe) {
        throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EnterSessionCommand.MESSAGE_USAGE), pe);
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
