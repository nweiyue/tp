package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.testutil.TypicalTabNames.SESSIONS_TAB_NAME;
import static atas.testutil.TypicalTabNames.STUDENTS_TAB_NAME;

import org.junit.jupiter.api.Test;

import atas.logic.commands.atas.SwitchCommand;

public class SwitchCommandParserTest {

    private SwitchCommandParser parser = new SwitchCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_whiteSpaces_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "  \n\t   \n \t",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        SwitchCommand expectedSwitchToStudentsCommand = new SwitchCommand(STUDENTS_TAB_NAME);
        CommandParserTestUtil.assertParseSuccess(parser, STUDENTS_TAB_NAME, expectedSwitchToStudentsCommand);
        SwitchCommand expectedSwitchToSessionsCommand = new SwitchCommand(SESSIONS_TAB_NAME);
        CommandParserTestUtil.assertParseSuccess(parser, SESSIONS_TAB_NAME, expectedSwitchToSessionsCommand);

        // leading and trailing whitespaces
        CommandParserTestUtil.assertParseSuccess(parser, " \n \t " + STUDENTS_TAB_NAME + " \n \t ",
                expectedSwitchToStudentsCommand);
        CommandParserTestUtil.assertParseSuccess(parser, " \n \t " + SESSIONS_TAB_NAME + " \n \t ",
                expectedSwitchToSessionsCommand);
    }
}
