package atas.model;

import java.util.List;

import atas.model.attendance.Session;
import atas.model.person.Person;
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
     * Returns an unmodifiable view of the person list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getInternalPersonList();

    void updatePersonList(List<Person> list);

}

