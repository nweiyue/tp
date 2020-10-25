package atas.model;

import java.util.List;

import atas.model.attendance.Session;
import atas.model.student.Student;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a session list
 */
public interface ReadOnlySessionList {

    /**
     * Returns sessions in the sessionList.
     */
    ObservableList<Session> getSessions();

    /**
     * Returns an unmodifiable view of the student list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getInternalStudentList();

    /**
     * Update the current student list with the given list.
     */
    void updateStudentList(List<Student> list);

}

