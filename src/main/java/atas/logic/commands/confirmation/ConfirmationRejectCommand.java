package atas.logic.commands.confirmation;

import static java.util.Objects.requireNonNull;

import atas.logic.commands.CommandResult;
import atas.model.Model;

/**
 * Rejects the execution of a DangerousCommand.
 */
public class ConfirmationRejectCommand extends ConfirmCommand {
    public static final String MESSAGE_REJECT_COMMAND = "%1$s is not executed";

    /**
     * Constructs a {@code ConfirmationRejectCommand} with the specified DangerousCommand.
     */
    public ConfirmationRejectCommand(DangerousCommand dangerousCommand) {
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
        DangerousCommand dangerousCommand = this.getDangerousCommand();
        requireNonNull(dangerousCommand);
        return new CommandResult(String.format(MESSAGE_REJECT_COMMAND, dangerousCommand.toString()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (this == other) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ConfirmationRejectCommand)) {
            return false;
        }

        // calls for checks between the dangerousCommands
        DangerousCommand otherDangerousCommand = ((ConfirmationRejectCommand) other).getDangerousCommand();
        return this.getDangerousCommand().equals(otherDangerousCommand);
    }
}
