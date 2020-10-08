package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.attendance.Session;
import seedu.address.model.person.Person;

import java.util.List;

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

