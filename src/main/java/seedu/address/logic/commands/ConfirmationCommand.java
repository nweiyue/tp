package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Confirms whether or not to execute a command.
 */
public class ConfirmationCommand extends Command {
    public static final String MESSAGE_CONFIRMATION = "(yes/no)";
    public static final String MESSAGE_CONFIRMATION_DELETE = "Delete %1$d? " + MESSAGE_CONFIRMATION;
    public static final String MESSAGE_CONFIRMATION_EDIT = "Edit %1$d? " + MESSAGE_CONFIRMATION;
    public static final String MESSAGE_CONFIRMATION_CLEAR = "Clear list? " + MESSAGE_CONFIRMATION;
    public static final String ACCEPT_COMMAND_1 = "yes";
    public static final String ACCEPT_COMMAND_2 = "y";
    public static final String REJECT_COMMAND_1 = "no";
    public static final String REJECT_COMMAND_2 = "n";

    private final DangerousCommand dangerousCommand;

    /**
     * Constructs a {@code ConfirmationCommand} with the specified DangerousCommand.
     */
    public ConfirmationCommand(DangerousCommand dangerousCommand) {
        this.dangerousCommand = dangerousCommand;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(String.format("%s? %s", dangerousCommand, MESSAGE_CONFIRMATION));
    }

    public DangerousCommand getDangerousCommand() {
        return this.dangerousCommand;
    }

    public ConfirmationAcceptCommand accept() {
        return new ConfirmationAcceptCommand(dangerousCommand);
    }

    public ConfirmationRejectCommand reject() {
        return new ConfirmationRejectCommand(dangerousCommand);
    }
}
