package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTabNames.ATTENDANCE_TAB_NAME;
import static seedu.address.testutil.TypicalTabNames.CLASSES_TAB_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SwitchCommand;

public class SwitchCommandParserTest {

    private SwitchCommandParser parser = new SwitchCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_whiteSpaces_throwsParseException() {
        assertParseFailure(parser, "  \n\t   \n \t",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        SwitchCommand expectedSwitchToClassesCommand = new SwitchCommand(CLASSES_TAB_NAME);
        assertParseSuccess(parser, CLASSES_TAB_NAME, expectedSwitchToClassesCommand);
        SwitchCommand expectedSwitchToAttendanceCommand = new SwitchCommand(ATTENDANCE_TAB_NAME);
        assertParseSuccess(parser, ATTENDANCE_TAB_NAME, expectedSwitchToAttendanceCommand);

        // leading and trailing whitespaces
        assertParseSuccess(parser, " \n \t " + CLASSES_TAB_NAME + " \n \t ",
                expectedSwitchToClassesCommand);
        assertParseSuccess(parser, " \n \t " + ATTENDANCE_TAB_NAME + " \n \t ",
                expectedSwitchToAttendanceCommand);
    }
}
