package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalSessions.getTypicalSessionList;

import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

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
