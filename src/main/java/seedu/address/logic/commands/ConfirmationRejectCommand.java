package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import seedu.address.model.Model;

public class ConfirmationRejectCommand extends ConfirmationCommand {
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
        return new CommandResult(String.format("%s is not executed.", dangerousCommand.toString()));
    }
}
