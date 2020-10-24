package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.logic.commands.sessionlist.EditSessionCommand.MESSAGE_NOT_EDITED;
import static atas.logic.parser.CliSyntax.PREFIX_SESSION_DATE;
import static atas.logic.parser.CliSyntax.PREFIX_SESSION_NAME;
import static atas.model.session.SessionDate.MESSAGE_CONSTRAINTS;
import static java.util.Objects.requireNonNull;

import java.time.format.DateTimeParseException;

import atas.commons.core.index.Index;
import atas.logic.commands.sessionlist.EditSessionCommand;
import atas.logic.parser.exceptions.ParseException;

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
                ArgumentTokenizer.tokenize(args, PREFIX_SESSION_NAME, PREFIX_SESSION_DATE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, EditSessionCommand.MESSAGE_USAGE), pe);
        }

        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionCommand.EditSessionDescriptor();
        if (argMultimap.getValue(PREFIX_SESSION_NAME).isPresent()) {
            descriptor.setSessionName(ParserUtil.parseSessionName(argMultimap.getValue(PREFIX_SESSION_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_SESSION_DATE).isPresent()) {
            try {
                descriptor.setSessionDate(ParserUtil.parseSessionDate(argMultimap.getValue(PREFIX_SESSION_DATE).get()));
            } catch (DateTimeParseException e) {
                throw new ParseException(MESSAGE_CONSTRAINTS);
            }
        }

        if (!descriptor.isAnyFieldEdited()) {
            throw new ParseException(MESSAGE_NOT_EDITED);
        }

        return new EditSessionCommand(index, descriptor);
    }

}
