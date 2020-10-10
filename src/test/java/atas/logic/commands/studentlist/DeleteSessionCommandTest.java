package atas.logic.commands.studentlist;

import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import atas.logic.commands.sessionlist.DeleteSessionCommand;
import atas.model.Model;
import atas.model.ModelManager;
import atas.model.UserPrefs;
import atas.model.attendance.SessionName;
import atas.testutil.ModelManagerBuilder;
import atas.testutil.TypicalSessions;

public class DeleteSessionCommandTest {

    private Model model = ModelManagerBuilder.buildTypicalModelManager();

    @Test
    public void execute_validSessionName_success() {

        SessionName sessionName = TypicalSessions.TUT1.getSessionName();
        DeleteSessionCommand deleteSessionCommand = new DeleteSessionCommand(sessionName);

        String expectedMessage = DeleteSessionCommand.MESSAGE_SUCCESS;

        ModelManager expectedModel = new ModelManager(TypicalSessions.getTypicalSessionListMinusTut1(
                model.getStudentList().getStudentList()), model.getStudentList(), new UserPrefs());

        assertCommandSuccess(deleteSessionCommand, model, expectedMessage, expectedModel);
        assertFalse(model.hasSession(TypicalSessions.TUT1));
    }

}
