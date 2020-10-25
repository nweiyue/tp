package atas.model.session;

import static atas.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Session's Name in a class.
 * Guarantees: immutable; is valid as declared in {@link #isValidSessionName(String)}
 */
public class SessionName {

    public static final String MESSAGE_CONSTRAINTS = "SessionNames should only consists of numbers, letters, dashes, "
            + "underscores and whitespaces. The total number of characters should be at least 1 to form a valid "
            + "session name\n"
            + "The session name should also start with either a number or a letter and should not end with "
            + "a whitespace.";
    public static final String VALIDATION_REGEX = "([a-zA-z0-9_-]+)((?: [a-zA-z0-9_-]+)*)";
    public final String value;

    /**
     * Constructs an {@code SessionName}.
     *
     * @param sessionName A valid session name.
     */
    public SessionName(String sessionName) {
        requireNonNull(sessionName);
        checkArgument(isValidSessionName(sessionName), MESSAGE_CONSTRAINTS);
        value = sessionName;
    }

    public SessionName getCopy() {
        return new SessionName(this.value);
    }

    /**
     * Returns if a given string is a valid session name.
     */
    public static boolean isValidSessionName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SessionName // instanceof handles nulls
                && value.equals(((SessionName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
