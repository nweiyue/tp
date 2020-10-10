package atas.logic.commands.confirmation;

import static java.util.Objects.requireNonNull;

import atas.logic.commands.CommandResult;
import atas.logic.commands.exceptions.CommandException;
import atas.model.Model;

/**
 * Confirms an execution of a DangerousCommand.
 */
public class ConfirmationAcceptCommand extends ConfirmCommand {
    /**
     * Constructs a {@code ConfirmationAcceptCommand} with the specified DangerousCommand.
     */
    public ConfirmationAcceptCommand(DangerousCommand dangerousCommand) {
        super(dangerousCommand);
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        DangerousCommand dangerousCommand = this.getDangerousCommand();
        requireNonNull(dangerousCommand);
        return dangerousCommand.execute(model);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (this == other) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ConfirmationAcceptCommand)) {
            return false;
        }

        // calls for checks between the dangerousCommands
        DangerousCommand otherDangerousCommand = ((ConfirmationAcceptCommand) other).getDangerousCommand();
        return this.getDangerousCommand().equals(otherDangerousCommand);
    }
}
