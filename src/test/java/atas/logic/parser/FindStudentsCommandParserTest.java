package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.logic.parser.CommandParserTestUtil.assertParseFailure;
import static atas.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import atas.logic.commands.studentlist.FindStudentsCommand;
import atas.model.person.NameContainsKeywordsPredicate;

public class FindStudentsCommandParserTest {

    private FindStudentsCommandParser parser = new FindStudentsCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindStudentsCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindStudentsCommand expectedFindStudentsCommand =
                new FindStudentsCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindStudentsCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindStudentsCommand);
    }

}
