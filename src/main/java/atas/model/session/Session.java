package atas.model.session;

import static atas.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import atas.commons.core.index.Index;
import atas.model.statistics.ParticipationStatistics;
import atas.model.statistics.PresenceStatistics;
import atas.model.statistics.SessionStatistics;
import atas.model.student.Name;
import atas.model.student.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a particular session at a particular date.
 * Each {@code Session} contains a list of details for each student in ATAS for the TA to keep track of.
 */
public class Session implements Comparable<Session> {

    private final SessionName sessionName;
    private final SessionDate sessionDate;
    private final ObservableList<Attributes> attributeList;
    private Index sessionIndex;
    private SessionStatistics sessionStats;

    /**
     * Parametrized constructor.
     */
    public Session(SessionName sessionName, SessionDate sessionDate) {
        this.sessionName = sessionName;
        this.sessionDate = sessionDate;
        this.attributeList = FXCollections.observableArrayList();
        this.sessionIndex = Index.fromZeroBased(0);
        this.sessionStats = new SessionStatistics();
    }

    /**
     * Parametrized constructor.
     */
    public Session(SessionName sessionName, SessionDate sessionDate, List<Attributes> attributeList) {
        this.sessionName = sessionName;
        this.sessionDate = sessionDate;
        this.attributeList = FXCollections.observableArrayList();
        this.attributeList.addAll(attributeList);
        this.sessionIndex = Index.fromZeroBased(0);
        this.sessionStats = new SessionStatistics(attributeList.size());
    }

    public ObservableList<Attributes> getAttributeList() {
        return this.attributeList;
    }

    public Session getCopy() {
        List<Attributes> attributesListCopy = new ArrayList<>();
        for (Attributes a : attributeList) {
            attributesListCopy.add(a.getCopy());
        }
        return new Session(sessionName.getCopy(), sessionDate.getCopy(), attributesListCopy);
    }
    /**
     * Toggles student's presence status. Finds the correct corresponding attribute
     * using the given index.
     */
    public void toggleStudentPresence(Index studentId) {
        requireNonNull(studentId);
        Attributes attributes = attributeList.get(studentId.getZeroBased());
        attributeList.set(studentId.getZeroBased(), attributes.togglePresence());
    }

    /**
     * Toggles student's participation status. Finds the correct corresponding attribute
     * using the given index.
     */
    public void toggleStudentParticipation(Index studentId) {
        requireNonNull(studentId);
        Attributes attributes = attributeList.get(studentId.getZeroBased());
        attributeList.set(studentId.getZeroBased(), attributes.toggleParticipation());
    }

    /**
     * Updates the session after the deletion of a student (with a given student ID)
     */
    public void updateSessionAfterDelete(Index studentId, List<Student> masterList) {
        requireAllNonNull(studentId, masterList);
        // shift the values down by 1, starting from deleted student ID
        for (int i = studentId.getZeroBased(); i < masterList.size(); i++) {
            Attributes temp = attributeList.get(i + 1);
            attributeList.set(i, temp);
        }
        attributeList.remove(masterList.size()); // remove last key-value pair from hashmap
        refreshSessionStatistics();
    }

    /**
     * Updates the session after adding a student
     */
    public void updateSessionAfterAdd(List<Student> masterList) {
        requireAllNonNull(masterList);
        Name studentName = masterList.get(masterList.size() - 1).getName();
        attributeList.add(masterList.size() - 1, new Attributes(studentName));
        refreshSessionStatistics();
    }

    /**
     * Updates the participation given a range of index.
     */
    public void updateParticipation(IndexRange indexRange) {
        requireNonNull(indexRange);

        // find students that have index in range
        for (int i = indexRange.getZeroBasedLower(); i <= indexRange.getZeroBasedUpper(); i++) {
            Attributes temp = attributeList.get(i);
            // exclude invalid index
            if (temp != null) {
                toggleStudentParticipation(Index.fromZeroBased(i));
            }
        }
        refreshSessionStatistics();
    }

    /**
     * Updates the presence given a range of index.
     */
    public void updatePresence(IndexRange indexRange) {
        requireNonNull(indexRange);

        // find students that have index in range
        for (int i = indexRange.getZeroBasedLower(); i <= indexRange.getZeroBasedUpper(); i++) {
            Attributes temp = attributeList.get(i);

            // exclude invalid index
            if (temp != null) {
                toggleStudentPresence(Index.fromZeroBased(i));
            }
        }
        refreshSessionStatistics();
    }

    /**
     * Recalculates the participation and presence statistics of this session.
     */
    public void refreshSessionStatistics() {
        sessionStats.replaceStatistics(
                new ParticipationStatistics().getSessionStatistics(this),
                new PresenceStatistics().getSessionStatistics(this));
    }

    /**
     * Initializes the studentList using the given masterList.
     */
    public void initializeSession(List<Student> masterList) {
        for (int i = 0; i < masterList.size(); i++) {
            attributeList.add(new Attributes(masterList.get(i).getName()));
        }

    }

    /**
     * Updates the student name after editing a student's name.
     */
    public void updateAttributeName(Name name, int index) {
        Attributes original = attributeList.get(index);
        attributeList.set(index, original.setName(name));
    }

    public String returnStudentNameStringByIndex(int index) throws Exception {
        return attributeList.get(index).getName();
    }

    public void resetAttributeList() {
        setAttributeList(new ArrayList<>());
    }

    private void setAttributeList(List<Attributes> attributeList) {
        this.attributeList.setAll(attributeList);
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

    public SessionStatistics getSessionStats() {
        return sessionStats;
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
