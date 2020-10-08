package seedu.address.logic.commands.confirmationcommands;

import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

/**
 * Confirms whether or not to execute a command.
 */
public class ConfirmationCommand extends ConfirmCommand {
    public static final String MESSAGE_CONFIRMATION = "(yes/no)";
    public static final String MESSAGE_CONFIRMATION_DELETE = "Delete %1$d? " + MESSAGE_CONFIRMATION;
    public static final String MESSAGE_CONFIRMATION_EDIT = "Edit %1$d? " + MESSAGE_CONFIRMATION;
    public static final String MESSAGE_CONFIRMATION_CLEAR = "Clear list? " + MESSAGE_CONFIRMATION;
    public static final String ACCEPT_COMMAND_FULL = "yes";
    public static final String ACCEPT_COMMAND_SHORT = "y";
    public static final String REJECT_COMMAND_FULL = "no";
    public static final String REJECT_COMMAND_SHORT = "n";

    /**
     * Constructs a {@code ConfirmationCommand} with the specified DangerousCommand.
     */
    public ConfirmationCommand(DangerousCommand dangerousCommand) {
        super(dangerousCommand);
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(String.format("%s? %s", getDangerousCommand(), MESSAGE_CONFIRMATION));
    }

    public ConfirmationAcceptCommand accept() {
        return new ConfirmationAcceptCommand(getDangerousCommand());
    }

    public ConfirmationRejectCommand reject() {
        return new ConfirmationRejectCommand(getDangerousCommand());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (this == other) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ConfirmationCommand)) {
            return false;
        }

        // calls for checks between the dangerousCommands
        DangerousCommand otherDangerousCommand = ((ConfirmationCommand) other).getDangerousCommand();
        return this.getDangerousCommand().equals(otherDangerousCommand);
    }
}
