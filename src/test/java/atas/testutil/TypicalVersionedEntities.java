package atas.testutil;

import static atas.testutil.TypicalAttributes.getTypicalAttributesList;
import static atas.testutil.TypicalSessions.getTypicalSessionList;
import static atas.testutil.TypicalStudents.getTypicalStudentList;

import atas.model.session.VersionedAttributesList;
import atas.model.session.VersionedSessionList;
import atas.model.student.VersionedStudentList;

/**
 * Contains some of various versioned entities.
 * Each entity contains 2 states.
 * {@code latest} entity implies that the {@code currentStatePointer} currently points to the latest state.
 * (i.e. state pointer = 1)
 * Like-wise for {@code earliest}. (i.e. state pointer = 0)
 * For simplicity's sake, there is virtually no difference between either state (apart from state pointer position).
 */
public class TypicalVersionedEntities {

    public static VersionedStudentList getTypicalVersionedStudentListLatest() {
        VersionedStudentList toReturn = new VersionedStudentList(getTypicalStudentList());
        toReturn.commit();
        return toReturn;
    }

    public static VersionedStudentList getTypicalVersionedStudentListEarliest() {
        VersionedStudentList toReturn = new VersionedStudentList(getTypicalStudentList());
        toReturn.commit();
        toReturn.setCurrentStatePointer(0);
        return toReturn;
    }

    public static VersionedSessionList getTypicalVersionedSessionListLatest() {
        VersionedSessionList toReturn = new VersionedSessionList(getTypicalSessionList());
        toReturn.commit();
        return toReturn;
    }

    public static VersionedSessionList getTypicalVersionedSessionListEarliest() {
        VersionedSessionList toReturn = new VersionedSessionList(getTypicalSessionList());
        toReturn.commit();
        toReturn.setCurrentStatePointer(0);
        return toReturn;
    }

    public static VersionedAttributesList getTypicalVersionedAttributesListLatest() {
        VersionedAttributesList toReturn = new VersionedAttributesList(getTypicalAttributesList());
        toReturn.commit();
        return toReturn;
    }

    public static VersionedAttributesList getTypicalVersionedAttributesListEarliest() {
        VersionedAttributesList toReturn = new VersionedAttributesList(getTypicalAttributesList());
        toReturn.commit();
        toReturn.setCurrentStatePointer(0);
        return toReturn;
    }

}
