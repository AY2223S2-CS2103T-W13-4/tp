package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.NamePredicate;
import seedu.address.model.student.Student;

/**
 * Deletes a lesson from a student.
 */
public class DeleteExamCommand extends Command {

    public static final String COMMAND_WORD = "delete-exam";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an exam from a student.\n"
        + "Parameters: "
        + PREFIX_NAME + "STUDENT_NAME "
        + PREFIX_INDEX + "INDEX\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "John Doe "
        + PREFIX_INDEX + "1";

    private final NamePredicate predicate;
    private final Index targetIndex;
    private final List<String> inputNames;


    /**
     * Creates a DeleteLessonCommand to delete the specified lesson from the specified student.
     */
    public DeleteExamCommand(List<String> inputNames, NamePredicate predicate, Index targetIndex) {
        requireNonNull(predicate);
        requireNonNull(targetIndex);

        this.inputNames = inputNames;
        this.predicate = predicate;
        this.targetIndex = targetIndex;

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
        StringBuilder dupNames = new StringBuilder("");
        for (String name : inputNames) {
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

        StringBuilder sb = new StringBuilder();
        for (Student student : studentList) {
            try {
                sb.append(String.format(Messages.MESSAGE_EXAM_DELETED_SUCCESS, targetIndex.getOneBased(),
                    student.getExam(targetIndex).toString(), student.getName().toString()));
                sb.append("\n");
                student.deleteExam(targetIndex);
            } catch (IndexOutOfBoundsException e) {
                throw new CommandException(Messages.MESSAGE_INVALID_EXAM_DISPLAYED_INDEX);
            }
        }

        return new CommandResult(sb.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof DeleteExamCommand // instanceof handles nulls
            && predicate.equals(((DeleteExamCommand) other).predicate)); // state check
    }
}