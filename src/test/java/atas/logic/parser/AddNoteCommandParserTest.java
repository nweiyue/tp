package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.logic.parser.CommandParserTestUtil.assertParseFailure;
import static atas.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import atas.logic.commands.memo.AddNoteCommand;

public class AddNoteCommandParserTest {

    private AddNoteCommandParser parser = new AddNoteCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsAddNoteCommand() {
        // no leading and trailing whitespaces
        AddNoteCommand expectedAddNoteCommand =
                new AddNoteCommand("note");
        assertParseSuccess(parser, " note", expectedAddNoteCommand);
    }

    @Test
    public void parse_validArgsOnlyWhiteSpace_returnsAddNoteCommand() {
        // no leading and trailing whitespaces
        AddNoteCommand expectedAddNoteCommand =
                new AddNoteCommand("  ");
        assertParseSuccess(parser, "   ", expectedAddNoteCommand);
    }

}
