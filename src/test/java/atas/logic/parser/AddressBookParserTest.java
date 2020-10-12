package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static atas.logic.commands.atas.HelpCommand.MESSAGE_USAGE;
import static atas.testutil.Assert.assertThrows;
import static atas.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static atas.testutil.TypicalTabNames.CLASSES_TAB_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import atas.commons.core.index.Index;
import atas.logic.commands.atas.ExitCommand;
import atas.logic.commands.atas.HelpCommand;
import atas.logic.commands.atas.SwitchCommand;
import atas.logic.commands.confirmation.ConfirmationCommand;
import atas.logic.commands.confirmation.ConfirmationRejectCommand;
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
import atas.model.attendance.IndexRange;
import atas.model.attendance.Session;
import atas.model.person.NameContainsKeywordsPredicate;
import atas.model.person.Person;
import atas.testutil.EditPersonDescriptorBuilder;
import atas.testutil.EditSessionDescriptorBuilder;
import atas.testutil.PersonBuilder;
import atas.testutil.PersonUtil;
import atas.testutil.SessionUtil;
import atas.testutil.TypicalSessions;


public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ConfirmationCommand);
        assertTrue(parser.parseCommand(ConfirmationCommand.REJECT_COMMAND_FULL) instanceof ConfirmationRejectCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ConfirmationCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        ConfirmationCommand command = (ConfirmationCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new ConfirmationCommand(new DeleteCommand(INDEX_FIRST_PERSON)), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        ConfirmationCommand command = (ConfirmationCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new ConfirmationCommand(new EditCommand(INDEX_FIRST_PERSON, descriptor)), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_switch() throws Exception {
        SwitchCommand command = (SwitchCommand) parser.parseCommand(
                SwitchCommand.COMMAND_WORD + " " + CLASSES_TAB_NAME);
        assertEquals(new SwitchCommand(CLASSES_TAB_NAME), command);
    }

    @Test
    public void parseCommand_addSession() throws Exception {
        Session session = TypicalSessions.TUT1;
        AddSessionCommand command = (AddSessionCommand) parser.parseCommand(
                SessionUtil.getAddSessionCommand(session));
        assertEquals(new AddSessionCommand(session), command);
    }

    @Test
    public void parseCommand_deleteSession() throws Exception {
        ConfirmationCommand command = (ConfirmationCommand) parser.parseCommand(
                DeleteSessionCommand.COMMAND_WORD + " " + TypicalSessions.SESSIONNAME_WEEK_ONE_STRING);
        assertEquals(new ConfirmationCommand(new DeleteSessionCommand(TypicalSessions.SESSIONNAME_WEEK_ONE)), command);
    }

    @Test
    public void parseCommand_editSession() throws Exception {
        Session session = TypicalSessions.SESSION_WEEK_ONE;
        EditSessionCommand.EditSessionDescriptor descriptor = new EditSessionDescriptorBuilder(session).build();
        EditSessionCommand command = (EditSessionCommand) parser.parseCommand(EditSessionCommand.COMMAND_WORD + " "
                + TypicalSessions.SESSIONNAME_WEEK_TWO + " " + SessionUtil.getSessionDetails(session));
        assertEquals(new EditSessionCommand(TypicalSessions.SESSIONNAME_WEEK_TWO, descriptor), command);
    }

    @Test
    public void parseCommand_clearSession() throws Exception {
        ClearSessionsCommand command = (ClearSessionsCommand) parser.parseCommand(ClearSessionsCommand.COMMAND_WORD);
        assertEquals(new ClearSessionsCommand(), command);
    }

    @Test
    public void parseCommand_enterSession() throws Exception {
        EnterSessionCommand command = (EnterSessionCommand) parser.parseCommand(
                EnterSessionCommand.COMMAND_WORD + " " + CliSyntax.PREFIX_SESSIONINDEX + "1");
        assertEquals(new EnterSessionCommand(Index.fromOneBased(1)), command);
    }

    @Test
    public void parseCommand_participate() throws Exception {
        ParticipateCommand command = (ParticipateCommand) parser.parseCommand(
                ParticipateCommand.COMMAND_WORD + " 1-3");
        assertEquals(new ParticipateCommand(new IndexRange("1-3")), command);
    }

    @Test
    public void parseCommand_presence() throws Exception {
        PresenceCommand command = (PresenceCommand) parser.parseCommand(
                PresenceCommand.COMMAND_WORD + " 1-3");
        assertEquals(new PresenceCommand(new IndexRange("1-3")), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
