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
 * Represents a particular class at a particular date.
 * Each Class contains a list of details for each student in ATAS for the TA to keep track of.
 */
public class Class implements Comparable<Class> {

    private final LocalDate date;
    private final Map<Integer, Attributes> studentList;

    /**
     * Parametrized constructor.
     */
    public Class(LocalDate date, List<Person> masterList) {
        this.date = date;
        this.studentList = new HashMap<>();
        initializeClass(masterList);
    }

    public Map<Integer, Attributes> getStudentList() {
        return studentList;
    }

    /**
     * Sets the student class presence to <code>true</code>.
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
     * Updates the class after the deletion of a student (with a given student ID)
     */
    public void updateClassAfterDeletion(Index studentId, List<Person> masterList) {
        requireAllNonNull(studentId, masterList);
        // shift the values down by 1, starting from deleted student ID
        for (int i = studentId.getZeroBased(); i < masterList.size(); i++) {
            Attributes temp = studentList.get(i + 1);
            studentList.put(i, temp);
        }
        studentList.remove(masterList.size()); // remove last key-value pair from hashmap
    }

    /**
     * Returns true if both classes have the same date.
     * This defines a weaker notion of equality between two classes.
     */
    public boolean isSameClass(Class otherClass) {
        if (otherClass == this) {
            return true;
        }

        return otherClass != null && otherClass.date.equals(this.date);
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof Class
                && this.date.equals(((Class) other).date)
                && this.studentList.equals(((Class) other).studentList));
    }

    @Override
    public int compareTo(Class other) {
        return this.date.compareTo(other.date);
    }

    private void initializeClass(List<Person> masterList) {
        for (int i = 0; i < masterList.size(); i++) {
            studentList.put(i, new Attributes());
        }
    }

}
