package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.sessionlistcommands.ClearSessionsCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.testutil.ModelManagerBuilder;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

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
