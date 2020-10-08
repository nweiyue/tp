package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The student index provided is invalid";
    public static final String MESSAGE_INVALID_SESSION_DISPLAYED_INDEX = "The session index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d students listed!";
    public static final String MESSAGE_SESSION_DOES_NOT_EXIST = "The session does not exist";
    public static final String MESSAGE_MULTIPLE_STUDENTS_LISTED_OVERVIEW = "%1$d students listed!";
    public static final String MESSAGE_SINGLE_STUDENT_LISTED_OVERVIEW = "%1$d student listed!";
    public static final String MESSAGE_INVALID_CONFIRMATION_INPUT = "Confirmation input is invalid!";

    /**
     * Retrieves the correct message to be displayed to the user
     * based on the number of students in the list.
     *
     * @param numberOfStudents Number of students in the list.
     * @return message to be displayed to user.
     */

    public static String getStudentListedMessage(int numberOfStudents) {
        if (numberOfStudents == 1) {
            return MESSAGE_SINGLE_STUDENT_LISTED_OVERVIEW;
        } else {
            // all other cases including 0 students
            return MESSAGE_MULTIPLE_STUDENTS_LISTED_OVERVIEW;
        }
    }
}
