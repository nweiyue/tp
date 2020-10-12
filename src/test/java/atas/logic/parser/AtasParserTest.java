package atas.logic.parser;

import static atas.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static atas.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static atas.logic.commands.atas.HelpCommand.MESSAGE_USAGE;
import static atas.testutil.Assert.assertThrows;
import static atas.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static atas.testutil.TypicalTabNames.CLASSES_TAB_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import atas.logic.commands.atas.ExitCommand;
import atas.logic.commands.atas.HelpCommand;
import atas.logic.commands.atas.SwitchCommand;
import atas.logic.commands.confirmation.ConfirmationCommand;
import atas.logic.commands.confirmation.ConfirmationRejectCommand;
import atas.logic.commands.studentlist.AddStudentCommand;
import atas.logic.commands.studentlist.ClearStudentListCommand;
import atas.logic.commands.studentlist.DeleteStudentCommand;
import atas.logic.commands.studentlist.EditStudentCommand;
import atas.logic.commands.studentlist.FindStudentCommand;
import atas.logic.commands.studentlist.ListCommand;
import atas.logic.parser.exceptions.ParseException;
import atas.model.student.NameContainsKeywordsPredicate;
import atas.model.student.Student;
import atas.testutil.EditStudentDescriptorBuilder;
import atas.testutil.StudentBuilder;
import atas.testutil.StudentUtil;

public class AtasParserTest {

    private final AtasParser parser = new AtasParser();

    @Test
    public void parseCommand_add() throws Exception {
        Student student = new StudentBuilder().build();
        AddStudentCommand command = (AddStudentCommand) parser.parseCommand(StudentUtil.getAddCommand(student));
        assertEquals(new AddStudentCommand(student), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearStudentListCommand.COMMAND_WORD) instanceof ConfirmationCommand);
        assertTrue(parser.parseCommand(ConfirmationCommand.REJECT_COMMAND_FULL) instanceof ConfirmationRejectCommand);
        assertTrue(parser.parseCommand(ClearStudentListCommand.COMMAND_WORD + " 3") instanceof ConfirmationCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        ConfirmationCommand command = (ConfirmationCommand) parser.parseCommand(
                DeleteStudentCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new ConfirmationCommand(new DeleteStudentCommand(INDEX_FIRST_STUDENT)), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new StudentBuilder().build();
        EditStudentCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        ConfirmationCommand command = (ConfirmationCommand) parser.parseCommand(EditStudentCommand.COMMAND_WORD + " "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        assertEquals(new ConfirmationCommand(new EditStudentCommand(INDEX_FIRST_STUDENT, descriptor)), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindStudentCommand command = (FindStudentCommand) parser.parseCommand(
                FindStudentCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindStudentCommand(new NameContainsKeywordsPredicate(keywords)), command);
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
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
