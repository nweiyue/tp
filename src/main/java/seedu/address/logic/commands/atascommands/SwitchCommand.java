package seedu.address.logic.commands.atascommands;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.ui.Tab;

import static java.util.Objects.requireNonNull;

/**
 * Switches tab using the name of the destination tab.
 * Name of destination tab is case insensitive.
 */
public class SwitchCommand extends Command {

    public static final String COMMAND_WORD = "switch";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Switches to the specified tab (case-insensitive).\n"
            + "Parameters: TAB_NAME (must be an existing tab)\n"
            + "Example: " + COMMAND_WORD + " attendance";

    public static final String MESSAGE_SWITCH_TAB_SUCCESS = "Switched to %1$s tab.";

    public static final String MESSAGE_INVALID_TAB = "Tab does not exist!";

    public static final String MESSAGE_ALREADY_ON_TAB = "Already at %1$s tab!";

    private final String tabName;

    public SwitchCommand(String tabName) {
        this.tabName = tabName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Tab tab;
        requireNonNull(tabName);
        String trimmedTab = tabName.toLowerCase();
        if (trimmedTab.equals("students")) {
            tab = Tab.STUDENTS;
        } else if (trimmedTab.equals("sessions")) {
            tab = Tab.SESSIONS;
        } else if (trimmedTab.equals("current")) {
            tab = Tab.CURRENT;
        } else {
            throw new CommandException(MESSAGE_INVALID_TAB);
        }
        return new CommandResult(String.format(MESSAGE_SWITCH_TAB_SUCCESS, tab.toString().toLowerCase()),
                false, tab, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SwitchCommand // instanceof handles nulls
                && tabName.equals(((SwitchCommand) other).tabName)); // state check
    }
}
