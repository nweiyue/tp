package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ConfirmationAcceptCommand extends ConfirmationCommand {
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
}
