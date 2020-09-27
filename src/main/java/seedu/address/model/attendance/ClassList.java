package seedu.address.model.attendance;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import seedu.address.commons.core.index.Index;
import seedu.address.model.attendance.exceptions.DuplicateClassException;
import seedu.address.model.person.Person;

/**
 * Represents a collection of all the classes in the semester.
 */
public class ClassList {

    private final SortedSet<Class> classes = new TreeSet<>();
    private final List<Person> masterList;

    public ClassList(List<Person> masterList) {
        this.masterList = masterList;
    }

    /**
     * Adds a class to the list.
     * The class must not already exist in the list.
     */
    public void addClass(LocalDate date) {
        requireNonNull(date);
        Class toAdd = new Class(date, masterList);
        if (contains(toAdd)) {
            throw new DuplicateClassException();
        }
        classes.add(toAdd);
    }

    /**
     * Returns true if the collection contains an equivalent class as the given argument.
     */
    public boolean contains(Class toCheck) {
        requireNonNull(toCheck);
        return classes.stream().anyMatch(toCheck::isSameClass);
    }

    /**
     * Updates all classes after the deletion of a student (with a given student ID)
     */
    public void updateAllClassesAfterDeletion(Index studentId) {
        requireNonNull(studentId);
        for (Class c : classes) {
            c.updateClassAfterDeletion(studentId, masterList);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClassList // instanceof handles nulls
                && classes.equals(((ClassList) other).classes)
                && masterList.equals(((ClassList) other).masterList));
    }

}
