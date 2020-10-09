package atas.logic.parser;

import static atas.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static atas.testutil.TypicalSessions.SESSIONNAME_WEEK_ONE;
import static atas.testutil.TypicalSessions.SESSIONNAME_WEEK_ONE_STRING;

import org.junit.jupiter.api.Test;

import atas.logic.commands.sessionlistcommands.DeleteSessionCommand;

public class DeleteSessionCommandParserTest {

    private DeleteSessionCommandParser parser = new DeleteSessionCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, SESSIONNAME_WEEK_ONE_STRING, new DeleteSessionCommand(SESSIONNAME_WEEK_ONE));
    }
}
