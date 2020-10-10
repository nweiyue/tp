package atas.model.attendance;

import static atas.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import atas.commons.core.index.Index;
import atas.model.person.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Represents a particular session at a particular date.
 * Each {@code Session} contains a list of details for each student in ATAS for the TA to keep track of.
 */
public class Session implements Comparable<Session> {

    private final SessionName sessionName;
    private final SessionDate sessionDate;
    private final Map<Integer, Attributes> studentList;
    //private final ObservableList<Person> masterList;
    private Index sessionIndex;
    private List<Person> masterList = new ArrayList<>();

    /* Bugs with masterList ---> commented out codes on hold */
    /**
     * Parametrized constructor.
     */
    public Session(SessionName sessionName, SessionDate sessionDate) {
        this.sessionName = sessionName;
        this.sessionDate = sessionDate;
        this.studentList = new HashMap<>();
        this.sessionIndex = Index.fromZeroBased(0);
        //this.masterList = FXCollections.emptyObservableList();
    }

    /**
     * Parametrized constructor.
     */
    public Session(SessionName sessionName, SessionDate sessionDate, Map<Integer, Attributes> studentList) {
        this.sessionName = sessionName;
        this.sessionDate = sessionDate;
        this.studentList = studentList;
        this.sessionIndex = Index.fromZeroBased(0);
        //this.masterList = FXCollections.emptyObservableList();
    }

    //    public Session(SessionName sessionName, SessionDate
    //    sessionDate, Map<Integer, Attributes> studentList, ObservableList<Person> masterList) {
    //        this.sessionName = sessionName;
    //        this.sessionDate = sessionDate;
    //        this.studentList = studentList;
    //        //this.masterList = masterList;
    //    }
    public Map<Integer, Attributes> getStudentList() {
        return studentList;
    }

    /**
     * Sets the student session presence to <code>true</code>.
     */
    public void setStudentAsPresent(Index studentId) {
        requireNonNull(studentId);
        Attributes attributes = studentList.get(studentId.getZeroBased());
        studentList.put(studentId.getZeroBased(), attributes.togglePresence());
    }

    /**
     * Sets the student participation status to <code>true</code>.
     */
    public void setStudentAsParticipated(Index studentId) {
        requireNonNull(studentId);
        Attributes attributes = studentList.get(studentId.getZeroBased());
        studentList.put(studentId.getZeroBased(), attributes.toggleParticipation());
    }

    /**
     * Updates the session after the deletion of a student (with a given student ID)
     */
    public void updateSessionAfterDelete(Index studentId, List<Person> masterList) {
        requireAllNonNull(studentId, masterList);
        // shift the values down by 1, starting from deleted student ID
        for (int i = studentId.getZeroBased(); i < masterList.size(); i++) {
            Attributes temp = studentList.get(i + 1);
            //studentList.remove(i + 1);
            studentList.put(i, temp);
        }
        studentList.remove(masterList.size()); // remove last key-value pair from hashmap
    }

    /**
     * Updates the session after adding a student
     */
    public void updateSessionAfterAdd(List<Person> masterList) {
        requireAllNonNull(masterList);
        studentList.put(masterList.size() - 1, new Attributes());
    }

    /**
     * Updates the participation given a range of index.
     */
    public void updateParticipation(IndexRange indexRange) {
        requireNonNull(indexRange);

        // find students that have index in range
        for (int i = indexRange.getZeroBasedLower(); i <= indexRange.getZeroBasedUpper(); i++) {
            Attributes temp = studentList.get(i);
            // exclude invalid index
            if (temp != null) {
                setStudentAsParticipated(Index.fromZeroBased(i));
            }
        }
    }

    /**
     * Updates the presence given a range of index.
     */
    public void updatePresence(IndexRange indexRange) {
        requireNonNull(indexRange);

        // find students that have index in range
        for (int i = indexRange.getZeroBasedLower(); i <= indexRange.getZeroBasedUpper(); i++) {
            Attributes temp = studentList.get(i);

            // exclude invalid index
            if (temp != null) {
                setStudentAsPresent(Index.fromZeroBased(i));
            }
        }
    }

    public ObservableList<Attributes> getAttributesAsList() {
        ObservableList<Attributes> newAttributesList = FXCollections.observableArrayList();
        newAttributesList.addAll(studentList.values());
        return newAttributesList;
    }

    /**
     * Initialize the studentList using the given masterList.
     */
    public void initializeSession(List<Person> masterList) {
        for (int i = 0; i < masterList.size(); i++) {
            this.masterList.add(masterList.get(i));
            studentList.put(i, new Attributes(masterList.get(i).getName()));
        }
    }

    public String returnStudentNameStringByIndex(int index) throws Exception {
        return studentList.get(index).getName();
    }

    /**
     * Returns true if both sessions have the same session name.
     * This defines a weaker notion of equality between two sessions.
     */
    public boolean isSameSession(Session otherSession) {
        if (otherSession == this) {
            return true;
        }

        return otherSession != null
                && otherSession.sessionName.equals(this.sessionName);
    }

    public SessionName getSessionName() {
        return sessionName;
    }

    public SessionDate getSessionDate() {
        return sessionDate;
    }

    public Index getSessionIndex() {
        return sessionIndex;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof Session && this.sessionDate.equals(((Session) other).sessionDate)
                && this.sessionName.equals(((Session) other).sessionName));
    }

    @Override
    public int compareTo(Session other) {
        return this.sessionDate.compareTo(other.sessionDate);
    }

    @Override
    public String toString() {
        return sessionName.toString() + " @ " + sessionDate.toString();
    }
}
