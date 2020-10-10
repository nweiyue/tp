package atas.logic.commands.studentlist;

import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import atas.model.AddressBook;
import atas.model.Model;
import atas.model.ModelManager;
import atas.testutil.ModelManagerBuilder;

public class ClearStudentsCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearStudentsCommand(), model, ClearStudentsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = ModelManagerBuilder.buildTypicalModelManager();
        Model expectedModel = ModelManagerBuilder.buildTypicalModelManager();
        expectedModel.setAddressBook(new AddressBook());

        assertCommandSuccess(new ClearStudentsCommand(), model, ClearStudentsCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
