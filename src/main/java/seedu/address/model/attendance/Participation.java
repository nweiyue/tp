package seedu.address.model.attendance;

/**
 * Represents whether or not a student has participated in the particular class.
 * Guarantees: immutability
 */
public class Participation {

    private final boolean hasParticipated;

    public Participation(boolean isPresent) {
        this.hasParticipated = isPresent;
    }

    /**
     * Sets to the default value of false.
     */
    public Participation() {
        this(false);
    }

    public Participation participate() {
        return new Participation(true);
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof Participation
                && (hasParticipated == ((Participation) other).hasParticipated));
    }

}
