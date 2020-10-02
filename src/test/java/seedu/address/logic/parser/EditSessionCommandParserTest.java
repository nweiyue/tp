package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SESSIONDATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SESSIONNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SESSIONDATE_CON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SESSIONDATE_CON_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SESSIONDATE_REC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SESSIONNAME_CON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SESSIONNAME_CON_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SESSIONNAME_REC_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalSessions.SESSIONNAME_WEEK_ONE;
import static seedu.address.testutil.TypicalSessions.SESSIONNAME_WEEK_ONE_STRING;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditSessionCommand;
import seedu.address.model.attendance.SessionDate;
import seedu.address.model.attendance.SessionName;
import seedu.address.testutil.EditSessionDescriptorBuilder;



public class EditSessionCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditSessionCommand.MESSAGE_USAGE);

    private EditSessionCommandParser parser = new EditSessionCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no sessionName of session specified
        assertParseFailure(parser, VALID_SESSIONNAME_CON_DESC, MESSAGE_INVALID_FORMAT);

        // no sessionName of session and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // empty preamble
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // whitespace peramble
        assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "tut" + INVALID_SESSIONNAME_DESC, SessionName.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "tut" + INVALID_SESSIONDATE_DESC, SessionDate.MESSAGE_CONSTRAINTS);

        // invalid sessionName followed by valid sessionDate
        assertParseFailure(parser, "tut" + INVALID_SESSIONNAME_DESC + VALID_SESSIONDATE_REC_DESC,
                SessionName.MESSAGE_CONSTRAINTS);

        // valid sessionDate followed by invalid sessionName.
        assertParseFailure(parser, "tut" + VALID_SESSIONNAME_REC_DESC + INVALID_SESSIONDATE_DESC,
                SessionDate.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "tut" + INVALID_SESSIONNAME_DESC + INVALID_SESSIONDATE_DESC,
                SessionName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = SESSIONNAME_WEEK_ONE_STRING + VALID_SESSIONNAME_CON_DESC + VALID_SESSIONDATE_CON_DESC;

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder()
                .withSessionName(VALID_SESSIONNAME_CON)
                .withSessionDate(VALID_SESSIONDATE_CON)
                .build();
        EditSessionCommand expectedCommand = new EditSessionCommand(SESSIONNAME_WEEK_ONE, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = SESSIONNAME_WEEK_ONE_STRING + VALID_SESSIONNAME_CON_DESC;

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder()
                .withSessionName(VALID_SESSIONNAME_CON)
                .build();
        EditSessionCommand expectedCommand = new EditSessionCommand(SESSIONNAME_WEEK_ONE, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // sessionName
        String userInput1 = SESSIONNAME_WEEK_ONE_STRING + VALID_SESSIONNAME_CON_DESC;

        EditSessionCommand.EditSessionDescriptor descriptor1 = new EditSessionDescriptorBuilder()
                .withSessionName(VALID_SESSIONNAME_CON)
                .build();
        EditSessionCommand expectedCommand1 = new EditSessionCommand(SESSIONNAME_WEEK_ONE, descriptor1);

        assertParseSuccess(parser, userInput1, expectedCommand1);


        // sessionDate
        String userInput2 = SESSIONNAME_WEEK_ONE_STRING + VALID_SESSIONDATE_CON_DESC;

        EditSessionCommand.EditSessionDescriptor descriptor2 = new EditSessionDescriptorBuilder()
                .withSessionDate(VALID_SESSIONDATE_CON)
                .build();
        EditSessionCommand expectedCommand2 = new EditSessionCommand(SESSIONNAME_WEEK_ONE, descriptor2);

        assertParseSuccess(parser, userInput2, expectedCommand2);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = SESSIONNAME_WEEK_ONE_STRING
                + VALID_SESSIONNAME_CON_DESC + VALID_SESSIONDATE_CON_DESC
                + VALID_SESSIONNAME_CON_DESC + VALID_SESSIONDATE_CON_DESC
                + VALID_SESSIONNAME_CON_DESC + VALID_SESSIONDATE_CON_DESC;

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder()
                .withSessionName(VALID_SESSIONNAME_CON)
                .withSessionDate(VALID_SESSIONDATE_CON)
                .build();
        EditSessionCommand expectedCommand = new EditSessionCommand(SESSIONNAME_WEEK_ONE, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput1 = SESSIONNAME_WEEK_ONE_STRING;

        EditSessionCommand.EditSessionDescriptor descriptor1 = new EditSessionDescriptorBuilder().build();
        EditSessionCommand expectedCommand1 = new EditSessionCommand(SESSIONNAME_WEEK_ONE, descriptor1);

        assertParseFailure(parser, userInput1, EditSessionCommand.MESSAGE_NOT_EDITED);

        // other valid values specified
        String userInput = SESSIONNAME_WEEK_ONE_STRING + VALID_SESSIONNAME_CON_DESC + VALID_SESSIONDATE_CON_DESC;

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder()
                .withSessionName(VALID_SESSIONNAME_CON)
                .withSessionDate(VALID_SESSIONDATE_CON)
                .build();
        EditSessionCommand expectedCommand = new EditSessionCommand(SESSIONNAME_WEEK_ONE, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
