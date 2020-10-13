package atas.logic.commands.studentlist;

import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static atas.testutil.TypicalStudents.CARL;
import static atas.testutil.TypicalStudents.ELLE;
import static atas.testutil.TypicalStudents.FIONA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import atas.commons.core.Messages;
import atas.model.Model;
import atas.model.student.NameContainsKeywordsPredicate;
import atas.testutil.ModelManagerBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindStudentsCommand}.
 */

public class FindStudentsCommandTest {
    private Model model = ModelManagerBuilder.buildTypicalModelManager();
    private Model expectedModel = ModelManagerBuilder.buildTypicalModelManager();

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        FindStudentsCommand findFirstStudentsCommand = new FindStudentsCommand(firstPredicate);
        FindStudentsCommand findSecondStudentsCommand = new FindStudentsCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstStudentsCommand.equals(findFirstStudentsCommand));

        // same values -> returns true

        FindStudentsCommand findFirstStudentsCommandCopy = new FindStudentsCommand(firstPredicate);
        assertTrue(findFirstStudentsCommand.equals(findFirstStudentsCommandCopy));

        // different types -> returns false
        assertFalse(findFirstStudentsCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstStudentsCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstStudentsCommand.equals(findSecondStudentsCommand));

    }

    @Test
    public void execute_zeroKeywords_noStudentFound() {
        String expectedMessage = String.format(Messages.getStudentListedMessage(0), 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindStudentsCommand findStudentsCommand = new FindStudentsCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(findStudentsCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleKeywords_multipleStudentsFound() {
        String expectedMessage = String.format(Messages.getStudentListedMessage(3), 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindStudentsCommand findStudentsCommand = new FindStudentsCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(findStudentsCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
