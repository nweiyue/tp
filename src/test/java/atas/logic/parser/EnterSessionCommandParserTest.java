package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.logic.commands.CommandTestUtil.INVALID_SESSIONNAME_DESC;
import static atas.logic.commands.CommandTestUtil.VALID_SESSIONNAME_CON_DESC;
import static atas.logic.commands.sessionlist.EnterSessionCommand.MESSAGE_USAGE;

import org.junit.jupiter.api.Test;

import atas.logic.commands.sessionlist.EnterSessionCommand;

public class EnterSessionCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EnterSessionCommand.MESSAGE_USAGE);

    private EnterSessionCommandParser parser = new EnterSessionCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no sessionName of session specified
        CommandParserTestUtil.assertParseFailure(parser, VALID_SESSIONNAME_CON_DESC, MESSAGE_INVALID_FORMAT);

        // no sessionName of session and no field specified
        CommandParserTestUtil.assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // empty preamble
        CommandParserTestUtil.assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // whitespace peramble
        CommandParserTestUtil.assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        CommandParserTestUtil.assertParseFailure(parser, "tut" + INVALID_SESSIONNAME_DESC,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }
}
