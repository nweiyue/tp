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

class SessionTest {

    private final List<Person> emptyMasterList = new AddressBook().getModifiablePersonList();
    private final Session emptySessionWeekOne = new Session(WEEK_ONE, emptyMasterList);
    private final Session emptySessionWeekTwo = new Session(WEEK_TWO, emptyMasterList);
    private final Session emptySessionWeekThree = new Session(WEEK_THREE, emptyMasterList);

    private final List<Person> typicalMasterList = getTypicalAddressBook().getModifiablePersonList();
    private final Session typicalSessionWeekOne = new Session(WEEK_ONE, typicalMasterList);
    private final Session typicalSessionWeekTwo = new Session(WEEK_TWO, typicalMasterList);
    private final Session typicalSessionWeekThree = new Session(WEEK_THREE, typicalMasterList);

    @Test
    public void isSameClass() {
        // same object -> returns true
        assertTrue(emptySessionWeekOne.isSameSession(emptySessionWeekOne));
        assertTrue(typicalSessionWeekOne.isSameSession(typicalSessionWeekOne));

        // same date but different master list -> returns true
        assertTrue(emptySessionWeekOne.isSameSession(typicalSessionWeekOne));

        // null -> returns false
        assertFalse(emptySessionWeekOne.isSameSession(null));
        assertFalse(typicalSessionWeekOne.isSameSession(null));

        // different date -> returns false
        assertFalse(emptySessionWeekOne.isSameSession(emptySessionWeekTwo));
        assertFalse(emptySessionWeekTwo.isSameSession(emptySessionWeekThree));
        assertFalse(typicalSessionWeekOne.isSameSession(typicalSessionWeekTwo));
        assertFalse(typicalSessionWeekTwo.isSameSession(typicalSessionWeekThree));
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(emptySessionWeekOne.equals(emptySessionWeekOne));
        assertTrue(typicalSessionWeekOne.equals(typicalSessionWeekOne));

        // null -> returns false
        assertFalse(emptySessionWeekOne.equals(null));

        // same date but different master list -> returns false
        assertFalse(emptySessionWeekOne.equals(typicalSessionWeekOne));

        // same master list but different date -> returns false
        assertFalse(emptySessionWeekOne.equals(emptySessionWeekTwo));
        assertFalse(emptySessionWeekTwo.equals(emptySessionWeekThree));

        // different date and master list -> returns false
        assertFalse(emptySessionWeekOne.equals(typicalSessionWeekTwo));
        assertFalse(typicalSessionWeekTwo.equals(emptySessionWeekThree));
    }

    @Test
    public void studentBecomesPresent_validId_success() {
        Index index = Index.fromOneBased(1);
        typicalSessionWeekOne.studentBecomesPresent(index);
        assertEquals(PRESENT_BUT_HAS_NOT_PARTICIPATED,
                typicalSessionWeekOne.getStudentList().get(index.getZeroBased()));
    }

    @Test
    public void studentBecomesPresent_nullId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> emptySessionWeekOne.studentBecomesPresent(null));
        assertThrows(NullPointerException.class, () -> typicalSessionWeekOne.studentBecomesPresent(null));
    }

    @Test
    public void studentParticipates_validId_success() {
        Index index = Index.fromOneBased(1);
        typicalSessionWeekOne.studentParticipates(index);
        assertEquals(ABSENT_BUT_HAS_PARTICIPATED,
                typicalSessionWeekOne.getStudentList().get(index.getZeroBased()));
    }

    @Test
    public void studentParticipates_nullId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> emptySessionWeekOne.studentParticipates(null));
        assertThrows(NullPointerException.class, () -> typicalSessionWeekOne.studentParticipates(null));
    }

    @Test
    public void studentBecomesPresentAndParticipates_validId_success() {
        Index index = Index.fromOneBased(1);
        typicalSessionWeekOne.studentBecomesPresent(index);
        typicalSessionWeekOne.studentParticipates(index);
        assertEquals(PRESENT_AND_HAS_PARTICIPATED,
                typicalSessionWeekOne.getStudentList().get(index.getZeroBased()));
    }

    @Test
    public void updateSessionAfterDeletion_validId_success() {
        // Set up expected result. Alice already removed
        List<Person> expectedMasterList = getTypicalPersonsMinusAlice();
        Session expectedSession = new Session(WEEK_ONE, expectedMasterList);

        // Set up actual result. Remove Alice
        typicalMasterList.remove(ALICE);
        typicalSessionWeekOne.updateSessionAfterDeletion(Index.fromOneBased(1), typicalMasterList);

        assertEquals(expectedSession, typicalSessionWeekOne);
    }
}
