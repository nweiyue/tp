package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.AddSessionCommand;
import seedu.address.model.attendance.Session;
import seedu.address.testutil.SessionBuilder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class AddSessionCommandParser {

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
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + MATRICULATION_DESC_AMY + EMAIL_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_MATRICULATION_BOB + EMAIL_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + MATRICULATION_DESC_BOB + VALID_EMAIL_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_MATRICULATION_BOB + VALID_EMAIL_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_MATRICULATION_DESC + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Matriculation.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + MATRICULATION_DESC_BOB + INVALID_EMAIL_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + MATRICULATION_DESC_BOB + EMAIL_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + MATRICULATION_DESC_BOB
                        + EMAIL_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
