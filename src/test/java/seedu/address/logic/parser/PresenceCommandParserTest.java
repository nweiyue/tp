package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.VALID_SESSIONNAME_REC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SESSIONNAME_REC_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PresenceCommand;
import seedu.address.model.attendance.IndexRange;
import seedu.address.model.attendance.SessionName;


public class PresenceCommandParserTest {

    private final PresenceCommandParser parser = new PresenceCommandParser();

    @Test
    public void parse_validArgs_successtest1() {
        IndexRange indexRange = new IndexRange("1");
        SessionName sessionName = new SessionName(VALID_SESSIONNAME_REC);
        PresenceCommand command = new PresenceCommand(sessionName, indexRange);
        assertParseSuccess(parser, "1" + VALID_SESSIONNAME_REC_DESC, command);
    }

    @Test
    public void parse_validArgs_successtest2() {
        IndexRange indexRange = new IndexRange("1-3");
        SessionName sessionName = new SessionName(VALID_SESSIONNAME_REC);
        PresenceCommand command = new PresenceCommand(sessionName, indexRange);
        assertParseSuccess(parser, "1-3" + VALID_SESSIONNAME_REC_DESC, command);
    }

    @Test
    public void parse_validArgs_successtest3() {
        IndexRange indexRange = new IndexRange("0-5");
        SessionName sessionName = new SessionName(VALID_SESSIONNAME_REC);
        PresenceCommand command = new PresenceCommand(sessionName, indexRange);
        assertParseSuccess(parser, "0-5" + VALID_SESSIONNAME_REC_DESC, command);
    }

    @Test
    public void parse_validArgs_successtest4() {
        IndexRange indexRange = new IndexRange("1-1");
        SessionName sessionName = new SessionName(VALID_SESSIONNAME_REC);
        PresenceCommand command = new PresenceCommand(sessionName, indexRange);
        assertParseSuccess(parser, "1-1" + VALID_SESSIONNAME_REC_DESC, command);
    }

    @Test
    public void parse_invalidArgs_failure() {
        assertParseFailure(parser, "a" + VALID_SESSIONNAME_REC_DESC, IndexRange.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "@1" + VALID_SESSIONNAME_REC_DESC, IndexRange.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "_" + VALID_SESSIONNAME_REC_DESC, IndexRange.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "-1" + VALID_SESSIONNAME_REC_DESC, IndexRange.MESSAGE_CONSTRAINTS);
    }
}
