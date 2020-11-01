package atas.model.student;

import static atas.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static atas.logic.commands.CommandTestUtil.VALID_MATRICULATION_BOB;
import static atas.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static atas.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static atas.testutil.Assert.assertThrows;
import static atas.testutil.TypicalStudents.ALICE;
import static atas.testutil.TypicalStudents.BOB;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new StudentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> student.getTags().remove(0));
    }

    @Test
    public void isSameStudent() {
        // same object -> returns true
        assertTrue(ALICE.isSameStudent(ALICE));

        // null -> throws exception
        assertThrows(NullPointerException.class, () -> ALICE.isSameStudent(null));

        // different matriculation and email -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withMatriculation(VALID_MATRICULATION_BOB)
                .withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // same name, same matriculation, same email -> returns true
        editedAlice = new StudentBuilder(ALICE).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // same name, same matriculation, same email, different tags -> returns true
        editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // different name, same matriculation, same email -> returns true
        editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // same name, different matriculation, same email -> returns false
        editedAlice = new StudentBuilder(ALICE).withMatriculation(VALID_MATRICULATION_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // same name, same matriculation, different email,  -> return false;
        editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));
    }

    @Test
    public void hasSameField() {
        // same object -> have same fields -> returns true
        assertTrue(ALICE.hasSameField(ALICE));

        // null -> throws exception
        assertThrows(NullPointerException.class, () -> ALICE.hasSameField(null));

        // same student -> same matriculation, same email -> returns true
        Student editedAlice = new StudentBuilder(ALICE).build();
        assertTrue(ALICE.hasSameField(editedAlice));

        // same matriculation, different email -> returns true
        editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(ALICE.hasSameField(editedAlice));

        // different matriculation, same email -> returns true
        editedAlice = new StudentBuilder(ALICE).withMatriculation(VALID_MATRICULATION_BOB).build();
        assertTrue(ALICE.hasSameField(editedAlice));

        // different matriculation, different email -> returns false
        editedAlice = new StudentBuilder(ALICE).withMatriculation(VALID_MATRICULATION_BOB)
                .withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.hasSameField(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new StudentBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different student -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different matriculation -> returns false
        editedAlice = new StudentBuilder(ALICE).withMatriculation(VALID_MATRICULATION_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
