package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static atas.logic.commands.atas.HelpCommand.MESSAGE_USAGE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import atas.logic.commands.Command;
import atas.logic.commands.atas.ExitCommand;
import atas.logic.commands.atas.HelpCommand;
import atas.logic.commands.atas.SwitchCommand;
import atas.logic.commands.confirmation.ConfirmationCommand;
import atas.logic.commands.sessionlist.AddSessionCommand;
import atas.logic.commands.sessionlist.ClearSessionsCommand;
import atas.logic.commands.sessionlist.DeleteSessionCommand;
import atas.logic.commands.sessionlist.EditSessionCommand;
import atas.logic.commands.sessionlist.EnterSessionCommand;
import atas.logic.commands.sessionlist.session.ParticipateCommand;
import atas.logic.commands.sessionlist.session.PresenceCommand;
import atas.logic.commands.studentlist.AddCommand;
import atas.logic.commands.studentlist.ClearCommand;
import atas.logic.commands.studentlist.DeleteCommand;
import atas.logic.commands.studentlist.EditCommand;
import atas.logic.commands.studentlist.FindCommand;
import atas.logic.commands.studentlist.ListCommand;
import atas.logic.parser.exceptions.ParseException;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

/**
 * Parses user input.
 */
public class AddressBookParser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /** The command to be executed after confirmation prompt*/
    private ConfirmationCommand previousCommand;

    @FXML
    private Tab inClassTab;

    /**
     * Removes the previous confirmation command.
     */
    private void removePreviousCommand() {
        this.previousCommand = null;
    }
    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // If there is an edit, delete or clear, there should be a previous command.
        if (previousCommand != null) {
            Command result = new ConfirmationParser(previousCommand).parse(userInput);
            // Remove previous command if the result is able to be executed.
            removePreviousCommand();
            return result;
        } else {
            switch (commandWord) {

            case AddCommand.COMMAND_WORD:
                return new AddCommandParser().parse(arguments);

            case EditCommand.COMMAND_WORD:
                //Sets the previous command to a confirmation edit command.
                this.previousCommand = new ConfirmationCommand(new EditCommandParser().parse(arguments));
                return previousCommand;

            case DeleteCommand.COMMAND_WORD:
                //Sets the previous command to a confirmation delete command.
                this.previousCommand = new ConfirmationCommand(new DeleteCommandParser().parse(arguments));
                return previousCommand;

            case ClearCommand.COMMAND_WORD:
                //Sets the previous command to a confirmation clear command.
                this.previousCommand = new ConfirmationCommand(new ClearCommand());
                return previousCommand;

            case FindCommand.COMMAND_WORD:
                return new FindCommandParser().parse(arguments);

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();

            case SwitchCommand.COMMAND_WORD:
                return new SwitchCommandParser().parse(arguments);

            case AddSessionCommand.COMMAND_WORD:
                return new AddSessionCommandParser().parse(arguments);

            case DeleteSessionCommand.COMMAND_WORD:
                this.previousCommand = new ConfirmationCommand(new DeleteSessionCommandParser().parse(arguments));
                return previousCommand;

            case EditSessionCommand.COMMAND_WORD:
                return new EditSessionCommandParser().parse(arguments);

            case ClearSessionsCommand.COMMAND_WORD:
                return new ClearSessionsCommand();

            case ParticipateCommand.COMMAND_WORD:
                return new ParticipateCommandParser().parse(arguments);

            case PresenceCommand.COMMAND_WORD:
                return new PresenceCommandParser().parse(arguments);

            case EnterSessionCommand.COMMAND_WORD:
                return new EnterSessionCommandParser().parse(arguments);

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        }
    }

}
