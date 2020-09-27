package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAttributes.ABSENT_BUT_HAS_PARTICIPATED;
import static seedu.address.testutil.TypicalAttributes.PRESENT_AND_HAS_PARTICIPATED;
import static seedu.address.testutil.TypicalAttributes.PRESENT_BUT_HAS_NOT_PARTICIPATED;
import static seedu.address.testutil.TypicalDates.WEEK_ONE;
import static seedu.address.testutil.TypicalDates.WEEK_THREE;
import static seedu.address.testutil.TypicalDates.WEEK_TWO;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsMinusAlice;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

class ClassTest {

    private final List<Person> emptyMasterList = new AddressBook().getModifiablePersonList();
    private final Class emptyClassWeekOne = new Class(WEEK_ONE, emptyMasterList);
    private final Class emptyClassWeekTwo = new Class(WEEK_TWO, emptyMasterList);
    private final Class emptyClassWeekThree = new Class(WEEK_THREE, emptyMasterList);

    private final List<Person> typicalMasterList = getTypicalAddressBook().getModifiablePersonList();
    private final Class typicalClassWeekOne = new Class(WEEK_ONE, typicalMasterList);
    private final Class typicalClassWeekTwo = new Class(WEEK_TWO, typicalMasterList);
    private final Class typicalClassWeekThree = new Class(WEEK_THREE, typicalMasterList);

    @Test
    public void isSameClass() {
        // same object -> returns true
        assertTrue(emptyClassWeekOne.isSameClass(emptyClassWeekOne));
        assertTrue(typicalClassWeekOne.isSameClass(typicalClassWeekOne));

        // same date but different master list -> returns true
        assertTrue(emptyClassWeekOne.isSameClass(typicalClassWeekOne));

        // null -> returns false
        assertFalse(emptyClassWeekOne.isSameClass(null));
        assertFalse(typicalClassWeekOne.isSameClass(null));

        // different date -> returns false
        assertFalse(emptyClassWeekOne.isSameClass(emptyClassWeekTwo));
        assertFalse(emptyClassWeekTwo.isSameClass(emptyClassWeekThree));
        assertFalse(typicalClassWeekOne.isSameClass(typicalClassWeekTwo));
        assertFalse(typicalClassWeekTwo.isSameClass(typicalClassWeekThree));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(emptyClassWeekOne.equals(emptyClassWeekOne));
        assertTrue(typicalClassWeekOne.equals(typicalClassWeekOne));

        // null -> returns false
        assertFalse(emptyClassWeekOne.equals(null));

        // same date but different master list -> returns false
        assertFalse(emptyClassWeekOne.equals(typicalClassWeekOne));

        // same master list but different date -> returns false
        assertFalse(emptyClassWeekOne.equals(emptyClassWeekTwo));
        assertFalse(emptyClassWeekTwo.equals(emptyClassWeekThree));

        // different date and master list -> returns false
        assertFalse(emptyClassWeekOne.equals(typicalClassWeekTwo));
        assertFalse(typicalClassWeekTwo.equals(emptyClassWeekThree));
    }

    @Test
    public void studentBecomesPresent_validId_success() {
        Index index = Index.fromOneBased(1);
        typicalClassWeekOne.studentBecomesPresent(index);
        assertEquals(PRESENT_BUT_HAS_NOT_PARTICIPATED,
                typicalClassWeekOne.getStudentList().get(index.getZeroBased()));
    }

    @Test
    public void studentBecomesPresent_nullId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> emptyClassWeekOne.studentBecomesPresent(null));
        assertThrows(NullPointerException.class, () -> typicalClassWeekOne.studentBecomesPresent(null));
    }

    @Test
    public void studentParticipates_validId_success() {
        Index index = Index.fromOneBased(1);
        typicalClassWeekOne.studentParticipates(index);
        assertEquals(ABSENT_BUT_HAS_PARTICIPATED,
                typicalClassWeekOne.getStudentList().get(index.getZeroBased()));
    }

    @Test
    public void studentParticipates_nullId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> emptyClassWeekOne.studentParticipates(null));
        assertThrows(NullPointerException.class, () -> typicalClassWeekOne.studentParticipates(null));
    }

    @Test
    public void studentBecomesPresentAndParticipates_validId_success() {
        Index index = Index.fromOneBased(1);
        typicalClassWeekOne.studentBecomesPresent(index);
        typicalClassWeekOne.studentParticipates(index);
        assertEquals(PRESENT_AND_HAS_PARTICIPATED,
                typicalClassWeekOne.getStudentList().get(index.getZeroBased()));
    }

    @Test
    public void updateClassAfterDeletion_validId_success() {
        // Set up expected result. Alice already removed
        List<Person> expectedMasterList = getTypicalPersonsMinusAlice();
        Class expectedClass = new Class(WEEK_ONE, expectedMasterList);

        // Set up actual result. Remove Alice
        typicalMasterList.remove(ALICE);
        typicalClassWeekOne.updateClassAfterDeletion(Index.fromOneBased(1), typicalMasterList);

        assertEquals(expectedClass, typicalClassWeekOne);
    }
}
