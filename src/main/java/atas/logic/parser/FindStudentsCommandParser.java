package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.logic.commands.studentlist.FindStudentsCommand.MESSAGE_USAGE;

import java.util.Arrays;

import atas.logic.commands.studentlist.FindStudentsCommand;
import atas.logic.parser.exceptions.ParseException;
import atas.model.student.NameContainsKeywordsPredicate;


/**
 * Parses input arguments and creates a new FindStudentsCommand object
 */
public class FindStudentsCommandParser implements Parser<FindStudentsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindStudentsCommand
     * and returns a FindStudentsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindStudentsCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindStudentsCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
