package atas.model.student;

import static atas.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

/**
 * Represents a Student's matriculation number.
 * Guarantees: immutable; is valid as declared in {@link #isValidMatriculation(String)}
 */
public class Matriculation {


    public static final String MESSAGE_CONSTRAINTS =
            "Matriculation Number should be in the form of A1234567X, starting with 'A', followed by "
            + "7 digits, ends with another letter.";
    /* For better user experience, ATAS allows user input to have small cases for first and last letter. */
    public static final String VALIDATION_REGEX = "[a,A][0-9]{7}[a-z,A-Z]";
    public final String value;

    /**
     * Constructs a {@code Matriculation}.
     *
     * @param matriculation A valid Matriculation number.
     */
    public Matriculation(String matriculation) {
        requireNonNull(matriculation);
        checkArgument(isValidMatriculation(matriculation), MESSAGE_CONSTRAINTS);
        // Capitalizes the small cases of user input.
        value = matriculation.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid Matriculation number.
     */
    public static boolean isValidMatriculation(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Matriculation // instanceof handles nulls
                && value.equals(((Matriculation) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
