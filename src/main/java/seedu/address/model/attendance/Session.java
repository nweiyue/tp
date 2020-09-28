package seedu.address.model.attendance;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;

/**
 * Represents a particular session at a particular date.
 * Each {@code Session} contains a list of details for each student in ATAS for the TA to keep track of.
 */
public class Session implements Comparable<Session> {

    private final LocalDate date;
    private final Map<Integer, Attributes> studentList;

    /**
     * Parametrized constructor.
     */
    public Session(LocalDate date, List<Person> masterList) {
        this.date = date;
        this.studentList = new HashMap<>();
        initializeSession(masterList);
    }

    public Map<Integer, Attributes> getStudentList() {
        return studentList;
    }

    /**
     * Sets the student session presence to <code>true</code>.
     */
    public void studentBecomesPresent(Index studentId) {
        requireNonNull(studentId);
        Attributes attributes = studentList.get(studentId.getZeroBased());
        studentList.put(studentId.getZeroBased(), attributes.becomePresent());
    }

    /**
     * Sets the student participation status to <code>true</code>.
     */
    public void studentParticipates(Index studentId) {
        requireNonNull(studentId);
        Attributes attributes = studentList.get(studentId.getZeroBased());
        studentList.put(studentId.getZeroBased(), attributes.participate());
    }

    /**
     * Updates the session after the deletion of a student (with a given student ID)
     */
    public void updateSessionAfterDeletion(Index studentId, List<Person> masterList) {
        requireAllNonNull(studentId, masterList);
        // shift the values down by 1, starting from deleted student ID
        for (int i = studentId.getZeroBased(); i < masterList.size(); i++) {
            Attributes temp = studentList.get(i + 1);
            studentList.put(i, temp);
        }
        studentList.remove(masterList.size()); // remove last key-value pair from hashmap
    }

    /**
     * Returns true if both sessions have the same date.
     * This defines a weaker notion of equality between two sessions.
     */
    public boolean isSameSession(Session otherSession) {
        if (otherSession == this) {
            return true;
        }

        return otherSession != null && otherSession.date.equals(this.date);
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof Session
                && this.date.equals(((Session) other).date)
                && this.studentList.equals(((Session) other).studentList));
    }

    @Override
    public int compareTo(Session other) {
        return this.date.compareTo(other.date);
    }

    private void initializeSession(List<Person> masterList) {
        for (int i = 0; i < masterList.size(); i++) {
            studentList.put(i, new Attributes());
        }
    }

}
