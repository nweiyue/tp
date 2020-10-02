package seedu.address.model.attendance;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import seedu.address.commons.core.index.Index;
import seedu.address.model.attendance.exceptions.DuplicateSessionException;
import seedu.address.model.person.Person;

/**
 * Represents a collection of all the sessions in the semester.
 */
public class SessionList {

    private final SortedSet<Session> sessions = new TreeSet<>();
    private final List<Person> masterList;

    public SessionList(List<Person> masterList) {
        this.masterList = masterList;
    }

    /**
     * Adds a session to the list.
     * The session must not already exist in the list.
     */
    public void addSession(LocalDate date) {
        requireNonNull(date);
        Session toAdd = new Session(date, masterList);
        if (contains(toAdd)) {
            throw new DuplicateSessionException();
        }
        sessions.add(toAdd);
    }

    /**
     * Returns true if the collection contains an equivalent session as the given argument.
     */
    public boolean contains(Session toCheck) {
        requireNonNull(toCheck);
        return sessions.stream().anyMatch(toCheck::isSameSession);
    }

    /**
     * Updates all sessions after the deletion of a student (with a given student ID)
     */
    public void updateAllSessionsAfterDeletion(Index studentId) {
        requireNonNull(studentId);
        for (Session s : sessions) {
            s.updateSessionAfterDeletion(studentId, masterList);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SessionList // instanceof handles nulls
                && sessions.equals(((SessionList) other).sessions)
                && masterList.equals(((SessionList) other).masterList));
    }

}
