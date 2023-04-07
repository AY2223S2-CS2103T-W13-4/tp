package seedu.address.logic.commands.homework;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Homework;
import seedu.address.model.student.NamePredicate;
import seedu.address.model.student.Student;
import seedu.address.testutil.StudentBuilder;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.ParserUtil.parseDeadline;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeleteHomeworkCommandTest {
    private AddressBook abStub = new AddressBook();

    @Test
    void execute_invalidIndex_failure() throws ParseException {
        List<Homework> hwList = List.of(
                new Homework("Math Ch 3", ParserUtil.parseDeadline("2027-07-14T14:00"))
        );
        Student student = new StudentBuilder().withHomeworkList(hwList).build();
        abStub.addStudent(student);
        Model modelStub = new ModelManager(abStub, new UserPrefs());
        DeleteHomeworkCommand input = new DeleteHomeworkCommand(List.of(student.getName().fullName),
                                                         new NamePredicate(List.of(student.getName().fullName)),
                                                         Index.fromOneBased(3));
        assertCommandFailure(input, modelStub, Messages.MESSAGE_INVALID_HOMEWORK_DISPLAYED_INDEX);
    }


    @Test
    void execute_invalidStudent_failure() throws ParseException {
        List<Homework> hwList = List.of(
                new Homework("Math Ch 3", ParserUtil.parseDeadline("2027-07-14T14:00"))
        );
        Student student = new StudentBuilder().withHomeworkList(hwList).build();
        Model modelStub = new ModelManager(abStub, new UserPrefs());
        DeleteHomeworkCommand input = new DeleteHomeworkCommand(List.of(student.getName().fullName),
                                                                new NamePredicate(List.of(student.getName().fullName)),
                                                                Index.fromOneBased(3));

        String expectedMsg = String.format(Messages.MESSAGE_NO_SUCH_STUDENT, student.getName().fullName);

        assertCommandFailure(input, modelStub, expectedMsg);
    }

    @Test
    void testEquals() {
    }
}