package atas.testutil;

import static atas.testutil.TypicalPersons.getTypicalAddressBook;
import static atas.testutil.TypicalSessions.getTypicalSessionList;

import atas.model.ModelManager;
import atas.model.UserPrefs;

/**
 * A util class for building typical sessionlist with typical addressbook.
 */
public class ModelManagerBuilder {

    private ModelManagerBuilder() {}

    /**
     * Build a typical ATAS.
     */
    public static ModelManager buildTypicalModelManager() {
        return new ModelManager(getTypicalSessionList(getTypicalAddressBook().getPersonList()),
                getTypicalAddressBook(), new UserPrefs());
    }
}
