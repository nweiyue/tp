package atas.ui;

import javafx.beans.property.SimpleStringProperty;

/**
 *  Represents the command summaries to be displayed in the {@code HelpWindow}.
 *
 * //@@author Alla Redko-reused
 * //Reused from https://docs.oracle.com/javafx/2/ui_controls/table-view.htm with minor modifications
 */
public class CommandSummary {

    private final SimpleStringProperty action = new SimpleStringProperty("");
    private final SimpleStringProperty commandFormat = new SimpleStringProperty("");

    /**
     * Constructs a CommandSummary of a command.
     *
     * @param action String to describe the action of the command.
     * @param commandFormat String to describe the format that should be used for the command.
     * @return CommandSummary
     */
    public CommandSummary(String action, String commandFormat) {
        this.action.set(action);
        this.commandFormat.set(commandFormat);
    }

    /**
     * Returns the action name.
     *
     * @return action of the command.
     */
    public String getAction() {
        return action.get();
    }

    /**
     * Sets the action name.
     *
     * @param action action of the command.
     */
    public void setAction(String action) {
        this.action.set(action);
    }

    /**
     * Returns the command format.
     *
     * @return command format of the command.
     */
    public String getCommandFormat() {
        return commandFormat.get();
    }

    /**
     * Sets the command format.
     *
     * @param commandFormat command format of the command.
     */
    public void setCommandFormat(String commandFormat) {
        this.commandFormat.set(commandFormat);
    }
    //@@author
}
