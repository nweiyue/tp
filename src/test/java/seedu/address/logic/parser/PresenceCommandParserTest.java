package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.VALID_SESSIONNAME_REC_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sessioncommands.PresenceCommand;
import seedu.address.model.Model;
import seedu.address.model.attendance.IndexRange;


public class PresenceCommandParserTest {

    private final PresenceCommandParser parser = new PresenceCommandParser();
    private Model model;

    @Test
    public void parse_validArgs_successtest1() {
        IndexRange indexRange = new IndexRange("1");
        PresenceCommand command = new PresenceCommand(indexRange);
        assertParseSuccess(parser, "1", command);
    }

    @Test
    public void parse_validArgs_successtest2() {
        IndexRange indexRange = new IndexRange("1-3");
        PresenceCommand command = new PresenceCommand(indexRange);
        assertParseSuccess(parser, "1-3", command);
    }

    @Test
    public void parse_validArgs_successtest3() {
        IndexRange indexRange = new IndexRange("0-5");
        PresenceCommand command = new PresenceCommand(indexRange);
        assertParseSuccess(parser, "0-5", command);
    }

    @Test
    public void parse_validArgs_successtest4() {
        IndexRange indexRange = new IndexRange("1-1");
        PresenceCommand command = new PresenceCommand(indexRange);
        assertParseSuccess(parser, "1-1", command);
    }

    @Test
    public void parse_invalidArgs_failure() {
        assertParseFailure(parser, "a" + VALID_SESSIONNAME_REC_DESC, IndexRange.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "@1" + VALID_SESSIONNAME_REC_DESC, IndexRange.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "_" + VALID_SESSIONNAME_REC_DESC, IndexRange.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "-1" + VALID_SESSIONNAME_REC_DESC, IndexRange.MESSAGE_CONSTRAINTS);
    }
}
