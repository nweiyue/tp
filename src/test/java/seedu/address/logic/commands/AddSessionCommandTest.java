package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.sessionlistcommands.AddSessionCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.attendance.IndexRange;
import seedu.address.model.attendance.Session;
import seedu.address.model.attendance.SessionList;
import seedu.address.model.attendance.SessionName;
import seedu.address.model.person.Person;
import seedu.address.testutil.SessionBuilder;

public class AddSessionCommandTest {

    @Test
    public void constructor_nullSession_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddSessionCommand(null));
    }

    @Test
    public void execute_sessionAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingSessionAdded modelStub = new ModelStubAcceptingSessionAdded();
        Session validSession = new SessionBuilder().build();

        CommandResult commandResult = new AddSessionCommand(validSession).execute(modelStub);

        assertEquals(String.format(AddSessionCommand.MESSAGE_SUCCESS, validSession), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validSession), modelStub.sessionList);
    }

    @Test
    public void execute_duplicateSession_throwsCommandException() {
        Session validSession = new SessionBuilder().build();
        AddSessionCommand addSessionCommand = new AddSessionCommand(validSession);
        ModelStub modelStub = new ModelStubWithSession(validSession);

        assertThrows(CommandException.class,
                AddSessionCommand.MESSAGE_DUPLICATE_SESSION, () -> addSessionCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Session tut = new SessionBuilder().withSessionName("tut").build();
        Session lab = new SessionBuilder().withSessionName("lab").build();

        AddSessionCommand addTutCommand = new AddSessionCommand(tut);
        AddSessionCommand addLabCommand = new AddSessionCommand(lab);

        // same object -> returns true
        assertTrue(addTutCommand.equals(addTutCommand));

        // same session name -> returns true
        AddSessionCommand addTutCommandCopy = new AddSessionCommand(tut);
        assertTrue(addTutCommand.equals(addTutCommandCopy));

        // different types -> returns false
        assertFalse(addTutCommand.equals(1));

        // null -> returns false
        assertFalse(addTutCommand.equals(null));

        // different person -> returns false
        assertFalse(addTutCommand.equals(addLabCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target, Index id) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getSessionListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSessionListFilePath(Path sessionListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetSessionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public SessionList getSessionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSession(Session session) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSession(Session target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSession(Session session) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSession(Session target, Session editedSession) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateParticipationBySessionName(SessionName sessionName, IndexRange indexRange) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updatePresenceBySessionName(SessionName sessionName, IndexRange indexRange) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single session.
     */
    private class ModelStubWithSession extends ModelStub {
        private final Session session;

        ModelStubWithSession(Session session) {
            requireNonNull(session);
            this.session = session;
        }

        @Override
        public boolean hasSession(Session session) {
            requireNonNull(session);
            return this.session.isSameSession(session);
        }
    }

    /**
     * A Model stub that always accept the session being added.
     */
    private class ModelStubAcceptingSessionAdded extends ModelStub {
        final ArrayList<Session> sessionList = new ArrayList<>();

        @Override
        public boolean hasSession(Session session) {
            requireNonNull(session);
            return sessionList.stream().anyMatch(session::isSameSession);
        }

        @Override
        public void addSession(Session session) {
            requireNonNull(session);
            sessionList.add(session);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
