package atas.model.session;

/**
 * Represents whether or not a student has participated in the particular class.
 * Guarantees: immutability
 */
public class Participation {

    public static final String MESSAGE_CONSTRAINTS = "Participation should be either true or false";

    private static final boolean PARTICIPATED = true;
    private static final boolean NOT_PARTICIPATED = false;

    private final boolean hasParticipated;

    public Participation(boolean hasParticipated) {
        this.hasParticipated = hasParticipated;
    }

    /**
     * Sets to the default value of false.
     */
    public Participation() {
        this(NOT_PARTICIPATED);
    }

    /**
     * Toggles the status of participation.
     */
    public Participation toggleParticipation() {
        if (hasParticipated == PARTICIPATED) {
            return new Participation(NOT_PARTICIPATED);
        } else {
            return new Participation(PARTICIPATED);
        }
    }

    /**
     * Returns the participation status of the student.
     */
    public boolean hasParticipated() {
        return hasParticipated;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof Participation
                && (hasParticipated == ((Participation) other).hasParticipated));
    }

    @Override
    public String toString() {
        return Boolean.toString(hasParticipated);
    }

}
