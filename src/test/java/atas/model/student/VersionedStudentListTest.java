package atas.model.student;

import static atas.testutil.Assert.assertThrows;
import static atas.testutil.TypicalVersionedEntities.getTypicalVersionedStudentListEarliest;
import static atas.testutil.TypicalVersionedEntities.getTypicalVersionedStudentListLatest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.model.exceptions.UnableToRedoException;
import atas.model.exceptions.UnableToUndoException;

public class VersionedStudentListTest {

    // Student list with one commit and at the latest state
    private final VersionedStudentList typicalVersionedStudentListLatest = getTypicalVersionedStudentListLatest();
    // Student list with one commit and at the earliest state
    private final VersionedStudentList typicalVersionedStudentListEarliest = getTypicalVersionedStudentListEarliest();

    @Test
    public void commit() {
        assertEquals(2, typicalVersionedStudentListLatest.getStudentStateList().size());
        typicalVersionedStudentListLatest.commit();
        assertEquals(3, typicalVersionedStudentListLatest.getStudentStateList().size());

        assertEquals(2, typicalVersionedStudentListEarliest.getStudentStateList().size());
        typicalVersionedStudentListEarliest.commit();
        assertEquals(2, typicalVersionedStudentListEarliest.getStudentStateList().size());
    }

    @Test
    public void canUndo() {
        // typical student list (latest state) check -> returns true
        assertTrue(typicalVersionedStudentListLatest.canUndo());

        // typical student list (earliest state) check -> returns false
        assertFalse(typicalVersionedStudentListEarliest.canUndo());
    }

    @Test
    public void undo() {
        // typical student list (latest state) check -> success
        assertEquals(1, typicalVersionedStudentListLatest.getCurrentStatePointer());
        typicalVersionedStudentListLatest.undo();
        assertEquals(0, typicalVersionedStudentListLatest.getCurrentStatePointer());

        // typical student list (earliest state) check -> throws exception
        assertThrows(UnableToUndoException.class, typicalVersionedStudentListEarliest::undo);
    }

    @Test
    public void canRedo() {
        // typical student list (latest state) check -> returns false
        assertFalse(typicalVersionedStudentListLatest.canRedo());

        // typical student list (earliest state) check -> returns true
        assertTrue(typicalVersionedStudentListEarliest.canRedo());
    }

    @Test
    public void redo() {
        // typical student list (latest state) check -> throws exception
        assertThrows(UnableToRedoException.class, typicalVersionedStudentListLatest::redo);

        // typical student list (earliest state) check -> success
        assertEquals(0, typicalVersionedStudentListEarliest.getCurrentStatePointer());
        typicalVersionedStudentListEarliest.redo();
        assertEquals(1, typicalVersionedStudentListEarliest.getCurrentStatePointer());
    }

}
