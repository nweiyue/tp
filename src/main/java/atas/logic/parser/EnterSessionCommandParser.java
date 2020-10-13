package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import atas.commons.core.index.Index;
import atas.logic.commands.sessionlist.EnterSessionCommand;
import atas.logic.parser.exceptions.ParseException;

public class EnterSessionCommandParser implements Parser <EnterSessionCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EnterSessionCommand
     * and returns an EnterSessionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EnterSessionCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new EnterSessionCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EnterSessionCommand.MESSAGE_USAGE), pe);
        }
    }
}
