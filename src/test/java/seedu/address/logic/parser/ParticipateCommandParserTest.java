package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sessioncommands.ParticipateCommand;
import seedu.address.model.attendance.IndexRange;



public class ParticipateCommandParserTest {

    private final ParticipateCommandParser parser = new ParticipateCommandParser();

    @Test
    public void parse_validArgs_successtest1() {
        IndexRange indexRange = new IndexRange("1");
        ParticipateCommand command = new ParticipateCommand(indexRange);
        assertParseSuccess(parser, "1", command);
    }

    @Test
    public void parse_validArgs_successtest2() {
        IndexRange indexRange = new IndexRange("1-3");
        ParticipateCommand command = new ParticipateCommand(indexRange);
        assertParseSuccess(parser, "1-3", command);
    }

    @Test
    public void parse_validArgs_successtest3() {
        IndexRange indexRange = new IndexRange("0-5");
        ParticipateCommand command = new ParticipateCommand(indexRange);
        assertParseSuccess(parser, "0-5", command);
    }

    @Test
    public void parse_validArgs_successtest4() {
        IndexRange indexRange = new IndexRange("1-1");
        ParticipateCommand command = new ParticipateCommand(indexRange);
        assertParseSuccess(parser, "1-1", command);
    }

    @Test
    public void parse_invalidArgs_failure() {
        assertParseFailure(parser, "a", IndexRange.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "@1", IndexRange.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "_", IndexRange.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "-1", IndexRange.MESSAGE_CONSTRAINTS);
    }
}
