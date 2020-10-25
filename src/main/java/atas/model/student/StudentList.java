package atas.model.student;

import static java.util.Objects.requireNonNull;

import java.util.List;

import atas.commons.core.index.Index;
import atas.model.session.SessionList;
import atas.model.statistics.ParticipationStatistics;
import atas.model.statistics.PresenceStatistics;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the student-list level
 * Duplicates are not allowed(same name and matriculation number) (by .isSameStudent comparison)
 */
public class StudentList implements ReadOnlyStudentList {

    private final UniqueStudentList students;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
    }

    public StudentList() {}

    /**
     * Creates a StudentList using the students in the {@code toBeCopied}
     */
    public StudentList(ReadOnlyStudentList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Resets the existing data of this {@code StudentList} with {@code newData}.
     */
    public void resetData(ReadOnlyStudentList newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the student list
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Adds a student to the student list.
     * The student must not already exist in the student list.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the student list.
     * The identity of {@code editedStudent} must not be the same as another existing student in the student list.
     */
    public void setStudent(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code StudentList}.
     * {@code key} must exist in the student list.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    //// util methods

    /**
     * Recalculate student statistics using the given session list.
     */
    public void refreshStudentListStatistics(SessionList sessionList) {
        int counter = 0;
        for (Student student: students) {
            Index index = Index.fromZeroBased(counter);
            student.setStats(new ParticipationStatistics().getStudentStatistics(sessionList, index),
                    new PresenceStatistics().getStudentStatistics(sessionList, index));
            counter++;
        }
    }

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    public ObservableList<Student> getModifiableStudentList() {
        return students.asModifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StudentList // instanceof handles nulls
                && students.equals(((StudentList) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
