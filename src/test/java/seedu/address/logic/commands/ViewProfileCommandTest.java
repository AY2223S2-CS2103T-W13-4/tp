package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.NamePredicate;
import seedu.address.model.tag.Tag;

import javax.swing.text.View;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

class ViewProfileCommandTest {
    private Model modelStub = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private static final String SEPERATOR = "--------------------------------------------------\n";


    @Test
    void execute_inexistentName_failure() throws CommandException {
        Command input = new ViewProfileCommand(List.of("Amy Bee"), new NamePredicate(List.of("Amy Bee")));
        CommandException actualResult = assertThrows(CommandException.class, () -> input.execute(modelStub));
        CommandException expectedResult = new CommandException(String.format(Messages.MESSAGE_NO_SUCH_STUDENT, "Amy Bee"));
        assertEquals(expectedResult.getMessage(), actualResult.getMessage());
    }

    @Test
    void execute_existentName_success() throws CommandException {
        Command input = new ViewProfileCommand(List.of(ALICE.getName().fullName), new NamePredicate(List.of(ALICE.getName().fullName)));
        CommandResult actualResult = input.execute(modelStub);

        StringBuilder sb = new StringBuilder();
        sb.append(SEPERATOR);
        sb.append(ALICE.getName().fullName).append(":\n");
        sb.append("Phone: ").append(ALICE.getPhone()).append("\n");
        sb.append("Address: ").append(ALICE.getAddress().toString()).append("\n");
        sb.append("Email: ").append(ALICE.getEmail()).append("\n");
        sb.append("Tags: ");
        for (Tag tag : ALICE.getTags()) {
            sb.append(tag);
        }
        sb.append("\n");

        sb.append(SEPERATOR);
        CommandResult expectedResult = new CommandResult(String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, 1, sb), false, false);

        assertEquals(expectedResult, actualResult);

    }
}
