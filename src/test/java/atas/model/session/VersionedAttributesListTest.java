package atas.model.session;

import static atas.testutil.Assert.assertThrows;
import static atas.testutil.TypicalVersionedEntities.getTypicalVersionedAttributesListEarliest;
import static atas.testutil.TypicalVersionedEntities.getTypicalVersionedAttributesListLatest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.model.exceptions.UnableToRedoException;
import atas.model.exceptions.UnableToUndoException;

public class VersionedAttributesListTest {

    // Initialized attributes list
    private final VersionedAttributesList emptyAttributesList = new VersionedAttributesList();
    // Attributes list with one commit and at the latest state
    private final VersionedAttributesList typicalAttributesListLatest = getTypicalVersionedAttributesListLatest();
    // Attributes list with one commit and at the earliest state
    private final VersionedAttributesList typicalAttributesListEarliest = getTypicalVersionedAttributesListEarliest();

    @Test
    public void commit() {
        assertEquals(1, getInitialAttributesStateListSize());
        emptyAttributesList.commit();
        assertEquals(2, getInitialAttributesStateListSize());
    }

    @Test
    public void canUndo() {
        // empty attributes list check
        assertFalse(emptyAttributesList.canUndo());
        emptyAttributesList.commit();
        assertTrue(emptyAttributesList.canUndo());

        // typical attributes list (latest state) check -> returns true
        assertTrue(typicalAttributesListLatest.canUndo());

        // typical attributes list (earliest state) check -> returns false
        assertFalse(typicalAttributesListEarliest.canUndo());
    }

    @Test
    public void undo() {
        // empty attributes list check -> throws exception
        assertThrows(UnableToUndoException.class, emptyAttributesList::undo);

        // typical attributes list (latest state) check -> success
        assertEquals(1, typicalAttributesListLatest.getCurrentStatePointer());
        typicalAttributesListLatest.undo();
        assertEquals(0, typicalAttributesListLatest.getCurrentStatePointer());

        // typical attributes list (earliest state) check -> throws exception
        assertThrows(UnableToUndoException.class, typicalAttributesListEarliest::undo);
    }

    @Test
    public void canRedo() {
        // empty attributes list check
        assertFalse(emptyAttributesList.canRedo());
        emptyAttributesList.commit();
        assertFalse(emptyAttributesList.canRedo()); // still unable to redo after committing when at latest state

        // typical attributes list (latest state) check -> returns false
        assertFalse(typicalAttributesListLatest.canRedo());

        // typical attributes list (earliest state) check -> returns true
        assertTrue(typicalAttributesListEarliest.canRedo());
    }

    @Test
    public void redo() {
        // empty attributes list check -> throws exception
        assertThrows(UnableToRedoException.class, emptyAttributesList::redo);

        // typical attributes list (latest state) check -> throws exception
        assertThrows(UnableToRedoException.class, typicalAttributesListLatest::redo);

        // typical attributes list (earliest state) check -> success
        assertEquals(0, typicalAttributesListEarliest.getCurrentStatePointer());
        typicalAttributesListEarliest.redo();
        assertEquals(1, typicalAttributesListEarliest.getCurrentStatePointer());
    }

    private int getInitialAttributesStateListSize() {
        return emptyAttributesList.getAttributeStateList().size();
    }

}
