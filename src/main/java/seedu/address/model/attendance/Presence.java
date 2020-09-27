package seedu.address.model.attendance;

/**
 * Represents whether a student is present during a particular class.
 * Guarantees: immutability
 */
public class Presence {

    private final boolean isPresent;

    public Presence(boolean isPresent) {
        this.isPresent = isPresent;
    }

    /**
     * Sets to the default value of false.
     */
    public Presence() {
        this(false);
    }

    public Presence becomePresent() {
        return new Presence(true);
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || (other instanceof Presence
                && (isPresent == ((Presence) other).isPresent));
    }

}
