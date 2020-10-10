package atas.logic.commands.studentlist;

import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import atas.model.Model;
import atas.model.ModelManager;
import atas.model.StudentList;
import atas.testutil.ModelManagerBuilder;

public class ClearStudentListCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearStudentListCommand(), model,
                ClearStudentListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = ModelManagerBuilder.buildTypicalModelManager();
        Model expectedModel = ModelManagerBuilder.buildTypicalModelManager();
        expectedModel.setStudentList(new StudentList());

        assertCommandSuccess(new ClearStudentListCommand(), model,
                ClearStudentListCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
