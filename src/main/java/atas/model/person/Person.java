package atas.model.person;

import static atas.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import atas.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Matriculation matriculation;
    private final Email email;

    // Data fields
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Matriculation matriculation, Email email, Set<Tag> tags) {
        requireAllNonNull(name, matriculation, email, tags);
        this.name = name;
        this.matriculation = matriculation;
        this.email = email;
        this.tags.addAll(tags);
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

    /**
     * Returns true if both persons of the same name have the same matriculation number.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null && otherPerson.getName().equals(getName())
                && otherPerson.getMatriculation().equals(getMatriculation());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getMatriculation().equals(getMatriculation())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getTags().equals(getTags());
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