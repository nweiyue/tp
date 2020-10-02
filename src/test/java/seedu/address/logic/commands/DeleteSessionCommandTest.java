package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalSessions.TUT1;
import static seedu.address.testutil.TypicalSessions.getTypicalSessionListMinusTut1;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.attendance.SessionName;
import seedu.address.testutil.ModelManagerBuilder;

public class DeleteSessionCommandTest {

    private Model model = ModelManagerBuilder.buildTypicalModelManager();

    @Test
    public void execute_validSessionName_success() {

        SessionName sessionName = TUT1.getSessionName();
        DeleteSessionCommand deleteSessionCommand = new DeleteSessionCommand(sessionName);

        String expectedMessage = DeleteSessionCommand.MESSAGE_SUCCESS;

        ModelManager expectedModel = new ModelManager(getTypicalSessionListMinusTut1(
                model.getAddressBook().getPersonList()), model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(deleteSessionCommand, model, expectedMessage, expectedModel);
        assertFalse(model.hasSession(TUT1));
    }

}
