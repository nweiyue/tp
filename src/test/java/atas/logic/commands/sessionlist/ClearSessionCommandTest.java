package atas.logic.commands.sessionlist;

import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import atas.model.Model;
import atas.model.ModelManager;
import atas.testutil.ModelManagerBuilder;

public class ClearSessionCommandTest {

    @Test
    public void execute_emptySessionList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearSessionsCommand(), model, ClearSessionsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptySessionList_success() {
        Model model = ModelManagerBuilder.buildTypicalModelManager();
        Model expectedModel = ModelManagerBuilder.buildTypicalModelManager();
        expectedModel.resetSessionList();

        assertCommandSuccess(new ClearSessionsCommand(), model, ClearSessionsCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
