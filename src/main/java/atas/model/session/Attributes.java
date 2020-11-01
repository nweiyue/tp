package atas.model.session;

import atas.model.student.Name;

/**
 * Represents the things to keep track for each student for each class.
 * Guarantees: immutability
 */
public class Attributes {

    private static final String DEFAULT_NAME = "DEFAULT";
    private final Presence presence;
    private final Participation participation;
    private final Name studentName;

    /**
     * Parameterized constructor.
     */
    public Attributes(Presence presence, Participation participation, Name studentName) {
        this.presence = presence;
        this.participation = participation;
        this.studentName = studentName;
    }

    /**
     * Sets to the default values for each field.
     */
    public Attributes() {
        this(new Presence(), new Participation(), new Name(DEFAULT_NAME));
    }

    /**
     * Sets to the default values for each field but instantiate with name
     */
    public Attributes(Name name) {
        this(new Presence(), new Participation(), name);
    }

    public Attributes getCopy() {
        return new Attributes(presence, participation, studentName);
    }

    /**
     * Causes the presence field to be true.
     */
    public Attributes togglePresence() {
        return new Attributes(presence.togglePresence(), participation, studentName);
    }

    /**
     * Causes the participation field to be true.
     */
    public Attributes toggleParticipation() {
        return new Attributes(presence, participation.toggleParticipation(), studentName);
    }

    /**
     * Returns a new attributes with the new name.
     */
    public Attributes setName(Name name) {
        return new Attributes(presence, participation, name);
    }

    public boolean getPresenceStatus() {
        return presence.isPresent();
    }

    public boolean getParticipationStatus() {
        return participation.hasParticipated();
    }

    public String getName() {
        return studentName.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Attributes) {
            return this.presence.equals(((Attributes) other).presence)
                    && this.participation.equals(((Attributes) other).participation);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Presence: " + presence.isPresent() + " Participation: " + participation.hasParticipated();
    }
}
