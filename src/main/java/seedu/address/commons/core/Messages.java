package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX = "The student index provided is invalid";
    public static final String MESSAGE_STUDENTS_LISTED_OVERVIEW = "%1$d students listed!";

    public static final String MESSAGE_HOMEWORK_ADDED_SUCCESS = "New homework added:\n%s\n"
            + "To the following students:\n%s";

    public static final String MESSAGE_HOMEWORK_LISTED_OVERVIEW = "%d homework from %d student listed:\n%s";
    public static final String MESSAGE_ALL_HOMEWORK_LISTED_OVERVIEW = "%d homework listed:\n%s";
    public static final String MESSAGE_NO_HOMEWORK_FOUND = "No homework found";
    public static final String MESSAGE_INVALID_HOMEWORK_DISPLAYED_INDEX = "The homework index provided is invalid";
    public static final String MESSAGE_HOMEWORK_DELETED_SUCCESS = "Homework : %s. %s\n"
            + "Deleted from the %s\n";
    public static final String MESSAGE_LESSON_ADDED_SUCCESS = "New lesson added: \n%s \n"
        + "To the following students: \n%s";
    public static final String MESSAGE_HOMEWORK_ALREADY_MARKED_AS_DONE =
            "Homework %s of student %s is already marked as done\n";
    public static final String MESSAGE_HOMEWORK_MARKED_AS_DONE = "Homework %s of student %s is marked as done\n";
    public static final String MESSAGE_HOMEWORK_MARKED_AS_UNDONE = "Homework %s of student %s is marked as undone\n";
    public static final String MESSAGE_HOMEWORK_ALREADY_MARKED_AS_UNDONE =
            "Homework %s of student %s is already marked as undone\n";
    public static final String MESSAGE_INVALID_STUDENT_NAME = "No student found!\n";
    public static final String MESSAGE_NO_LESSON_FOUND = "No lesson is found!";
    public static final String MESSAGE_ALL_LESSONS_LISTED_OVERVIEW = "%d lessons from all students listed:\n%s";
    public static final String MESSAGE_LESSONS_LISTED_OVERVIEW = "%d lessons from %d students listed: \n%s";
    public static final String MESSAGE_LESSON_DELETED_SUCCESS = "Lesson: %s, %s\n"
        + "Deleted from the %s\n";
    public static final String MESSAGE_INVALID_LESSON_DISPLAYED_INDEX = "The lesson index provided is invalid";

    public static final String MESSAGE_INVALID_EXAM_DISPLAYED_INDEX = "The exam index provided is invalid";
    public static final String MESSAGE_EXAM_DELETED_SUCCESS = "Exam: %s, %s\n"
        + "Deleted from the %s\n";

    public static final String MESSAGE_EXAM_ADDED_SUCCESS = "New exam added: \n%s \n"
            + "To the following students: \n%s";
    public static final String MESSAGE_EXAMS_LISTED_OVERVIEW = "%d exams from %d students listed: \n%s";
    public static final String MESSAGE_ALL_EXAMS_LISTED_OVERVIEW = "%d exams from all students listed:\n%s";
    public static final String MESSAGE_NO_EXAM_FOUND = "No exam is found!";
    public static final String MESSAGE_HOMEWORK_UPDATED_SUCCESS = "Homework %s of student %s is updated to:\n"
            + "Homework name: %s\n"
            + "Deadline: %s\n";
    public static final String MESSAGE_LESSON_UPDATED_SUCCESS = "Lesson %s of student %s is updated to:\n"
        + "Lesson name: %s\n"
        + "Start Time: %s\n"
        + "End Time: %s\n";
    public static final String MESSAGE_HAS_DUPLICATE_NAMES = "Duplicate names detected for **%s** "
            + "please enter full name(s)";
}
