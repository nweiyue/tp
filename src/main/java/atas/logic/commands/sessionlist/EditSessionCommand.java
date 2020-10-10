package atas.logic.commands.sessionlist;

import static atas.commons.core.Messages.MESSAGE_SESSION_DOES_NOT_EXIST;
import static atas.commons.util.CollectionUtil.isAnyNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Optional;

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
            + "by the session name in the displayed session list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: SESSION_NAME "
            + "[" + CliSyntax.PREFIX_SESSIONNAME + "SESSION_NAME] "
            + "[" + CliSyntax.PREFIX_SESSIONDATE + "DATE] \n"
            + "Example: " + COMMAND_WORD + " tut1 "
            + CliSyntax.PREFIX_SESSIONNAME + "Tutorial1 "
            + CliSyntax.PREFIX_SESSIONDATE + "10/10/2020";

    public static final String MESSAGE_EDIT_SESSION_SUCCESS = "Edited session: %1$s => %2$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_SESSION = "This session already exists in the session list.";

    private final SessionName sessionNameOfEdit;
    private final EditSessionCommand.EditSessionDescriptor editSessionDescriptor;

    /**
     * Parameterized Constructor.
     * @param sessionNameOfEdit name of the session in the session list to edit
     * @param editSessionDescriptor details to edit the session with
     */
    public EditSessionCommand(SessionName sessionNameOfEdit,
                              EditSessionCommand.EditSessionDescriptor editSessionDescriptor) {
        requireNonNull(sessionNameOfEdit);
        requireNonNull(editSessionDescriptor);

        this.sessionNameOfEdit = sessionNameOfEdit;
        this.editSessionDescriptor = new EditSessionCommand.EditSessionDescriptor(editSessionDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Session sampleSession = new Session(sessionNameOfEdit, SessionDate.getPlaceholderSessionDate());

        if (!model.hasSession(sampleSession)) {
            throw new CommandException(MESSAGE_SESSION_DOES_NOT_EXIST);
        }

        Session sessionToEdit = null;

        for (Session next : model.getSessionList()) {
            if (next.isSameSession(sampleSession)) {
                sessionToEdit = next;
            }
        }

        assert sessionToEdit != null;
        Session editedSession = createEditedSession(sessionToEdit, editSessionDescriptor);

        if (!sessionToEdit.isSameSession(editedSession) && model.hasSession(editedSession)) {
            throw new CommandException(MESSAGE_DUPLICATE_SESSION);
        }

        model.setSession(sessionToEdit, editedSession);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_SESSION_SUCCESS, sessionToEdit, editedSession));
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
        return sessionNameOfEdit.equals(e.sessionNameOfEdit)
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
