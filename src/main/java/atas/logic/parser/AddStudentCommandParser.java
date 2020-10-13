package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.logic.commands.studentlist.AddStudentCommand.MESSAGE_USAGE;
import static atas.logic.parser.CliSyntax.PREFIX_EMAIL;
import static atas.logic.parser.CliSyntax.PREFIX_MATRICULATION;
import static atas.logic.parser.CliSyntax.PREFIX_NAME;
import static atas.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import atas.logic.commands.studentlist.AddStudentCommand;
import atas.logic.parser.exceptions.ParseException;
import atas.model.student.Email;
import atas.model.student.Matriculation;
import atas.model.student.Name;
import atas.model.student.Student;
import atas.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddStudentCommand object
 */
public class AddStudentCommandParser implements Parser<AddStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddStudentCommand
     * and returns an AddStudentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MATRICULATION, PREFIX_EMAIL, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MATRICULATION, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Matriculation matriculation = ParserUtil.parseMatriculation(argMultimap.getValue(PREFIX_MATRICULATION).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Student student = new Student(name, matriculation, email, tagList);

        return new AddStudentCommand(student);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
