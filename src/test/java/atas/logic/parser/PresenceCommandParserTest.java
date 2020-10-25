package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.logic.commands.CommandTestUtil.VALID_SESSIONNAME_REC_DESC;
import static atas.logic.commands.sessionlist.session.PresenceCommand.MESSAGE_USAGE;

import org.junit.jupiter.api.Test;

import atas.logic.commands.sessionlist.session.PresenceCommand;
import atas.model.Model;
import atas.model.session.IndexRange;

public class PresenceCommandParserTest {

    private final PresenceCommandParser parser = new PresenceCommandParser();
    private Model model;

    @Test
    public void parse_validArgs_successtest1() {
        IndexRange indexRange = new IndexRange("1");
        PresenceCommand command = new PresenceCommand(indexRange);
        CommandParserTestUtil.assertParseSuccess(parser, "1", command);
    }

    @Test
    public void parse_validArgs_successtest2() {
        IndexRange indexRange = new IndexRange("1-3");
        PresenceCommand command = new PresenceCommand(indexRange);
        CommandParserTestUtil.assertParseSuccess(parser, "1-3", command);
    }

    @Test
    public void parse_validArgs_successtest3() {
        IndexRange indexRange = new IndexRange("1-1");
        PresenceCommand command = new PresenceCommand(indexRange);
        CommandParserTestUtil.assertParseSuccess(parser, "1-1", command);
    }

    @Test
    public void parse_invalidArgs_failure() {
        CommandParserTestUtil.assertParseFailure(parser, "a" + VALID_SESSIONNAME_REC_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        CommandParserTestUtil.assertParseFailure(parser, "@1" + VALID_SESSIONNAME_REC_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        CommandParserTestUtil.assertParseFailure(parser, "_" + VALID_SESSIONNAME_REC_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        CommandParserTestUtil.assertParseFailure(parser, "-1" + VALID_SESSIONNAME_REC_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        CommandParserTestUtil.assertParseFailure(parser, "0-5" + VALID_SESSIONNAME_REC_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        CommandParserTestUtil.assertParseFailure(parser, "6-0" + VALID_SESSIONNAME_REC_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }
}
