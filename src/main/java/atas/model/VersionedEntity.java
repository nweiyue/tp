package atas.model;

/**
 * Enables behavior that enables saving and going back and forth between different history states.
 */
public interface VersionedEntity {

    /**
     * Saves the current entity state in its history.
     */
    void commit();

    /**
     * Returns <code>true</code> if entity has states to undo, <code>false</code> otherwise.
     */
    boolean canUndo();

    /**
     * Restores the previous entity state from its history.
     */
    void undo();

    /**
     * Returns <code>true</code> if entity has states to redo, <code>false</code> otherwise.
     */
    boolean canRedo();

    /**
     * Returns a previously undone entity from its history.
     */
    void redo();

}
