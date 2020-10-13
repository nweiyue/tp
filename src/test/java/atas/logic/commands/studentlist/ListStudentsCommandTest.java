package atas.logic.commands.studentlist;

import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static atas.logic.commands.CommandTestUtil.showStudentAtIndex;
import static atas.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atas.model.Model;
import atas.testutil.ModelManagerBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListStudentsCommand.
 */
public class ListStudentsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = ModelManagerBuilder.buildTypicalModelManager();
        expectedModel = ModelManagerBuilder.buildTypicalModelManager();
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListStudentsCommand(), model, ListStudentsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        assertCommandSuccess(new ListStudentsCommand(), model, ListStudentsCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
