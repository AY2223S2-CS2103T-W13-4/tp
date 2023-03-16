package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkHomeworkAsUndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new MarkHomeworkAsDoneCommand object
 */
public class MarkHomeworkAsUndoCommandParser implements Parser<MarkHomeworkAsUndoCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkHomeworkAsDoneCommand
     * and returns a MarkHomeworkAsDoneCommand object for execution.
     *
     * @param args the user input to be parsed.
     * @return MarkHomeworkAsUndoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkHomeworkAsUndoCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_INDEX)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkHomeworkAsUndoCommand.MESSAGE_USAGE));
        }

        List<String> nameKeywords = argMultimap.getAllValues(PREFIX_NAME);
        // for all the names, trim the name and only take the first word
        for (int i = 0; i < nameKeywords.size(); i++) {
            String name = nameKeywords.get(i);
            name = name.trim();
            int spaceIndex = name.indexOf(" ");
            if (spaceIndex != -1) {
                name = name.substring(0, spaceIndex);
            }
            nameKeywords.set(i, name);
        }

        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INDEX).get());

        return new MarkHomeworkAsUndoCommand(new NameContainsKeywordsPredicate(nameKeywords), index);
    }

    /**
     * Returns true if all prefixes are present in the given {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
