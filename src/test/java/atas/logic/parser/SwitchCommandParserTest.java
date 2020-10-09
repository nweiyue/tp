package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.testutil.TypicalTabNames.ATTENDANCE_TAB_NAME;
import static atas.testutil.TypicalTabNames.CLASSES_TAB_NAME;

import org.junit.jupiter.api.Test;

import atas.logic.commands.atascommands.SwitchCommand;

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
        SwitchCommand expectedSwitchToClassesCommand = new SwitchCommand(CLASSES_TAB_NAME);
        CommandParserTestUtil.assertParseSuccess(parser, CLASSES_TAB_NAME, expectedSwitchToClassesCommand);
        SwitchCommand expectedSwitchToAttendanceCommand = new SwitchCommand(ATTENDANCE_TAB_NAME);
        CommandParserTestUtil.assertParseSuccess(parser, ATTENDANCE_TAB_NAME, expectedSwitchToAttendanceCommand);

        // leading and trailing whitespaces
        CommandParserTestUtil.assertParseSuccess(parser, " \n \t " + CLASSES_TAB_NAME + " \n \t ",
                expectedSwitchToClassesCommand);
        CommandParserTestUtil.assertParseSuccess(parser, " \n \t " + ATTENDANCE_TAB_NAME + " \n \t ",
                expectedSwitchToAttendanceCommand);
    }
}
