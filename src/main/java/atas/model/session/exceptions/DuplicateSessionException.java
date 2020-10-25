package atas.model.session.exceptions;

/**
 * Signals that the operation will result in duplicate sessions (Sessions are considered duplicates
 * if they have the same date and name).
 */
public class DuplicateSessionException extends RuntimeException {
    public DuplicateSessionException() {
        super("Operation would result in duplicate classes");
    }
}
