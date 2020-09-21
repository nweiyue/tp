package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MatriculationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Matriculation(null));
    }

    @Test
    public void constructor_invalidMatriculation_throwsIllegalArgumentException() {
        String invalidMatriculation = "";
        assertThrows(IllegalArgumentException.class, () -> new Matriculation(invalidMatriculation));
    }

    @Test
    public void isValidMatriculation() {
        // null Matriculation number
        assertThrows(NullPointerException.class, () -> Matriculation.isValidMatriculation(null));

        // invalid Matriculation numbers
        assertFalse(Matriculation.isValidMatriculation("")); // empty string
        assertFalse(Matriculation.isValidMatriculation(" ")); // spaces only
        assertFalse(Matriculation.isValidMatriculation("1234567")); // only numbers
        assertFalse(Matriculation.isValidMatriculation("Matriculation")); // non-numeric
        assertFalse(Matriculation.isValidMatriculation("1234567X")); // missing prefix
        assertFalse(Matriculation.isValidMatriculation("A123456X")); // missing number
        assertFalse(Matriculation.isValidMatriculation("A1234567")); // missing suffix

        // valid Matriculation numbers
        assertTrue(Matriculation.isValidMatriculation("A1234567X")); // standard
        assertTrue(Matriculation.isValidMatriculation("A2534564F")); // random
        assertTrue(Matriculation.isValidMatriculation("A0000000X")); // full of zeros
    }
}
