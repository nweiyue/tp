package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.logic.commands.CommandTestUtil.INVALID_SESSIONDATE_DESC;
import static atas.logic.commands.CommandTestUtil.INVALID_SESSIONNAME_DESC;
import static atas.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static atas.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static atas.logic.commands.CommandTestUtil.VALID_SESSIONDATE_CON;
import static atas.logic.commands.CommandTestUtil.VALID_SESSIONDATE_CON_DESC;
import static atas.logic.commands.CommandTestUtil.VALID_SESSIONDATE_REC_DESC;
import static atas.logic.commands.CommandTestUtil.VALID_SESSIONNAME_CON;
import static atas.logic.commands.CommandTestUtil.VALID_SESSIONNAME_CON_DESC;
import static atas.logic.commands.CommandTestUtil.VALID_SESSIONNAME_REC_DESC;
import static atas.logic.parser.CommandParserTestUtil.assertParseFailure;
import static atas.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import atas.logic.commands.sessionlist.AddSessionCommand;
import atas.model.session.Session;
import atas.model.session.SessionDate;
import atas.model.session.SessionName;
import atas.testutil.SessionBuilder;

public class AddSessionCommandParserTest {

    private final AddSessionCommandParser parser = new AddSessionCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Session expectedSession = new SessionBuilder().withSessionName(VALID_SESSIONNAME_CON)
                .withSessionDate(VALID_SESSIONDATE_CON).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + VALID_SESSIONNAME_CON_DESC
                + VALID_SESSIONDATE_CON_DESC, new AddSessionCommand(expectedSession));

        // multiple session names - last name accepted
        assertParseSuccess(parser, VALID_SESSIONNAME_REC_DESC + VALID_SESSIONDATE_CON_DESC
                + VALID_SESSIONNAME_CON_DESC, new AddSessionCommand(expectedSession));

        // multiple session dates - last date accepted
        assertParseSuccess(parser, VALID_SESSIONNAME_CON_DESC + VALID_SESSIONDATE_REC_DESC
                + VALID_SESSIONDATE_CON_DESC, new AddSessionCommand(expectedSession));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        assertParseFailure(parser, VALID_SESSIONNAME_CON_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSessionCommand.MESSAGE_USAGE));
        assertParseFailure(parser, VALID_SESSIONDATE_CON_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSessionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid session name
        assertParseFailure(parser, INVALID_SESSIONNAME_DESC + VALID_SESSIONDATE_REC_DESC,
                SessionName.MESSAGE_CONSTRAINTS);

        // invalid session date
        assertParseFailure(parser, VALID_SESSIONNAME_REC_DESC + INVALID_SESSIONDATE_DESC,
                SessionDate.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + VALID_SESSIONNAME_REC_DESC + VALID_SESSIONDATE_REC_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSessionCommand.MESSAGE_USAGE));
    }
}
