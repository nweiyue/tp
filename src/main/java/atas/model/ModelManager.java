package atas.model;

import static atas.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import atas.commons.core.GuiSettings;
import atas.commons.core.LogsCenter;
import atas.commons.core.index.Index;
import atas.model.attendance.Attributes;
import atas.model.attendance.IndexRange;
import atas.model.attendance.Session;
import atas.model.attendance.SessionList;
import atas.model.attendance.SessionName;
import atas.model.student.Student;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final StudentList studentList;
    private final SessionList sessionList;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<Session> filteredSessions;
    private Index sessionId;
    private boolean isCurrentSessionEnabled;

    /**
     * Initializes a ModelManager with the given session list, studentList and userPrefs.
     */
    public ModelManager(ReadOnlySessionList sessionList, ReadOnlyStudentList studentList, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(studentList, userPrefs);

        logger.fine("Initializing with student list: " + studentList + " and user prefs " + userPrefs);

        this.sessionList = new SessionList(sessionList);
        this.studentList = new StudentList(studentList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.studentList.getStudentList());
        filteredSessions = new FilteredList<>(sessionList.getSessions());
        sessionId = Index.fromZeroBased(0);
    }

    public ModelManager() {
        this(new SessionList(), new StudentList(), new UserPrefs());
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
    public Path getStudentListFilePath() {
        return userPrefs.getStudentListFilePath();
    }

    @Override
    public void setStudentListFilePath(Path studentListFilePath) {
        requireNonNull(studentListFilePath);
        userPrefs.setAddressBookFilePath(studentListFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setStudentList(ReadOnlyStudentList studentList) {
        //this.addressBook.resetData(addressBook);
        resetSessionList();
    }

    @Override
    public ReadOnlyStudentList getStudentList() {
        return studentList;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return studentList.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target, Index id) {
        studentList.removeStudent(target);
        sessionList.updateStudentList(studentList.getStudentList());
        sessionList.updateAllSessionsAfterDelete(id);
    }

    @Override
    public void addStudent(Student student) {
        studentList.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
        sessionList.updateStudentList(studentList.getStudentList());
        sessionList.updateAllSessionsAfterAdd();
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        studentList.setStudent(target, editedStudent);
        sessionList.updateStudentList(studentList.getStudentList());
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
        sessionList.updateStudentList(studentList.getStudentList());
        sessionList.deleteSession(target);
    }

    @Override
    public void addSession(Session session) {
        sessionList.updateStudentList(studentList.getStudentList());
        sessionList.addSession(session);
        updateFilteredSessionList(PREDICATE_SHOW_ALL_SESSIONS);
    }

    @Override
    public void setSession(Session target, Session editedSession) {
        sessionList.setSession(target, editedSession);
    }

    @Override
    public void updateParticipationBySessionName(SessionName sessionName, IndexRange indexRange) {
        sessionName = sessionList.getSessionBasedOnId(sessionId).getSessionName();
        sessionList.updateStudentParticipation(sessionName, indexRange);
    }

    @Override
    public void updatePresenceBySessionName(SessionName sessionName, IndexRange indexRange) {
        sessionName = sessionList.getSessionBasedOnId(sessionId).getSessionName();
        sessionList.updateStudentPresence(sessionName, indexRange);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
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
        return studentList.equals(other.studentList)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
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

    @Override
    public void setCurrentSessionFalse() {
        this.isCurrentSessionEnabled = false;
    }

    @Override
    public void setCurrentSessionTrue() {
        this.isCurrentSessionEnabled = true;
    }

    @Override
    public boolean returnCurrentSessionEnabledStatus() {
        return isCurrentSessionEnabled;
    }

}
