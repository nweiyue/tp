package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.attendance.IndexRange;
import seedu.address.model.attendance.Session;
import seedu.address.model.attendance.SessionList;
import seedu.address.model.attendance.SessionName;
import seedu.address.model.person.Person;

import java.nio.file.Path;
import java.util.function.Predicate;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    Predicate<Person> PREDICATE_SHOW_ALL_SESSIONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Returns the user prefs' session list file path.
     */
    Path getSessionListFilePath();

    /**
     * Sets the user prefs' session list file path.
     */
    void setSessionListFilePath(Path sessionListFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target, Index id);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);


    /**
     * CLear the sessions existing in the current session list.
     * The link between the students and the session list will not be disrupted.
     */
    void resetSessionList();

    /** Returns the SessionList */
    SessionList getSessionList();

    /**
     * Returns true if a session with the same identity as {@code session} exists in the session list.
     */
    boolean hasSession(Session session);

    /**
     * Deletes the given session.
     * The session must exist in the session list.
     */
    void deleteSession(Session target);

    /**
     * Adds the given session.
     * {@code session} must not already exist in the session list.
     */
    void addSession(Session session);

    /**
     * Replaces the given session {@code target} with {@code editedSession}.
     * {@code target} must exist in the session list.
     * The session identity of {@code editedSession} must not be the same as another
     * existing session in the session list.
     */
    void setSession(Session target, Session editedSession);

    /**
     * Updates participation status of the given session.
     */
    void updateParticipationBySessionName(SessionName sessionName, IndexRange indexRange);

    /**
     * Updates presence status of the given session.
     */
    void updatePresenceBySessionName(SessionName sessionName, IndexRange indexRange);


    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered session list */
    ObservableList<Session> getFilteredSessionList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered session list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredSessionList(Predicate<Session> predicate);
}
