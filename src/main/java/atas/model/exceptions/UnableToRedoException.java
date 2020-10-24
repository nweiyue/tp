package atas.model.exceptions;

/**
 * Signals that the redo operation is unable to be performed due to reaching the latest history state.
 */
public class UnableToRedoException extends RuntimeException {
    public UnableToRedoException() {
        super("Unable to redo");
    }
}
