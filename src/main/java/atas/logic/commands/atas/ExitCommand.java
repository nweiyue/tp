package atas.logic.commands.atas;

import atas.logic.commands.Command;
import atas.logic.commands.CommandResult;
import atas.model.Model;

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