package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
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
import seedu.address.model.attendance.exceptions.DuplicateSessionException;
import seedu.address.model.person.Person;


public class SessionListTest {

    private final List<Person> emptyMasterList = new AddressBook().getModifiablePersonList();
    private final SessionList emptySessionList = new SessionList(emptyMasterList);

    private final List<Person> typicalMasterList = getTypicalAddressBook().getModifiablePersonList();
    private final SessionList typicalSessionList = new SessionList(typicalMasterList);

    private final Session emptySessionWeekOne = new Session(WEEK_ONE, emptyMasterList);
    private final Session typicalSessionWeekOne = new Session(WEEK_ONE, typicalMasterList);

    @Test
    public void contains_nullClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> emptySessionList.contains(null));
        assertThrows(NullPointerException.class, () -> typicalSessionList.contains(null));
    }

    @Test
    public void contains_classNotInList_returnsFalse() {
        assertFalse(emptySessionList.contains(emptySessionWeekOne));
        assertFalse(typicalSessionList.contains(typicalSessionWeekOne));
    }

    @Test
    public void add_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> emptySessionList.addSession(null));
        assertThrows(NullPointerException.class, () -> typicalSessionList.addSession(null));
    }

    @Test
    public void add_duplicateSession_throwsDuplicateSessionException() {
        emptySessionList.addSession(WEEK_ONE);
        assertThrows(DuplicateSessionException.class, () -> emptySessionList.addSession(WEEK_ONE));
        typicalSessionList.addSession(WEEK_ONE);
        assertThrows(DuplicateSessionException.class, () -> typicalSessionList.addSession(WEEK_ONE));
    }

    @Test
    public void updateAfterDeletingStudent_validId_success() {
        // Set up expected result. Alice already removed
        List<Person> expectedMasterList = getTypicalPersonsMinusAlice();
        SessionList expectedSessionList = new SessionList(expectedMasterList);
        expectedSessionList.addSession(WEEK_ONE);
        expectedSessionList.addSession(WEEK_TWO);
        expectedSessionList.addSession(WEEK_THREE);

        // Set up actual result. Remove Alice
        SessionList actualSessionList = new SessionList(typicalMasterList);
        actualSessionList.addSession(WEEK_ONE);
        actualSessionList.addSession(WEEK_TWO);
        actualSessionList.addSession(WEEK_THREE);

        typicalMasterList.remove(ALICE);
        actualSessionList.updateAllSessionsAfterDeletion(Index.fromOneBased(1));

        assertEquals(expectedSessionList, actualSessionList);
    }

    @Test
    public void updateAfterDeletingStudent_nullId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> emptySessionList.updateAllSessionsAfterDeletion(null));
        assertThrows(NullPointerException.class, () -> typicalSessionList.updateAllSessionsAfterDeletion(null));
    }
}
