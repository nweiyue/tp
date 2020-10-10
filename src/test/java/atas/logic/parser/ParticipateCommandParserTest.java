package atas.logic.parser;

import org.junit.jupiter.api.Test;

import atas.logic.commands.sessionlist.session.ParticipateCommand;
import atas.model.attendance.IndexRange;

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
        CommandParserTestUtil.assertParseFailure(parser, "a", IndexRange.MESSAGE_CONSTRAINTS);
        CommandParserTestUtil.assertParseFailure(parser, "@1", IndexRange.MESSAGE_CONSTRAINTS);
        CommandParserTestUtil.assertParseFailure(parser, "_", IndexRange.MESSAGE_CONSTRAINTS);
        CommandParserTestUtil.assertParseFailure(parser, "-1", IndexRange.MESSAGE_CONSTRAINTS);
    }
}
