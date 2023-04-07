package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ViewProfileCommand;
import seedu.address.logic.commands.homework.ViewHomeworkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.NamePredicate;

import javax.swing.text.View;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

class ViewProfileCommandParserTest {

    ViewProfileCommandParser parser = new ViewProfileCommandParser();
    @Test
    void parse_noNameAfterPrefix_failure() {
        String input = "name/";
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                        ViewProfileCommand.MESSAGE_USAGE);
        assertParseFailure(parser, input, expected);
    }

}