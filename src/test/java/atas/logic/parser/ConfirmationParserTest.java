package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_CONFIRMATION_INPUT;
import static atas.logic.parser.CommandParserTestUtil.assertParseFailure;
import static atas.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static atas.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import atas.logic.commands.confirmation.ConfirmationAcceptCommand;
import atas.logic.commands.confirmation.ConfirmationCommand;
import atas.logic.commands.confirmation.ConfirmationRejectCommand;
import atas.logic.commands.confirmation.DangerousCommand;
import atas.logic.commands.students.DeleteCommand;

public class ConfirmationParserTest {

    private final DangerousCommand dangerousCommand = new DeleteCommand(INDEX_FIRST_PERSON);

    @Test
    public void parse_validAcceptCommandWordFull_returnsConfirmationAcceptCommand() {
        ConfirmationCommand confirmationCommand = new ConfirmationCommand(dangerousCommand);
        ConfirmationParser parser = new ConfirmationParser(confirmationCommand);
        ConfirmationAcceptCommand expectedCommand =
                new ConfirmationAcceptCommand(new DeleteCommand(INDEX_FIRST_PERSON));
        assertParseSuccess(parser, ConfirmationCommand.ACCEPT_COMMAND_FULL, expectedCommand);
    }

    @Test
    public void parse_validAcceptCommandWordShort_returnsConfirmationAcceptCommand() {
        ConfirmationCommand confirmationCommand = new ConfirmationCommand(dangerousCommand);
        ConfirmationParser parser = new ConfirmationParser(confirmationCommand);
        ConfirmationAcceptCommand expectedCommand =
                new ConfirmationAcceptCommand(new DeleteCommand(INDEX_FIRST_PERSON));
        assertParseSuccess(parser, ConfirmationCommand.ACCEPT_COMMAND_SHORT, expectedCommand);
    }

    @Test
    public void parse_validRejectCommandWordFull_returnsConfirmationRejectCommand() {
        ConfirmationCommand confirmationCommand = new ConfirmationCommand(dangerousCommand);
        ConfirmationParser parser = new ConfirmationParser(confirmationCommand);
        ConfirmationRejectCommand expectedCommand =
                new ConfirmationRejectCommand(new DeleteCommand(INDEX_FIRST_PERSON));
        assertParseSuccess(parser, ConfirmationCommand.REJECT_COMMAND_FULL, expectedCommand);
    }

    @Test
    public void parse_validRejectCommandWordShort_returnsConfirmationRejectCommand() {
        ConfirmationCommand confirmationCommand = new ConfirmationCommand(dangerousCommand);
        ConfirmationParser parser = new ConfirmationParser(confirmationCommand);
        ConfirmationRejectCommand expectedCommand =
                new ConfirmationRejectCommand(new DeleteCommand(INDEX_FIRST_PERSON));
        assertParseSuccess(parser, ConfirmationCommand.REJECT_COMMAND_SHORT, expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        ConfirmationCommand confirmationCommand = new ConfirmationCommand(dangerousCommand);
        ConfirmationParser parser = new ConfirmationParser(confirmationCommand);
        assertParseFailure(parser, "a", MESSAGE_INVALID_CONFIRMATION_INPUT);
    }
}
