package atas.model.exceptions;

/**
 * Signals that the undo operation is unable to be performed due to reaching the earliest history state.
 */
public class UnableToUndoException extends RuntimeException {
    public UnableToUndoException() {
        super("Unable to undo");
    }
}

