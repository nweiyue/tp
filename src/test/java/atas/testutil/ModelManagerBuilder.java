package atas.testutil;

import static atas.testutil.TypicalMemoContents.EMPTY_MEMO_CONTENT;
import static atas.testutil.TypicalSessions.getTypicalSessionList;
import static atas.testutil.TypicalStudents.getTypicalStudentList;

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
        return new ModelManager(getTypicalSessionList(getTypicalStudentList().getStudentList()),
                getTypicalStudentList(), new UserPrefs(), EMPTY_MEMO_CONTENT);
    }
}
