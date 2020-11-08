package atas.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = "The student index provided is invalid";
    public static final String MESSAGE_INVALID_SESSION_DISPLAYED_INDEX = "The session index provided is invalid";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX_RANGE = "The student index range"
            + " provided is invalid";
    public static final String MESSAGE_STUDENTS_LISTED_OVERVIEW = "%1$d students listed!";
    public static final String MESSAGE_SESSION_DOES_NOT_EXIST = "The session does not exist";
    public static final String MESSAGE_MULTIPLE_STUDENTS_LISTED_OVERVIEW = "%1$d students listed!";
    public static final String MESSAGE_SINGLE_STUDENT_LISTED_OVERVIEW = "%1$d student listed!";
    public static final String MESSAGE_INVALID_CONFIRMATION_INPUT = "Confirmation input is invalid! "
            + "Try again? (yes/no)";
    public static final String MESSAGE_NOT_ON_CURRENT_SESSION = "You can only use this on the current session tab!";
    public static final String MESSAGE_NOT_IN_SESSION = "You have to be in a session to use this!";

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
