package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_CONFIRMATION_INPUT;

import seedu.address.logic.commands.ConfirmationCommand;
import seedu.address.logic.commands.DangerousCommand;
import seedu.address.logic.commands.RejectionCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ConfirmationParser implements Parser<ConfirmationCommand> {
    private static final String ACCEPT_COMMAND_1 = "yes";
    private static final String ACCEPT_COMMAND_2 = "y";
    private static final String REJECT_COMMAND_1 = "no";
    private static final String REJECT_COMMAND_2 = "n";

    private final DangerousCommand dangerousCommand;

    public ConfirmationParser(DangerousCommand dangerousCommand) {
        this.dangerousCommand = dangerousCommand;
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
            return new ConfirmationCommand(dangerousCommand);
        } else if (lowerCaseInput.equals(REJECT_COMMAND_1) || lowerCaseInput.equals(REJECT_COMMAND_2)) {
            return new RejectionCommand(dangerousCommand);
        } else {
            throw new ParseException(MESSAGE_INVALID_CONFIRMATION_INPUT);
        }
    }
}
