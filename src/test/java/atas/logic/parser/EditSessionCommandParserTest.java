package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.logic.commands.CommandTestUtil.INVALID_SESSIONDATE_DESC;
import static atas.logic.commands.CommandTestUtil.INVALID_SESSIONNAME_DESC;
import static atas.logic.commands.CommandTestUtil.VALID_SESSIONDATE_CON;
import static atas.logic.commands.CommandTestUtil.VALID_SESSIONDATE_CON_DESC;
import static atas.logic.commands.CommandTestUtil.VALID_SESSIONDATE_REC_DESC;
import static atas.logic.commands.CommandTestUtil.VALID_SESSIONNAME_CON;
import static atas.logic.commands.CommandTestUtil.VALID_SESSIONNAME_CON_DESC;
import static atas.logic.commands.CommandTestUtil.VALID_SESSIONNAME_REC_DESC;
import static atas.testutil.TypicalSessions.SESSIONNAME_WEEK_ONE;
import static atas.testutil.TypicalSessions.SESSIONNAME_WEEK_ONE_STRING;

import org.junit.jupiter.api.Test;

import atas.logic.commands.sessionlistcommands.EditSessionCommand;
import atas.model.attendance.SessionDate;
import atas.model.attendance.SessionName;
import atas.testutil.EditSessionDescriptorBuilder;

public class EditSessionCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditSessionCommand.MESSAGE_USAGE);

    private EditSessionCommandParser parser = new EditSessionCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no sessionName of session specified
        CommandParserTestUtil.assertParseFailure(parser, VALID_SESSIONNAME_CON_DESC, MESSAGE_INVALID_FORMAT);

        // no sessionName of session and no field specified
        CommandParserTestUtil.assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // empty preamble
        CommandParserTestUtil.assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // whitespace peramble
        CommandParserTestUtil.assertParseFailure(parser, " ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        CommandParserTestUtil.assertParseFailure(parser, "tut" + INVALID_SESSIONNAME_DESC,
                SessionName.MESSAGE_CONSTRAINTS);
        CommandParserTestUtil.assertParseFailure(parser, "tut" + INVALID_SESSIONDATE_DESC,
                SessionDate.MESSAGE_CONSTRAINTS);

        // invalid sessionName followed by valid sessionDate
        CommandParserTestUtil.assertParseFailure(parser, "tut" + INVALID_SESSIONNAME_DESC
                + VALID_SESSIONDATE_REC_DESC, SessionName.MESSAGE_CONSTRAINTS);

        // valid sessionDate followed by invalid sessionName.
        CommandParserTestUtil.assertParseFailure(parser, "tut" + VALID_SESSIONNAME_REC_DESC
                        + INVALID_SESSIONDATE_DESC, SessionDate.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        CommandParserTestUtil.assertParseFailure(parser, "tut" + INVALID_SESSIONNAME_DESC
                        + INVALID_SESSIONDATE_DESC, SessionName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = SESSIONNAME_WEEK_ONE_STRING + VALID_SESSIONNAME_CON_DESC + VALID_SESSIONDATE_CON_DESC;

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder()
                .withSessionName(VALID_SESSIONNAME_CON)
                .withSessionDate(VALID_SESSIONDATE_CON)
                .build();
        EditSessionCommand expectedCommand = new EditSessionCommand(SESSIONNAME_WEEK_ONE, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = SESSIONNAME_WEEK_ONE_STRING + VALID_SESSIONNAME_CON_DESC;

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder()
                .withSessionName(VALID_SESSIONNAME_CON)
                .build();
        EditSessionCommand expectedCommand = new EditSessionCommand(SESSIONNAME_WEEK_ONE, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // sessionName
        String userInput1 = SESSIONNAME_WEEK_ONE_STRING + VALID_SESSIONNAME_CON_DESC;

        EditSessionCommand.EditSessionDescriptor descriptor1 = new EditSessionDescriptorBuilder()
                .withSessionName(VALID_SESSIONNAME_CON)
                .build();
        EditSessionCommand expectedCommand1 = new EditSessionCommand(SESSIONNAME_WEEK_ONE, descriptor1);

        CommandParserTestUtil.assertParseSuccess(parser, userInput1, expectedCommand1);


        // sessionDate
        String userInput2 = SESSIONNAME_WEEK_ONE_STRING + VALID_SESSIONDATE_CON_DESC;

        EditSessionCommand.EditSessionDescriptor descriptor2 = new EditSessionDescriptorBuilder()
                .withSessionDate(VALID_SESSIONDATE_CON)
                .build();
        EditSessionCommand expectedCommand2 = new EditSessionCommand(SESSIONNAME_WEEK_ONE, descriptor2);

        CommandParserTestUtil.assertParseSuccess(parser, userInput2, expectedCommand2);
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

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput1 = SESSIONNAME_WEEK_ONE_STRING;

        EditSessionCommand.EditSessionDescriptor descriptor1 = new EditSessionDescriptorBuilder().build();
        EditSessionCommand expectedCommand1 = new EditSessionCommand(SESSIONNAME_WEEK_ONE, descriptor1);

        CommandParserTestUtil.assertParseFailure(parser, userInput1, EditSessionCommand.MESSAGE_NOT_EDITED);

        // other valid values specified
        String userInput = SESSIONNAME_WEEK_ONE_STRING + VALID_SESSIONNAME_CON_DESC + VALID_SESSIONDATE_CON_DESC;

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder()
                .withSessionName(VALID_SESSIONNAME_CON)
                .withSessionDate(VALID_SESSIONDATE_CON)
                .build();
        EditSessionCommand expectedCommand = new EditSessionCommand(SESSIONNAME_WEEK_ONE, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }
}
