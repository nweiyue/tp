package atas.logic.commands.studentlist;

import static atas.logic.commands.CommandTestUtil.assertCommandFailure;
import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static atas.testutil.TypicalSessions.getTypicalSessionList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atas.model.Model;
import atas.model.ModelManager;
import atas.model.UserPrefs;
import atas.model.person.Person;
import atas.testutil.ModelManagerBuilder;
import atas.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddStudentCommand}.
 */
public class AddStudentCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = ModelManagerBuilder.buildTypicalModelManager();
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(getTypicalSessionList(model.getAddressBook().getPersonList()),
                model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddStudentCommand(validPerson), model,
                String.format(AddStudentCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddStudentCommand(personInList), model, AddStudentCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
