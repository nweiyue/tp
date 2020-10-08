package seedu.address.logic.parser;

import seedu.address.logic.commands.sessionlistcommands.EditSessionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attendance.SessionDate;
import seedu.address.model.attendance.SessionName;

import java.time.format.DateTimeParseException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSIONDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SESSIONNAME;

/**
 * Parses input arguments and creates a new EditSessionCommand object
 */
public class EditSessionCommandParser implements Parser<EditSessionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditSessionCommand
     * and returns an EditSessionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditSessionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SESSIONNAME, PREFIX_SESSIONDATE);

        SessionName sessionName;

        try {
            sessionName = ParserUtil.parseSessionName(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, EditSessionCommand.MESSAGE_USAGE), pe);
        }

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionCommand.EditSessionDescriptor();
        if (argMultimap.getValue(PREFIX_SESSIONNAME).isPresent()) {
            descriptor.setSessionName(ParserUtil.parseSessionName(argMultimap.getValue(PREFIX_SESSIONNAME).get()));
        }
        if (argMultimap.getValue(PREFIX_SESSIONDATE).isPresent()) {
            try {
                descriptor.setSessionDate(ParserUtil.parseSessionDate(argMultimap.getValue(PREFIX_SESSIONDATE).get()));
            } catch (DateTimeParseException e) {
                throw new ParseException(SessionDate.MESSAGE_CONSTRAINTS);
            }
        }

        if (!descriptor.isAnyFieldEdited()) {
            throw new ParseException(EditSessionCommand.MESSAGE_NOT_EDITED);
        }

        return new EditSessionCommand(sessionName, descriptor);
    }

}
