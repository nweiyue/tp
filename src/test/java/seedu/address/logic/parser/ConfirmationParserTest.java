package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CONFIRMATION_INPUT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.confirmationcommands.ConfirmationAcceptCommand;
import seedu.address.logic.commands.confirmationcommands.ConfirmationCommand;
import seedu.address.logic.commands.confirmationcommands.ConfirmationRejectCommand;
import seedu.address.logic.commands.confirmationcommands.DangerousCommand;
import seedu.address.logic.commands.studentlistcommands.DeleteCommand;

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
