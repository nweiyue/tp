package atas.logic.commands.atas;

import static atas.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import atas.logic.commands.exceptions.CommandException;
import atas.model.Model;
import atas.testutil.ModelManagerBuilder;
import atas.testutil.TypicalStudents;

public class RngCommandTest {

    private Model model = ModelManagerBuilder.buildTypicalModelManager();

    @Test
    public void execute_rngNonEmptyList_success() throws CommandException {
        String expectedMessage = String.format(RngCommand.MESSAGE_SUCCESS, TypicalStudents.ELLE.getName());
        String actualMessage = new RngCommand().execute(model).getFeedbackToUser();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void execute_rngEmptyList_throwsCommandException() {
        model.clearStudentList(); // clear student list
        assertThrows(CommandException.class, RngCommand.MESSAGE_NO_STUDENTS, () -> new RngCommand().execute(model));
    }

}
