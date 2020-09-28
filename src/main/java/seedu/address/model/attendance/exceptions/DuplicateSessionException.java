package seedu.address.model.attendance.exceptions;

/**
 * Signals that the operation will result in duplicate Classes (Classes are considered duplicates if they have the same
 * date).
 */
public class DuplicateSessionException extends RuntimeException {
    public DuplicateSessionException() {
        super("Operation would result in duplicate classes");
    }
}
