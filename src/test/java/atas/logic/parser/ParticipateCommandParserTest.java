package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import atas.logic.commands.sessionlist.session.ParticipateCommand;
import atas.model.session.IndexRange;

public class ParticipateCommandParserTest {

    private final ParticipateCommandParser parser = new ParticipateCommandParser();

    @Test
    public void parse_validArgs_successtest1() {
        IndexRange indexRange = new IndexRange("1");
        ParticipateCommand command = new ParticipateCommand(indexRange);
        CommandParserTestUtil.assertParseSuccess(parser, "1", command);
    }

    @Test
    public void parse_validArgs_successtest2() {
        IndexRange indexRange = new IndexRange("1-3");
        ParticipateCommand command = new ParticipateCommand(indexRange);
        CommandParserTestUtil.assertParseSuccess(parser, "1-3", command);
    }

    @Test
    public void parse_validArgs_successtest3() {
        IndexRange indexRange = new IndexRange("0-5");
        ParticipateCommand command = new ParticipateCommand(indexRange);
        CommandParserTestUtil.assertParseSuccess(parser, "0-5", command);
    }

    @Test
    public void parse_validArgs_successtest4() {
        IndexRange indexRange = new IndexRange("1-1");
        ParticipateCommand command = new ParticipateCommand(indexRange);
        CommandParserTestUtil.assertParseSuccess(parser, "1-1", command);
    }

    @Test
    public void parse_invalidArgs_failure() {
        CommandParserTestUtil.assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, IndexRange.MESSAGE_CONSTRAINTS));
        CommandParserTestUtil.assertParseFailure(parser, "@1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, IndexRange.MESSAGE_CONSTRAINTS));
        CommandParserTestUtil.assertParseFailure(parser, "_",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, IndexRange.MESSAGE_CONSTRAINTS));
        CommandParserTestUtil.assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, IndexRange.MESSAGE_CONSTRAINTS));
    }
}
