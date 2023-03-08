package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.ConflictingLessonsException;

/**
 * Adds an assignment to a student.
 */
public class CreateLessonCommand extends Command {

    public static final String COMMAND_WORD = "new-lesson";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a lesson to a student.\n"
        + "Parameters: "
        + "n/STUDENT_NAME "
        + "l/LESSON_NAME "
        + "start/STARTTIME\n"
        + "end/ENDTIME\n"
        + "Example: " + COMMAND_WORD + " "
        + "n/John Doe "
        + "l/H2 Math Lesson "
        + "start/2023-03-01 12:00 "
        + "end/2023-03-01 14:00";

    public static final String MESSAGE_DATE = "endTime must be after startTime, both in the format YYYY-MM-DD HH:mm";

    private final String lessonName;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final NameContainsKeywordsPredicate predicate;

    /**
     * Creates a CreateHomeworkCommand to add the specified assignment to the specified student.
     */
    public CreateLessonCommand(NameContainsKeywordsPredicate predicate, String lessonName, LocalDateTime startTime,
                               LocalDateTime endTime) {
        requireNonNull(lessonName);
        requireNonNull(startTime);
        requireNonNull(endTime);
        requireNonNull(predicate);

        this.lessonName = lessonName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);

        List<Student> studentList = model.getFilteredStudentList();

        Lesson lesson = new Lesson(lessonName, startTime, endTime);

        try {
            for (Student student : studentList) {
                student.addLesson(lesson);
            }
        } catch (ConflictingLessonsException e) {
            throw new CommandException(e.getMessage());
        }

        StringBuilder sb = new StringBuilder();
        for (Student student : studentList) {
            sb.append(student.getName().fullName);
            sb.append("\n");
        }

        return new CommandResult(
            String.format(Messages.MESSAGE_LESSON_ADDED_SUCCESS, lesson, sb));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof CreateLessonCommand // instanceof handles nulls
            && predicate.equals(((CreateLessonCommand) other).predicate)
            && lessonName.equals(((CreateLessonCommand) other).lessonName)
            && startTime.equals(((CreateLessonCommand) other).startTime)
            && endTime.equals(((CreateLessonCommand) other).endTime));
    }
}

