package atas.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import atas.ui.Tab;
/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should switch tab. */
    private final Tab switchTab;

    /** The user is editing the memo. */
    private final boolean editMemo;

    /** The application should exit. */
    private final boolean exit;

    /** The user is entering a session. */
    private final boolean isEnterSession;

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, null, false, false, false);
    }

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser,
                         boolean showHelp, Tab switchTab, boolean editMemo, boolean exit, boolean isEnterSession) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.switchTab = switchTab;
        this.editMemo = editMemo;
        this.exit = exit;
        this.isEnterSession = isEnterSession;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public Tab getTab() {
        return switchTab;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isSwitchTab() {
        return switchTab != null;
    }

    public boolean isEditMemo() {
        return editMemo;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isEnterSession() {
        return isEnterSession;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && switchTab == otherCommandResult.switchTab
                && editMemo == otherCommandResult.editMemo
                && exit == otherCommandResult.exit
                && isEnterSession == otherCommandResult.isEnterSession;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, switchTab, editMemo, exit, isEnterSession);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("feedbackToUser: " + feedbackToUser + ", ");
        sb.append("showHelp: " + showHelp + ", ");
        sb.append("switchTab: " + switchTab + ", ");
        sb.append("exit: " + exit + ", ");
        sb.append("isEnterSession: " + isEnterSession);
        return sb.toString();
    }

}
