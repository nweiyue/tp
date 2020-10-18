package atas.model;

import java.util.List;

import atas.model.session.Session;
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

    void updateStudentList(List<Student> list);

}

