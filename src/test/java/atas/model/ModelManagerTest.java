package atas.model;

import static atas.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static atas.testutil.Assert.assertThrows;
import static atas.testutil.TypicalMemoContents.EMPTY_MEMO_CONTENT;
import static atas.testutil.TypicalMemoContents.SAMPLE_MEMO_CONTENT_ONE;
import static atas.testutil.TypicalMemoContents.SAMPLE_MEMO_CONTENT_TWO;
import static atas.testutil.TypicalMemoContents.SAMPLE_MEMO_NOTE_ONE;
import static atas.testutil.TypicalSessions.getTypicalSessionList;
import static atas.testutil.TypicalStudents.ALICE;
import static atas.testutil.TypicalStudents.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import atas.commons.core.GuiSettings;
import atas.commons.core.index.Index;
import atas.model.memo.Memo;
import atas.model.session.VersionedSessionList;
import atas.model.student.NameContainsKeywordsPredicate;
import atas.model.student.StudentList;
import atas.model.student.VersionedStudentList;
import atas.testutil.ModelManagerBuilder;
import atas.testutil.StudentListBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new StudentList(), new StudentList(modelManager.getStudentList()));
        assertEquals(new Memo(), modelManager.getMemo());
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setStudentListFilePath(Paths.get("atas/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        userPrefs.setMemoFilePath(Paths.get("atas/file/path"));
        modelManager.setUserPrefs(userPrefs);

        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setStudentListFilePath(Paths.get("new/atas/file/path"));
        userPrefs.setMemoFilePath(Paths.get("new/atas/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setStudentListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setStudentListFilePath(null));
    }

    @Test
    public void setStudentListFilePath_validPath_setsStudentListFilePath() {
        Path path = Paths.get("atas/file/path");
        modelManager.setStudentListFilePath(path);
        assertEquals(path, modelManager.getStudentListFilePath());
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInStudentList_returnsFalse() {
        assertFalse(modelManager.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInStudentList_returnsTrue() {
        modelManager.addStudent(ALICE);
        assertTrue(modelManager.hasStudent(ALICE));
    }

    @Test
    public void getFilteredStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredStudentList().remove(0));
    }

    @Test
    public void getSessionId() {
        modelManager.enterSession(Index.fromZeroBased(0));
        assertEquals(0, modelManager.getSessionId().getZeroBased());

        modelManager.enterSession(Index.fromZeroBased(2));
        assertEquals(2, modelManager.getSessionId().getZeroBased());

        modelManager.enterSession(Index.fromZeroBased(3));
        assertEquals(3, modelManager.getSessionId().getZeroBased());
    }

    @Test
    public void setMemoFilePath_validPath_setsMemoListFilePath() {
        Path path = Paths.get("atas/file/path");
        modelManager.setMemoFilePath(path);
        assertEquals(path, modelManager.getMemoFilePath());
    }

    @Test
    public void testGetMemo() {
        assertEquals(new Memo(), modelManager.getMemo());
    }

    @Test
    public void initialStateListIsSizeOne() {
        modelManager = ModelManagerBuilder.buildTypicalModelManager();
        assertEquals(1, ((VersionedStudentList) modelManager.getStudentList()).getStudentStateList().size());
        assertEquals(1, ((VersionedSessionList) modelManager.getSessionList()).getSessionStateList().size());
    }
    @Test
    public void testInitialAbilityToUndo() {
        modelManager = ModelManagerBuilder.buildTypicalModelManager();
        assertFalse(modelManager.canUndoStudentList());
        assertFalse(modelManager.canUndoSessionList());
    }
    @Test
    public void testInitialAbilityToRedo() {
        modelManager = ModelManagerBuilder.buildTypicalModelManager();
        assertFalse(modelManager.canRedoStudentList());
        assertFalse(modelManager.canRedoSessionList());
    }

    @Test
    public void stateListIncreasesSizeAfterCommit() {
        modelManager = ModelManagerBuilder.buildTypicalModelManager();
        modelManager.commit();
        assertEquals(2, ((VersionedStudentList) modelManager.getStudentList()).getStudentStateList().size());
        assertEquals(2, ((VersionedSessionList) modelManager.getSessionList()).getSessionStateList().size());
    }

    public void testGetMemoContent() {
        StudentList studentList = new StudentListBuilder().withStudent(ALICE).withStudent(BENSON).build();
        UserPrefs userPrefs = new UserPrefs();

        ModelManager modelManagerWithMemoContent = new ModelManager(getTypicalSessionList(studentList.getStudentList()),
                studentList, userPrefs, SAMPLE_MEMO_CONTENT_ONE);

        assertEquals(SAMPLE_MEMO_CONTENT_ONE, modelManagerWithMemoContent.getMemoContent());
    }

    @Test
    public void testSaveMemoContent() {
        StudentList studentList = new StudentListBuilder().withStudent(ALICE).withStudent(BENSON).build();
        UserPrefs userPrefs = new UserPrefs();

        ModelManager modelManagerWithNewMemoContent = new ModelManager(
                getTypicalSessionList(studentList.getStudentList()), studentList, userPrefs, SAMPLE_MEMO_CONTENT_ONE);

        modelManagerWithNewMemoContent.saveMemoContent(SAMPLE_MEMO_CONTENT_TWO);

        assertEquals(SAMPLE_MEMO_CONTENT_TWO, modelManagerWithNewMemoContent.getMemoContent());
    }

    @Test
    public void testAddNoteToMemo() {
        StudentList studentList = new StudentListBuilder().withStudent(ALICE).withStudent(BENSON).build();
        UserPrefs userPrefs = new UserPrefs();

        ModelManager modelManagerWithNewAddedNoteToMemo = new ModelManager(
                getTypicalSessionList(studentList.getStudentList()), studentList, userPrefs, SAMPLE_MEMO_CONTENT_ONE);

        modelManagerWithNewAddedNoteToMemo.addNoteToMemo(SAMPLE_MEMO_NOTE_ONE);

        assertEquals(SAMPLE_MEMO_CONTENT_ONE.concat("\n").concat(SAMPLE_MEMO_NOTE_ONE),
                modelManagerWithNewAddedNoteToMemo.getMemoContent());
    }

    @Test
    public void equals() {
        StudentList studentList = new StudentListBuilder().withStudent(ALICE).withStudent(BENSON).build();
        StudentList differentStudentList = new StudentList();

        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(getTypicalSessionList(
                studentList.getStudentList()), studentList, userPrefs, EMPTY_MEMO_CONTENT);
        ModelManager modelManagerCopy = new ModelManager(getTypicalSessionList(studentList.getStudentList()),
                studentList, userPrefs, EMPTY_MEMO_CONTENT);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different studentList -> returns false
        assertFalse(modelManager.equals(new ModelManager(getTypicalSessionList(studentList.getStudentList()),
                differentStudentList, userPrefs, EMPTY_MEMO_CONTENT)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        modelManager.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        assertFalse(modelManager.equals(new ModelManager(getTypicalSessionList(studentList.getStudentList()),
                studentList, userPrefs, EMPTY_MEMO_CONTENT)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setStudentListFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(getTypicalSessionList(studentList.getStudentList()),
                studentList, differentUserPrefs, EMPTY_MEMO_CONTENT)));

        // different memo -> returns false
        assertFalse(modelManager.equals(new ModelManager(getTypicalSessionList(studentList.getStudentList()),
                studentList, userPrefs, SAMPLE_MEMO_CONTENT_ONE)));
    }
}
