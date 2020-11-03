package atas.storage;

import static atas.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import atas.model.memo.Memo;

public class TxtMemoStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "TxtMemoStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readMemo_nullFilePath_throwsNullPointerException() {
        TxtMemoStorage memoStorage = new TxtMemoStorage(testFolder);
        assertThrows(NullPointerException.class, () -> readMemo(null));
    }

    private String readMemo(String filePath) throws Exception {
        return new TxtMemoStorage(Paths.get(filePath)).readMemo(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertTrue(readMemo("NonExistentFile.txt").isEmpty());
    }

    @Test
    public void saveMemo_nullMemo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMemo(null, "SomeFile.txt"));
    }

    /**
     * Saves {@code memo} at the specified {@code filePath}.
     */
    private void saveMemo(Memo memo, String filePath) {
        try {
            new TxtMemoStorage(Paths.get(filePath))
                    .saveMemo(memo, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveMemo_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMemo(new Memo(), null));
    }
}
