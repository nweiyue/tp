package atas.model.session;

import static atas.testutil.Assert.assertThrows;
import static atas.testutil.TypicalVersionedEntities.getTypicalVersionedSessionListEarliest;
import static atas.testutil.TypicalVersionedEntities.getTypicalVersionedSessionListLatest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.model.exceptions.UnableToRedoException;
import atas.model.exceptions.UnableToUndoException;

public class VersionedSessionListTest {

    // Session list with one commit and at the latest state
    private final VersionedSessionList typicalVersionedSessionListLatest = getTypicalVersionedSessionListLatest();
    // Session list with one commit and at the earliest state
    private final VersionedSessionList typicalVersionedSessionListEarliest = getTypicalVersionedSessionListEarliest();

    @Test
    public void commit() {
        assertEquals(2, typicalVersionedSessionListLatest.getSessionStateList().size());
        typicalVersionedSessionListLatest.commit();
        assertEquals(3, typicalVersionedSessionListLatest.getSessionStateList().size());

        assertEquals(2, typicalVersionedSessionListEarliest.getSessionStateList().size());
        typicalVersionedSessionListEarliest.commit();
        assertEquals(2, typicalVersionedSessionListEarliest.getSessionStateList().size());
    }

    @Test
    public void canUndo() {
        // typical session list (latest state) check -> returns true
        assertTrue(typicalVersionedSessionListLatest.canUndo());

        // typical session list (earliest state) check -> returns false
        assertFalse(typicalVersionedSessionListEarliest.canUndo());
    }

    @Test
    public void undo() {
        // typical session list (latest state) check -> success
        assertEquals(1, typicalVersionedSessionListLatest.getCurrentStatePointer());
        typicalVersionedSessionListLatest.undo();
        assertEquals(0, typicalVersionedSessionListLatest.getCurrentStatePointer());

        // typical session list (earliest state) check -> throws exception
        assertThrows(UnableToUndoException.class, typicalVersionedSessionListEarliest::undo);
    }

    @Test
    public void canRedo() {
        // typical session list (latest state) check -> returns false
        assertFalse(typicalVersionedSessionListLatest.canRedo());

        // typical session list (earliest state) check -> returns true
        assertTrue(typicalVersionedSessionListEarliest.canRedo());
    }

    @Test
    public void redo() {
        // typical session list (latest state) check -> throws exception
        assertThrows(UnableToRedoException.class, typicalVersionedSessionListLatest::redo);

        // typical session list (earliest state) check -> success
        assertEquals(0, typicalVersionedSessionListEarliest.getCurrentStatePointer());
        typicalVersionedSessionListEarliest.redo();
        assertEquals(1, typicalVersionedSessionListEarliest.getCurrentStatePointer());
    }

}
