package seedu.address.model;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.attendance.*;
import seedu.address.model.person.Person;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final SessionList sessionList;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Session> filteredSessions;
    private Index sessionId;

    /**
     * Initializes a ModelManager with the given session list, addressBook and userPrefs.
     */
    public ModelManager(ReadOnlySessionList sessionList, ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.sessionList = new SessionList(sessionList);
        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredSessions = new FilteredList<>(sessionList.getSessions());
        sessionId = Index.fromZeroBased(0);
    }

    public ModelManager() {
        this(new SessionList(), new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
        resetSessionList();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target, Index id) {
        addressBook.removePerson(target);
        sessionList.updatePersonList(addressBook.getPersonList());
        sessionList.updateAllSessionsAfterDelete(id);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        sessionList.updatePersonList(addressBook.getPersonList());
        sessionList.updateAllSessionsAfterAdd();
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
        sessionList.updatePersonList(addressBook.getPersonList());
    }

    //=========== SessionList ================================================================================


    @Override
    public void resetSessionList() {
        this.sessionList.clearSessions();
    }

    @Override
    public SessionList getSessionList() {
        return this.sessionList;
    }

    @Override
    public Path getSessionListFilePath() {
        return userPrefs.getSessionListFilePath();
    }

    @Override
    public void setSessionListFilePath(Path sessionListFilePath) {
        requireNonNull(sessionListFilePath);
        userPrefs.setSessionListFilePath(sessionListFilePath);
    }

    @Override
    public boolean hasSession(Session session) {
        requireNonNull(session);
        return sessionList.contains(session);
    }

    @Override
    public void deleteSession(Session target) {
        sessionList.updatePersonList(addressBook.getPersonList());
        sessionList.deleteSession(target);
    }

    @Override
    public void addSession(Session session) {
        sessionList.updatePersonList(addressBook.getPersonList());
        sessionList.addSession(session);
        updateFilteredSessionList(PREDICATE_SHOW_ALL_SESSIONS);
    }

    @Override
    public void setSession(Session target, Session editedSession) {
        sessionList.setSession(target, editedSession);
    }

    @Override
    public void updateParticipationBySessionName(SessionName sessionName, IndexRange indexRange) {
        sessionList.updateStudentParticipation(sessionName, indexRange);
    }

    @Override
    public void updatePresenceBySessionName(SessionName sessionName, IndexRange indexRange) {
        sessionList.updateStudentPresence(sessionName, indexRange);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }


    //=========== Filtered Session List Accessors =============================================================

    @Override
    public ObservableList<Session> getFilteredSessionList() {
        return filteredSessions;
    }

    @Override
    public void updateFilteredSessionList(Predicate<Session> predicate) {
        requireNonNull(predicate);
        filteredSessions.setPredicate(predicate);
    }

    //=========== Filtered Session Accessors =============================================================
    @Override
    public ObservableList<Attributes> getFilteredAttributesList() {
        return sessionList.getSessionBasedOnId(sessionId).getAttributesAsList();
    }

    @Override
    public void enterSession(Index sessionId) {
        this.sessionId = sessionId;
    }

}
