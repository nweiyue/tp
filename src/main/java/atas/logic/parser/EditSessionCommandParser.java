package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.logic.commands.sessionlist.EditSessionCommand.MESSAGE_NOT_EDITED;
import static atas.logic.commands.sessionlist.EditSessionCommand.MESSAGE_USAGE;
import static atas.logic.parser.CliSyntax.PREFIX_SESSIONDATE;
import static atas.logic.parser.CliSyntax.PREFIX_SESSIONNAME;
import static atas.model.attendance.SessionDate.MESSAGE_CONSTRAINTS;
import static java.util.Objects.requireNonNull;

import java.time.format.DateTimeParseException;

import atas.logic.commands.sessionlist.EditSessionCommand;
import atas.logic.parser.exceptions.ParseException;
import atas.model.attendance.SessionName;

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
                    MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), pe);
        }

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionCommand.EditSessionDescriptor();
        if (argMultimap.getValue(PREFIX_SESSIONNAME).isPresent()) {
            descriptor.setSessionName(ParserUtil.parseSessionName(argMultimap.getValue(PREFIX_SESSIONNAME).get()));
        }
        if (argMultimap.getValue(PREFIX_SESSIONDATE).isPresent()) {
            try {
                descriptor.setSessionDate(ParserUtil.parseSessionDate(argMultimap.getValue(PREFIX_SESSIONDATE).get()));
            } catch (DateTimeParseException e) {
                throw new ParseException(MESSAGE_CONSTRAINTS);
            }
        }

        if (!descriptor.isAnyFieldEdited()) {
            throw new ParseException(MESSAGE_NOT_EDITED);
        }

        return new EditSessionCommand(sessionName, descriptor);
    }

}
