package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static atas.testutil.TypicalSessions.SESSIONNAME_WEEK_ONE;
import static atas.testutil.TypicalSessions.SESSIONNAME_WEEK_ONE_STRING;

import org.junit.jupiter.api.Test;

import atas.logic.commands.sessionlist.DeleteSessionCommand;

public class DeleteSessionCommandParserTest {

    private DeleteSessionCommandParser parser = new DeleteSessionCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, SESSIONNAME_WEEK_ONE_STRING, new DeleteSessionCommand(SESSIONNAME_WEEK_ONE));
    }

    @Test
    public void parse_invalidSessionName_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "~",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSessionCommand.MESSAGE_USAGE));
    }
}
