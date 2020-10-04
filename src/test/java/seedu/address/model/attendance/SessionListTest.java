package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsMinusAlice;
import static seedu.address.testutil.TypicalSessions.CONSULTATION;
import static seedu.address.testutil.TypicalSessions.LAB2;
import static seedu.address.testutil.TypicalSessions.TUT1;
import static seedu.address.testutil.TypicalSessions.TUT2;
import static seedu.address.testutil.TypicalSessions.TUT3;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.attendance.exceptions.DuplicateSessionException;
import seedu.address.model.person.Person;
import seedu.address.testutil.SessionBuilder;
import seedu.address.testutil.TypicalSessions;


public class SessionListTest {
    private final SessionBuilder sessionBuilder = new SessionBuilder();

    private final SessionList emptySessionList = new SessionList();
    private final SessionList typicalSessionList = new SessionList(TypicalSessions.getTypicalSessionList());

    @Test
    public void contains_nullClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> emptySessionList.contains(null));
        assertThrows(NullPointerException.class, () -> typicalSessionList.contains(null));
    }

    @Test
    public void add_duplicateSession_throwsDuplicateSessionException() {
        emptySessionList.addSession(sessionBuilder.build());
        assertThrows(DuplicateSessionException.class, () -> emptySessionList.addSession(sessionBuilder.build()));
        typicalSessionList.addSession(sessionBuilder.build());
        assertThrows(DuplicateSessionException.class, () -> typicalSessionList.addSession(sessionBuilder.build()));
    }

    @Test
    public void updateAfterDeletingStudent_validId_success() {
        // Set up expected result. Alice already removed
        List<Person> expectedMasterList = getTypicalPersonsMinusAlice();
        SessionList expectedSessionList = new SessionList(expectedMasterList);
        expectedSessionList.addSession(TUT1);
        expectedSessionList.addSession(TUT2);
        expectedSessionList.addSession(TUT3);

        // Set up actual result. Remove Alice
        List<Person> typicalMasterList = getTypicalPersons();
        SessionList actualSessionList = new SessionList(typicalMasterList);
        actualSessionList.addSession(TUT1);
        actualSessionList.addSession(TUT2);
        actualSessionList.addSession(TUT3);

        typicalMasterList.remove(ALICE);
        actualSessionList.updateAllSessionsAfterDelete(Index.fromOneBased(1));

        assertEquals(expectedSessionList, actualSessionList);
    }

    @Test
    public void updateAfterDeletingStudent_nullId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> emptySessionList.updateAllSessionsAfterDelete(null));
        assertThrows(NullPointerException.class, () -> typicalSessionList.updateAllSessionsAfterDelete(null));
    }

    @Test
    public void contains() {
        Session session = CONSULTATION;

        assertThrows(NullPointerException.class, () -> emptySessionList.contains(null));

        assertFalse(emptySessionList.contains(CONSULTATION));
        assertFalse(typicalSessionList.contains(CONSULTATION));

        emptySessionList.addSession(CONSULTATION);
        assertTrue(emptySessionList.contains(CONSULTATION));
        assertTrue(typicalSessionList.contains(LAB2));
    }

}

