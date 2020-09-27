package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

public class ConfirmationCommand extends Command {
    private final DangerousCommand dangerousCommand;

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
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(String.format("%s? (yes/no)", dangerousCommand));
    }

    public DangerousCommand getDangerousCommand() {
        return this.dangerousCommand;
    }
}
