package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.testutil.TypicalIndexes.INDEX_FIRST_SESSION;

import org.junit.jupiter.api.Test;

import atas.logic.commands.sessionlist.DeleteSessionCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteSessionCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteSessionCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteSessionCommandParserTest {

    private DeleteSessionCommandParser parser = new DeleteSessionCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        CommandParserTestUtil.assertParseSuccess(parser, "1", new DeleteSessionCommand(INDEX_FIRST_SESSION));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSessionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidSessionName_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "~",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteSessionCommand.MESSAGE_USAGE));
    }

}
