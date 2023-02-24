package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Homework;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;

/**
 * Adds an assignment to a student.
 */
public class CreateHomeworkCommand extends Command {

    public static final String COMMAND_WORD = "assign-homework";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an assignment to a student.\n"
            + "Parameters: "
            + "s/STUDENT_NAME "
            + "hw/HOMEWORK_NAME "
            + "d/DEADLINE\n"
            + "Example: " + COMMAND_WORD + " "
            + "s/John Doe "
            + "as/Math Homework "
            + "d/2023-03-01T12:00";

    public static final String MESSAGE_SUCCESS = "New homework added: %1$s";
    public static final String MESSAGE_STUDENT_NOT_FOUND = "The specified student cannot be found in the address book";

    private final String homeworkName;
    private final LocalDateTime deadline;
    private final NameContainsKeywordsPredicate predicate;

    /**
     * Creates a CreateHomeworkCommand to add the specified assignment to the specified student.
     */
    public CreateHomeworkCommand(NameContainsKeywordsPredicate predicate, String homeworkName, LocalDateTime deadline) {
        requireNonNull(homeworkName);
        requireNonNull(deadline);
        requireNonNull(predicate);

        this.homeworkName = homeworkName;
        this.deadline = deadline;
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);

        List<Student> studentList = model.getFilteredStudentList();

        if (deadline.isBefore(LocalDateTime.now())) {
            throw new CommandException("Deadline must be in the future.");
        }

        Homework homework = new Homework(homeworkName, deadline);

        // Add the Homework to the target student's list of Assignments
        for (Student student : studentList) {
            student.addHomework(homework);
        }

        StringBuilder sb = new StringBuilder();
        for (Student student : studentList) {
            sb.append(student.getName().fullName);
            sb.append("\n");
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_HOMEWORK_ADDED_SUCCESS, homework, sb));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CreateHomeworkCommand // instanceof handles nulls
                && predicate.equals(((CreateHomeworkCommand) other).predicate)
                && homeworkName.equals(((CreateHomeworkCommand) other).homeworkName)
                && deadline.equals(((CreateHomeworkCommand) other).deadline));
    }
}
