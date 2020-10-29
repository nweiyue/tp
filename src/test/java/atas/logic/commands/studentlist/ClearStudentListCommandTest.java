package atas.logic.commands.studentlist;

import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import atas.model.Model;
import atas.model.ModelManager;
import atas.testutil.ModelManagerBuilder;

public class ClearStudentListCommandTest {

    @Test
    public void execute_emptyStudentList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearStudentListCommand(), model,
                ClearStudentListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyStudentList_success() {
        Model model = ModelManagerBuilder.buildTypicalModelManager();
        Model expectedModel = ModelManagerBuilder.buildTypicalModelManager();
        expectedModel.clearStudentList();

        assertCommandSuccess(new ClearStudentListCommand(), model,
                ClearStudentListCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
