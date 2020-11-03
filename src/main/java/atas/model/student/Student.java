package atas.model.student;

import static atas.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import atas.model.statistics.Statistics;
import atas.model.statistics.StudentStatistics;
import atas.model.tag.Tag;

/**
 * Represents a Student in the student list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity field
    private final Name name;

    // Unique student identification fields
    private final Matriculation matriculation;
    private final Email email;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    // Statistic fields
    private final StudentStatistics stats;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Matriculation matriculation, Email email, Set<Tag> tags) {
        requireAllNonNull(name, matriculation, email, tags);
        this.name = name;
        this.matriculation = matriculation;
        this.email = email;
        this.tags.addAll(tags);
        this.stats = new StudentStatistics();
    }

    public Name getName() {
        return name;
    }

    public Matriculation getMatriculation() {
        return matriculation;
    }

    public Email getEmail() {
        return email;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public StudentStatistics getStats() {
        return this.stats;
    }

    public void setStats(Statistics... statistics) {
        this.stats.replaceStatistics(statistics);
    }

    /**
     * Returns true if both students of the same name have the same matriculation number and email.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        requireNonNull(otherStudent);
        if (otherStudent == this) {
            return true;
        }

        return otherStudent.getMatriculation().equals(getMatriculation())
                && otherStudent.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both are not unique.
     */
    public boolean hasSameField(Student otherStudent) {
        requireNonNull(otherStudent);
        if (otherStudent == this) {
            return true;
        }

        // unique students should not have the same matriculation number and email address
        return otherStudent.getMatriculation().equals(getMatriculation())
                || otherStudent.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getMatriculation().equals(getMatriculation())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, matriculation, email, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Matriculation: ")
                .append(getMatriculation())
                .append(" Email: ")
                .append(getEmail())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
