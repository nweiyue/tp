package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CONFIRMATION_INPUT;

import seedu.address.logic.commands.ConfirmationCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ConfirmationParser implements Parser<ConfirmationCommand> {
    private static final String ACCEPT_COMMAND_1 = "yes";
    private static final String ACCEPT_COMMAND_2 = "y";
    private static final String REJECT_COMMAND_1 = "no";
    private static final String REJECT_COMMAND_2 = "n";

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
    public ConfirmationCommand parse(String userInput) throws ParseException {
        String lowerCaseInput = userInput.toLowerCase();
        if (lowerCaseInput.equals(ACCEPT_COMMAND_1) || lowerCaseInput.equals(ACCEPT_COMMAND_2)) {
            return confirmationCommand.accept();
        } else if (lowerCaseInput.equals(REJECT_COMMAND_1) || lowerCaseInput.equals(REJECT_COMMAND_2)) {
            return confirmationCommand.reject();
        } else {
            throw new ParseException(MESSAGE_INVALID_CONFIRMATION_INPUT);
        }
    }
}
