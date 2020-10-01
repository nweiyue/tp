package seedu.address.model.attendance;

/**
 * Represents the things to keep track for each student for each class.
 * Guarantees: immutability
 */
public class Attributes {

    private final Presence presence;
    private final Participation participation;

    /**
     * Parameterized constructor.
     */
    public Attributes(Presence presence, Participation participation) {
        this.presence = presence;
        this.participation = participation;
    }

    /**
     * Sets to the default values for each field.
     */
    public Attributes() {
        this(new Presence(), new Participation());
    }

    /**
     * Causes the presence field to be true.
     */
    public Attributes becomePresent() {
        return new Attributes(presence.becomePresent(), participation);
    }

    /**
     * Causes the participation field to be true.
     */
    public Attributes participate() {
        return new Attributes(presence, participation.participate());
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

}
