package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDates.WEEK_ONE;
import static seedu.address.testutil.TypicalDates.WEEK_THREE;
import static seedu.address.testutil.TypicalDates.WEEK_TWO;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonsMinusAlice;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.attendance.exceptions.DuplicateClassException;
import seedu.address.model.person.Person;


public class ClassListTest {

    private final List<Person> emptyMasterList = new AddressBook().getModifiablePersonList();
    private final ClassList emptyClassList = new ClassList(emptyMasterList);

    private final List<Person> typicalMasterList = getTypicalAddressBook().getModifiablePersonList();
    private final ClassList typicalClassList = new ClassList(typicalMasterList);

    private final Class emptyClassWeekOne = new Class(WEEK_ONE, emptyMasterList);
    private final Class typicalClassWeekOne = new Class(WEEK_ONE, typicalMasterList);

    @Test
    public void contains_nullClass_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> emptyClassList.contains(null));
        assertThrows(NullPointerException.class, () -> typicalClassList.contains(null));
    }

    @Test
    public void contains_classNotInList_returnsFalse() {
        assertFalse(emptyClassList.contains(emptyClassWeekOne));
        assertFalse(typicalClassList.contains(typicalClassWeekOne));
    }

    @Test
    public void add_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> emptyClassList.addClass(null));
        assertThrows(NullPointerException.class, () -> typicalClassList.addClass(null));
    }

    @Test
    public void add_duplicateClass_throwsDuplicateClassException() {
        emptyClassList.addClass(WEEK_ONE);
        assertThrows(DuplicateClassException.class, () -> emptyClassList.addClass(WEEK_ONE));
        typicalClassList.addClass(WEEK_ONE);
        assertThrows(DuplicateClassException.class, () -> typicalClassList.addClass(WEEK_ONE));
    }

    @Test
    public void updateAfterDeletingStudent_validId_success() {
        // Set up expected result. Alice already removed
        List<Person> expectedMasterList = getTypicalPersonsMinusAlice();
        ClassList expectedClassList = new ClassList(expectedMasterList);
        expectedClassList.addClass(WEEK_ONE);
        expectedClassList.addClass(WEEK_TWO);
        expectedClassList.addClass(WEEK_THREE);

        // Set up actual result. Remove Alice
        ClassList actualClassList = new ClassList(typicalMasterList);
        actualClassList.addClass(WEEK_ONE);
        actualClassList.addClass(WEEK_TWO);
        actualClassList.addClass(WEEK_THREE);

        typicalMasterList.remove(ALICE);
        actualClassList.updateAllClassesAfterDeletion(Index.fromOneBased(1));

        assertEquals(expectedClassList, actualClassList);
    }

    @Test
    public void updateAfterDeletingStudent_nullId_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> emptyClassList.updateAllClassesAfterDeletion(null));
        assertThrows(NullPointerException.class, () -> typicalClassList.updateAllClassesAfterDeletion(null));
    }
}
