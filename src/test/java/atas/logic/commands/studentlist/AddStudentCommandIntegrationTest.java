package atas.logic.commands.studentlist;

import static atas.logic.commands.CommandTestUtil.assertCommandFailure;
import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static atas.testutil.TypicalSessions.getTypicalSessionList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atas.model.Model;
import atas.model.ModelManager;
import atas.model.UserPrefs;
import atas.model.student.Student;
import atas.testutil.ModelManagerBuilder;
import atas.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddStudentCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = ModelManagerBuilder.buildTypicalModelManager();
    }

    @Test
    public void execute_newStudent_success() {
        Student validStudent = new StudentBuilder().build();

        Model expectedModel = new ModelManager(getTypicalSessionList(model.getStudentList().getStudentList()),
                model.getStudentList(), new UserPrefs());
        expectedModel.addStudent(validStudent);

        assertCommandSuccess(new AddStudentCommand(validStudent), model,
                String.format(AddStudentCommand.MESSAGE_SUCCESS, validStudent), expectedModel);
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        Student studentInList = model.getStudentList().getStudentList().get(0);
        assertCommandFailure(new AddStudentCommand(studentInList), model, AddStudentCommand.MESSAGE_DUPLICATE_STUDENT);
    }

}
