package atas.model.session;

import static atas.testutil.Assert.assertThrows;
import static atas.testutil.TypicalSessions.CONSULTATION;
import static atas.testutil.TypicalSessions.LAB2;
import static atas.testutil.TypicalSessions.TUT1;
import static atas.testutil.TypicalSessions.TUT2;
import static atas.testutil.TypicalSessions.TUT3;
import static atas.testutil.TypicalStudents.ALICE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import atas.commons.core.index.Index;
import atas.model.session.exceptions.DuplicateSessionException;
import atas.model.student.Student;
import atas.testutil.Assert;
import atas.testutil.SessionBuilder;
import atas.testutil.TypicalSessions;
import atas.testutil.TypicalStudents;

public class SessionListTest {
    private final SessionBuilder sessionBuilder = new SessionBuilder();

    private final SessionList emptySessionList = new SessionList();
    private final SessionList typicalSessionList = new SessionList(TypicalSessions.getTypicalSessionList());

    @Test
    public void contains_nullClass_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> emptySessionList.contains(null));
        Assert.assertThrows(NullPointerException.class, () -> typicalSessionList.contains(null));
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
        List<Student> expectedMasterList = TypicalStudents.getTypicalStudentsMinusAlice();
        SessionList expectedSessionList = new SessionList(expectedMasterList);
        expectedSessionList.addSession(TUT1);
        expectedSessionList.addSession(TUT2);
        expectedSessionList.addSession(TUT3);

        // Set up actual result. Remove Alice
        List<Student> typicalMasterList = TypicalStudents.getTypicalStudents();
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

    @Test
    public void returnListSize() {
        SessionList emptyList = new SessionList();
        assertEquals(0, emptyList.returnSize());
    }

    @Test
    public void equals() {
        SessionList typicalList = new SessionList(TypicalStudents.getTypicalStudents());
        typicalList.addSession(TypicalSessions.duplicateSession(TypicalSessions.TUT1));
        SessionList emptyList = new SessionList();

        //different list size
        assertFalse(typicalList.equals(emptyList));

        //same size but different sessions
        emptyList.addSession(TUT2);
        assertFalse(typicalList.equals(emptyList));

        //different type
        assertFalse(typicalList.equals(LAB2));

    }

}

