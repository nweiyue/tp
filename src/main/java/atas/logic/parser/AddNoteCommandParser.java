package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.logic.commands.memo.AddNoteCommand.MESSAGE_USAGE;

import atas.logic.commands.memo.AddNoteCommand;
import atas.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new AddNoteCommand object
 */
public class AddNoteCommandParser implements Parser<AddNoteCommand> {

    /**
     * Parses the given {@code String} of argument in the context of the AddNoteCommand
     * and returns an AddNoteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddNoteCommand parse(String args) throws ParseException {
        if (args.length() <= 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        // substring() to get rid of additional whitespace between command word and argument
        String actualNoteToAdd = args.substring(1);
        return new AddNoteCommand(actualNoteToAdd);
    }

}
