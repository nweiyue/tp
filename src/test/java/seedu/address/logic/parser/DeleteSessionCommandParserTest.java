package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalSessions.SESSIONNAME_WEEK_ONE;
import static seedu.address.testutil.TypicalSessions.SESSIONNAME_WEEK_ONE_STRING;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteSessionCommand;



public class DeleteSessionCommandParserTest {

    private DeleteSessionCommandParser parser = new DeleteSessionCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, SESSIONNAME_WEEK_ONE_STRING, new DeleteSessionCommand(SESSIONNAME_WEEK_ONE));
    }
}
