package seedu.address.logic.commands.homework;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Homework;
import seedu.address.model.student.NamePredicate;
import seedu.address.model.student.Student;

/**
 * Marks an assignment as done for a student.
 */
public class MarkHomeworkAsDoneCommand extends Command {

    public static final String COMMAND_WORD = "mark-homework";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks an assignment as done for a student.\n"
            + "Parameters: "
            + PREFIX_NAME + "STUDENT_NAME "
            + PREFIX_INDEX + "INDEX\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_INDEX + "1";

    private final NamePredicate predicate;
    private final Index targetIndex;
    private final List<String> names;

    /**
     * Creates a MarkHomeworkAsDoneCommand to mark the specified assignment as done for the specified student.
     */
    public MarkHomeworkAsDoneCommand(List<String> names, NamePredicate predicate, Index targetIndex) {
        requireNonNull(predicate);
        requireNonNull(targetIndex);

        this.predicate = predicate;
        this.targetIndex = targetIndex;
        this.names = names;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException if the command's preconditions are not met
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        StringBuilder dupNames = new StringBuilder();
        for (String name : names) {
            if (model.hasDuplicateName(name)) {
                dupNames.append(name).append(", ");
            }
            if (dupNames.length() != 0) {
                dupNames = new StringBuilder(dupNames.substring(0, dupNames.length() - 2));
                throw new CommandException(String.format(Messages.MESSAGE_HAS_DUPLICATE_NAMES, dupNames));
            }
        }
        model.updateFilteredStudentList(predicate);

        List<Student> studentList = model.getFilteredStudentList();

        if (studentList.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_NAME);
        }

        StringBuilder sb = new StringBuilder();
        for (Student student : studentList) {
            try {
                Homework homeworkToMarkAsDone = student.getHomework(targetIndex);
                if (homeworkToMarkAsDone.isCompleted()) {
                    sb.append(String.format(Messages.MESSAGE_HOMEWORK_ALREADY_MARKED_AS_DONE,
                            homeworkToMarkAsDone.getDescription(), student.getName().toString()));
                } else {
                    sb.append(String.format(Messages.MESSAGE_HOMEWORK_MARKED_AS_DONE,
                            homeworkToMarkAsDone.getDescription(), student.getName().toString()));
                    student.markHomeworkAsDone(targetIndex);
                }
                sb.append("\n");
            } catch (IndexOutOfBoundsException e) {
                throw new CommandException(Messages.MESSAGE_INVALID_HOMEWORK_DISPLAYED_INDEX);
            }
        }

        return new CommandResult(sb.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkHomeworkAsDoneCommand // instanceof handles nulls
                && predicate.equals(((MarkHomeworkAsDoneCommand) other).predicate)); // state check
    }
}
