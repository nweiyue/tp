package seedu.address.logic.commands.atascommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Bye bye most beautiful and smartest TA ever :(";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(String.format(MESSAGE_EXIT_ACKNOWLEDGEMENT), false, null, true);
    }

}
