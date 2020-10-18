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
import static atas.testutil.TypicalIndexes.INDEX_FIRST_SESSION;
import static atas.testutil.TypicalIndexes.INDEX_SECOND_SESSION;

import org.junit.jupiter.api.Test;

import atas.commons.core.index.Index;
import atas.logic.commands.sessionlist.EditSessionCommand;
import atas.model.session.SessionDate;
import atas.model.session.SessionName;
import atas.testutil.EditSessionDescriptorBuilder;

public class EditSessionCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditSessionCommand.MESSAGE_USAGE);

    private EditSessionCommandParser parser = new EditSessionCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        CommandParserTestUtil.assertParseFailure(parser, VALID_SESSIONNAME_CON_DESC, MESSAGE_INVALID_FORMAT);

        // no field specified
        CommandParserTestUtil.assertParseFailure(parser, "1", EditSessionCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        CommandParserTestUtil.assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        CommandParserTestUtil.assertParseFailure(parser, "-5" + VALID_SESSIONDATE_CON_DESC, MESSAGE_INVALID_FORMAT);

        // zero index
        CommandParserTestUtil.assertParseFailure(parser, "0" + VALID_SESSIONDATE_CON_DESC, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        CommandParserTestUtil.assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        CommandParserTestUtil.assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        CommandParserTestUtil.assertParseFailure(parser, "1" + INVALID_SESSIONNAME_DESC,
                SessionName.MESSAGE_CONSTRAINTS);
        CommandParserTestUtil.assertParseFailure(parser, "1" + INVALID_SESSIONDATE_DESC,
                SessionDate.MESSAGE_CONSTRAINTS);

        // invalid sessionName followed by valid sessionDate
        CommandParserTestUtil.assertParseFailure(parser, "1" + INVALID_SESSIONNAME_DESC
                + VALID_SESSIONDATE_REC_DESC, SessionName.MESSAGE_CONSTRAINTS);

        // valid sessionDate followed by invalid sessionName.
        CommandParserTestUtil.assertParseFailure(parser, "1" + VALID_SESSIONNAME_REC_DESC
                        + INVALID_SESSIONDATE_DESC, SessionDate.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        CommandParserTestUtil.assertParseFailure(parser, "1" + INVALID_SESSIONNAME_DESC
                        + INVALID_SESSIONDATE_DESC, SessionName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_SESSION;
        String userInput = targetIndex.getOneBased() + VALID_SESSIONNAME_CON_DESC + VALID_SESSIONDATE_CON_DESC;

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder()
                .withSessionName(VALID_SESSIONNAME_CON)
                .withSessionDate(VALID_SESSIONDATE_CON)
                .build();
        EditSessionCommand expectedCommand = new EditSessionCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_SESSION;
        String userInput = targetIndex.getOneBased() + VALID_SESSIONNAME_CON_DESC;

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder()
                .withSessionName(VALID_SESSIONNAME_CON)
                .build();
        EditSessionCommand expectedCommand = new EditSessionCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Index targetIndex = INDEX_FIRST_SESSION;

        // sessionName
        String userInput1 = targetIndex.getOneBased() + VALID_SESSIONNAME_CON_DESC;

        EditSessionCommand.EditSessionDescriptor descriptor1 = new EditSessionDescriptorBuilder()
                .withSessionName(VALID_SESSIONNAME_CON)
                .build();
        EditSessionCommand expectedCommand1 = new EditSessionCommand(targetIndex, descriptor1);

        CommandParserTestUtil.assertParseSuccess(parser, userInput1, expectedCommand1);


        // sessionDate
        String userInput2 = targetIndex.getOneBased() + VALID_SESSIONDATE_CON_DESC;

        EditSessionCommand.EditSessionDescriptor descriptor2 = new EditSessionDescriptorBuilder()
                .withSessionDate(VALID_SESSIONDATE_CON)
                .build();
        EditSessionCommand expectedCommand2 = new EditSessionCommand(targetIndex, descriptor2);

        CommandParserTestUtil.assertParseSuccess(parser, userInput2, expectedCommand2);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_SESSION;

        String userInput = targetIndex.getOneBased()
                + VALID_SESSIONNAME_CON_DESC + VALID_SESSIONDATE_CON_DESC
                + VALID_SESSIONNAME_CON_DESC + VALID_SESSIONDATE_CON_DESC
                + VALID_SESSIONNAME_CON_DESC + VALID_SESSIONDATE_CON_DESC;

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder()
                .withSessionName(VALID_SESSIONNAME_CON)
                .withSessionDate(VALID_SESSIONDATE_CON)
                .build();
        EditSessionCommand expectedCommand = new EditSessionCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        Index targetIndex = INDEX_FIRST_SESSION;
        // no other valid values specified
        String userInput1 = targetIndex.getOneBased() + "";

        EditSessionCommand.EditSessionDescriptor descriptor1 = new EditSessionDescriptorBuilder().build();
        EditSessionCommand expectedCommand1 = new EditSessionCommand(targetIndex, descriptor1);

        CommandParserTestUtil.assertParseFailure(parser, userInput1, EditSessionCommand.MESSAGE_NOT_EDITED);

        // other valid values specified
        String userInput = targetIndex.getOneBased() + VALID_SESSIONNAME_CON_DESC + VALID_SESSIONDATE_CON_DESC;

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder()
                .withSessionName(VALID_SESSIONNAME_CON)
                .withSessionDate(VALID_SESSIONDATE_CON)
                .build();
        EditSessionCommand expectedCommand = new EditSessionCommand(targetIndex, descriptor);

        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }
}
