package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import atas.commons.core.index.Index;
import atas.logic.commands.sessionlist.EnterSessionCommand;

public class EnterSessionCommandParserTest {

    private EnterSessionCommandParser parser = new EnterSessionCommandParser();

    @Test
    public void parse_validArgs_returnsEnterCommand() {
        assertParseSuccess(parser, " " + CliSyntax.PREFIX_SESSIONINDEX + "1",
                new EnterSessionCommand(Index.fromOneBased(1)));
    }

    @Test
    public void parse_invalidSessionIndex_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, " " + CliSyntax.PREFIX_SESSIONINDEX + "~",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EnterSessionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidSessionIndexZero_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, " " + CliSyntax.PREFIX_SESSIONINDEX + "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EnterSessionCommand.MESSAGE_USAGE));
    }
}
