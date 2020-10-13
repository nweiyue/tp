package atas.model;

import atas.model.student.Student;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an student list
 */
public interface ReadOnlyStudentList {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

}
