package atas.ui;

import javafx.beans.property.SimpleStringProperty;

/**
 *  Represents the command summaries to be displayed in the {@code HelpWindow}.
 */
public class CommandSummary {
    private final SimpleStringProperty action = new SimpleStringProperty("");
    private final SimpleStringProperty commandFormat = new SimpleStringProperty("");

    /**
     *  Constructs a CommandSummary of a command.
     *
     * @param action String value to describe the action of the command.
     * @param commandFormat String value to describe the format that should be used for the command.
     *
     * @return CommandSummary
     */
    public CommandSummary(String action, String commandFormat) {
        this.action.set(action);
        this.commandFormat.set(commandFormat);
    }

    /**
     * Returns the action name.
     * @return String value of the action.
     */
    public String getAction() {
        return action.get();
    }

    /**
     * Sets the action name.
     * @param action String value of the action.
     */
    public void setAction(String action) {
        this.action.set(action);
    }

    /**
     * Returns the command format.
     * @return String value of the the command format.
     */
    public String getCommandFormat() {
        return commandFormat.get();
    }

    /**
     * Sets the command format.
     * @param commandFormat String value of the command format.
     */
    public void setCommandFormat(String commandFormat) {
        this.commandFormat.set(commandFormat);
    }
}
