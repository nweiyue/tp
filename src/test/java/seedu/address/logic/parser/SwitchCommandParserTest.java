package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTabNames.CLASSES_TAB_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SwitchCommand;

public class SwitchCommandParserTest {

    private SwitchCommandParser parser = new SwitchCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SwitchCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        SwitchCommand expectedSwitchCommand = new SwitchCommand(CLASSES_TAB_NAME);
        assertParseSuccess(parser, CLASSES_TAB_NAME, expectedSwitchCommand);

        // leading and trailing whitespaces
        assertParseSuccess(parser, " \n \t " + CLASSES_TAB_NAME + " \n \t ", expectedSwitchCommand);
    }
}
