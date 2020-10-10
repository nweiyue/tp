package atas.testutil;

import static atas.testutil.TypicalSessions.getTypicalSessionList;
import static atas.testutil.TypicalStudents.getTypicalAddressBook;

import atas.model.ModelManager;
import atas.model.UserPrefs;

/**
 * A util class for building typical sessionlist with typical studentList.
 */
public class ModelManagerBuilder {

    private ModelManagerBuilder() {}

    /**
     * Build a typical ATAS.
     */
    public static ModelManager buildTypicalModelManager() {
        return new ModelManager(getTypicalSessionList(getTypicalAddressBook().getStudentList()),
                getTypicalAddressBook(), new UserPrefs());
    }
}
