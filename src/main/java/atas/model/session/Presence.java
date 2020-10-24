package atas.model.session;

/**
 * Represents whether a student is present during a particular class.
 * Guarantees: immutability
 */
public class Presence {

    public static final String MESSAGE_CONSTRAINTS = "Presence should be either true or false";

    private static final boolean PRESENT = true;
    private static final boolean ABSENT = false;

    private final boolean isPresent;


    public Presence(boolean isPresent) {
        this.isPresent = isPresent;
    }

    /**
     * Sets to the default value of false.
     */
    public Presence() {
        this(ABSENT);
    }

    /**
     * Toggles the status of presence.
     */
    public Presence togglePresence() {
        if (isPresent == PRESENT) {
            return new Presence(ABSENT);
        } else {
            return new Presence(PRESENT);
        }
    }

    /**
     * Returns the presence status of a student.
     */
    public boolean isPresent() {
        return isPresent;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof Presence
                && (isPresent == ((Presence) other).isPresent));
    }

    @Override
    public String toString() {
        return Boolean.toString(isPresent);
    }
}
