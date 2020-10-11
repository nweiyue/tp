package atas.logic.commands.sessionlist;

import static atas.commons.core.Messages.MESSAGE_INVALID_SESSION_DISPLAYED_INDEX;
import static atas.commons.util.CollectionUtil.isAnyNonNull;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import atas.commons.core.index.Index;
import atas.logic.commands.Command;
import atas.logic.commands.CommandResult;
import atas.logic.commands.exceptions.CommandException;
import atas.logic.parser.CliSyntax;
import atas.model.Model;
import atas.model.attendance.Session;
import atas.model.attendance.SessionDate;
import atas.model.attendance.SessionName;

public class EditSessionCommand extends Command {

    public static final String COMMAND_WORD = "editses";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the session identified "
            + "by the index number used in the displayed session list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + CliSyntax.PREFIX_SESSION_NAME + "SESSION_NAME] "
            + "[" + CliSyntax.PREFIX_SESSION_DATE + "DATE] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + CliSyntax.PREFIX_SESSION_NAME + "Tutorial 1 "
            + CliSyntax.PREFIX_SESSION_DATE + "10/10/2020";

    public static final String MESSAGE_EDIT_SESSION_SUCCESS = "Edited session: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_SESSION = "This session already exists in the session list.";

    private final Index index;
    private final EditSessionCommand.EditSessionDescriptor editSessionDescriptor;

    /**
     * Parameterized Constructor.
     * @param index The Index of the session in the filtered session list to edit.
     * @param editSessionDescriptor details to edit the session with
     */
    public EditSessionCommand(Index index,
                              EditSessionCommand.EditSessionDescriptor editSessionDescriptor) {
        requireNonNull(index);
        requireNonNull(editSessionDescriptor);

        this.index = index;
        this.editSessionDescriptor = new EditSessionCommand.EditSessionDescriptor(editSessionDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Session> lastShownList = model.getFilteredSessionList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_SESSION_DISPLAYED_INDEX);
        }

        Session sessionToEdit = lastShownList.get(index.getZeroBased());
        Session editedSession = createEditedSession(sessionToEdit, editSessionDescriptor);

        if (!sessionToEdit.isSameSession(editedSession) && model.hasSession(editedSession)) {
            throw new CommandException(MESSAGE_DUPLICATE_SESSION);
        }

        model.setSession(sessionToEdit, editedSession);
        model.updateFilteredSessionList(Model.PREDICATE_SHOW_ALL_SESSIONS);
        return new CommandResult(String.format(MESSAGE_EDIT_SESSION_SUCCESS, editedSession));
    }

    @Override
    public String toString() {
        String oneBasedIndex = String.valueOf(index.getOneBased());
        return "Edit " + oneBasedIndex;
    }

    /**
     * Creates and returns a {@code Session} with the details of {@code sessionToEdit}
     * edited with {@code editSessionDescriptor}.
     */
    private static Session createEditedSession(Session sessionToEdit,
                                              EditSessionCommand.EditSessionDescriptor editSessionDescriptor) {
        assert sessionToEdit != null;

        SessionName updatedSessionName = editSessionDescriptor.getSessionName().orElse(sessionToEdit.getSessionName());
        SessionDate updatedSessionDate = editSessionDescriptor.getSessionDate().orElse(sessionToEdit.getSessionDate());

        Session editedSession = new Session(updatedSessionName, updatedSessionDate);
        editedSession.getStudentList().putAll(sessionToEdit.getStudentList());
        return editedSession;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditSessionCommand)) {
            return false;
        }

        // state check
        EditSessionCommand e = (EditSessionCommand) other;
        return index.equals(e.index)
                && editSessionDescriptor.equals(e.editSessionDescriptor);
    }

    /**
     * Stores the details to edit the session with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditSessionDescriptor {
        private SessionName sessionName;
        private SessionDate sessionDate;

        public EditSessionDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditSessionDescriptor(EditSessionCommand.EditSessionDescriptor toCopy) {
            setSessionName(toCopy.sessionName);
            setSessionDate(toCopy.sessionDate);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return isAnyNonNull(sessionName, sessionDate);
        }

        public Optional<SessionName> getSessionName() {
            return Optional.ofNullable(sessionName);
        }

        public void setSessionName(SessionName sessionName) {
            this.sessionName = sessionName;
        }

        public Optional<SessionDate> getSessionDate() {
            return Optional.ofNullable(sessionDate);
        }

        public void setSessionDate(SessionDate sessionDate) {
            this.sessionDate = sessionDate;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditSessionCommand.EditSessionDescriptor)) {
                return false;
            }

            // state check
            EditSessionCommand.EditSessionDescriptor e = (EditSessionCommand.EditSessionDescriptor) other;

            return getSessionName().equals(e.getSessionName())
                    && getSessionDate().equals(e.getSessionDate());
        }
    }
}
