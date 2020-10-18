package atas.storage;

import static atas.testutil.TypicalMemos.SAMPLE_MEMO_ONE;
import static atas.testutil.TypicalStudents.getTypicalStudentList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import atas.commons.core.GuiSettings;
import atas.model.ReadOnlyStudentList;
import atas.model.StudentList;
import atas.model.UserPrefs;
import atas.model.memo.Memo;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonSessionListStorage sessionListStorage = new JsonSessionListStorage(getTempFilePath("sl"));
        JsonAtasStorage studentListStorage = new JsonAtasStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        TxtMemoStorage memoStorage = new TxtMemoStorage(getTempFilePath("memo"));
        storageManager = new StorageManager(sessionListStorage, studentListStorage, userPrefsStorage, memoStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void studentListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonStudentListStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonStudentListStorageTest} class.
         */
        StudentList original = getTypicalStudentList();
        storageManager.saveStudentList(original);
        ReadOnlyStudentList retrieved = storageManager.readStudentList().get();
        assertEquals(original, new StudentList(retrieved));
    }

    @Test
    public void getStudentListFilePath() {
        assertNotNull(storageManager.getStudentListFilePath());
    }

    @Test
    public void testSaveMemo() throws Exception {
        Memo original = SAMPLE_MEMO_ONE;
        storageManager.saveMemo(original);
        String retrieved = storageManager.readMemo();
        assertEquals(original, new Memo(retrieved));
    }

    @Test
    public void getMemoFilePath() {
        assertNotNull(storageManager.getMemoFilePath());
    }

}
