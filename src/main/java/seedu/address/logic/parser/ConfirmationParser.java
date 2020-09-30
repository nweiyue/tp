package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CONFIRMATION_INPUT;
import static seedu.address.logic.commands.ConfirmationCommand.ACCEPT_COMMAND_FULL;
import static seedu.address.logic.commands.ConfirmationCommand.ACCEPT_COMMAND_SHORT;
import static seedu.address.logic.commands.ConfirmationCommand.REJECT_COMMAND_FULL;
import static seedu.address.logic.commands.ConfirmationCommand.REJECT_COMMAND_SHORT;

import seedu.address.logic.commands.ConfirmCommand;
import seedu.address.logic.commands.ConfirmationCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ConfirmationParser implements Parser<ConfirmCommand> {
    private final ConfirmationCommand confirmationCommand;

    public ConfirmationParser(ConfirmationCommand confirmationCommand) {
        this.confirmationCommand = confirmationCommand;
    }

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public ConfirmCommand parse(String userInput) throws ParseException {
        String lowerCaseInput = userInput.toLowerCase();
        if (lowerCaseInput.equals(ACCEPT_COMMAND_FULL) || lowerCaseInput.equals(ACCEPT_COMMAND_SHORT)) {
            return confirmationCommand.accept();
        } else if (lowerCaseInput.equals(REJECT_COMMAND_FULL) || lowerCaseInput.equals(REJECT_COMMAND_SHORT)) {
            return confirmationCommand.reject();
        } else {
            throw new ParseException(MESSAGE_INVALID_CONFIRMATION_INPUT);
        }
    }
}
