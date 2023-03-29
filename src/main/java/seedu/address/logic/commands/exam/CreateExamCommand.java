package seedu.address.logic.commands.exam;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Exam;
import seedu.address.model.student.NamePredicate;
import seedu.address.model.student.Student;

/**
 * Adds an exam to a student.
 */
public class CreateExamCommand extends Command {

    public static final String COMMAND_WORD = "new-exam";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an exam to a student.\n"
        + "Parameters: "
        + PREFIX_NAME + "STUDENT_NAME "
        + PREFIX_EXAM + "EXAM_NAME "
        + PREFIX_STARTTIME + "Start time "
        + PREFIX_ENDTIME + "End time\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "John Doe "
        + PREFIX_EXAM + "Math MYE "
        + PREFIX_STARTTIME + "2023-05-21 12:00 "
        + PREFIX_ENDTIME + "2023-05-21 14:00";


    private final String examDescription;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final NamePredicate predicate;
    private final List<String> names;

    /**
     * Creates a CreateExamCommand to add the specified exam to the specified student.
     */
    public CreateExamCommand(List<String> names, NamePredicate predicate, String examDescription,
                             LocalDateTime startTime, LocalDateTime endTime) {
        requireNonNull(predicate);
        requireNonNull(examDescription);
        requireNonNull(startTime);

        this.examDescription = examDescription;
        this.startTime = startTime;
        this.endTime = endTime;
        this.predicate = predicate;
        this.names = names;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        StringBuilder nonExistNames = new StringBuilder();
        for (String name : names) {
            if (model.noSuchStudent(name)) {
                nonExistNames.append(name).append(", ");
            }
        }
        if (nonExistNames.length() != 0) {
            nonExistNames = new StringBuilder(nonExistNames.substring(0, nonExistNames.length() - 2));
            throw new CommandException(String.format(Messages.MESSAGE_NO_SUCH_STUDENT, nonExistNames));
        }
        StringBuilder dupNames = new StringBuilder();
        for (String name : names) {
            if (model.hasDuplicateName(name)) {
                dupNames.append(name).append(", ");
            }
        }
        if (dupNames.length() != 0) {
            dupNames = new StringBuilder(dupNames.substring(0, dupNames.length() - 2));
            throw new CommandException(String.format(Messages.MESSAGE_HAS_DUPLICATE_NAMES, dupNames));
        }
        model.updateFilteredStudentList(predicate);

        List<Student> studentList = model.getFilteredStudentList();

        if (startTime.isBefore(LocalDateTime.now())) {
            throw new CommandException("start time must be in the future.");
        }

        if (startTime.isAfter(endTime)) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXAM_TIME);
        }

        if (Duration.between(startTime, endTime).toMinutes() < 30 || Duration.between(startTime,
            endTime).toHours() > 3) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXAM_DURATION);
        }

        //Todo: currently weightage is 0 for convenience, implement this where possible
        Exam exam = new Exam(examDescription, startTime, endTime, 0d, null);

        try {
            for (Student student : studentList) {
                student.addExam(exam);
            }
        } catch (Exception e) {
            throw new CommandException(e.getMessage());
        }

        StringBuilder sb = new StringBuilder();
        for (Student student : studentList) {
            sb.append(student.getName().fullName);
            sb.append("\n");
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_EXAM_ADDED_SUCCESS, exam, sb));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateExamCommand // instanceof handles nulls
                && predicate.equals(((CreateExamCommand) other).predicate)
                && startTime.equals(((CreateExamCommand) other).startTime)
                && endTime.equals(((CreateExamCommand) other).endTime)
                && examDescription.equals(((CreateExamCommand) other).examDescription));
    }
}

