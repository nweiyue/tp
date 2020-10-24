package atas.storage;

import static atas.testutil.Assert.assertThrows;
import static atas.testutil.TypicalSessions.SESSION_WEEK_ONE;
import static atas.testutil.TypicalSessions.SESSION_WEEK_THREE;
import static atas.testutil.TypicalSessions.TUT1;
import static atas.testutil.TypicalSessions.getTypicalSessionList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import atas.commons.exceptions.DataConversionException;
import atas.model.session.ReadOnlySessionList;
import atas.model.session.SessionList;

public class JsonSessionListStorageTest {


    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSessionListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readSessionList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readSessionList(null));
    }

    private java.util.Optional<ReadOnlySessionList> readSessionList(String filePath) throws Exception {
        return new JsonSessionListStorage(Paths.get(filePath)).readSessionList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readSessionList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readSessionList("notJsonFormatSessionList.json"));
    }

    @Test
    public void readSessionList_invalidSessionSessionList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSessionList("invalidSessionSessionList.json"));
    }

    @Test
    public void readSessionList_invalidAndValidSessionSessionList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readSessionList("invalidAndValidSessionSessionList.json"));
    }

    @Test
    public void readAndSaveSessionList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempSessionList.json");
        SessionList original = getTypicalSessionList();
        JsonSessionListStorage jsonSessionListStorage = new JsonSessionListStorage(filePath);

        // Save in new file and read back
        jsonSessionListStorage.saveSessionList(original, filePath);
        ReadOnlySessionList readBack = jsonSessionListStorage.readSessionList(filePath).get();
        assertEquals(original, new SessionList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addSession(SESSION_WEEK_ONE);
        original.deleteSession(TUT1);
        jsonSessionListStorage.saveSessionList(original, filePath);
        readBack = jsonSessionListStorage.readSessionList(filePath).get();
        assertEquals(original, new SessionList(readBack));

        // Save and read without specifying file path
        original.addSession(SESSION_WEEK_THREE);
        jsonSessionListStorage.saveSessionList(original); // file path not specified
        readBack = jsonSessionListStorage.readSessionList().get(); // file path not specified
        assertEquals(original, new SessionList(readBack));

    }

    @Test
    public void saveSessionList_nullSessionList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSessionList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code sessionList} at the specified {@code filePath}.
     */
    private void saveSessionList(ReadOnlySessionList sessionList, String filePath) {
        try {
            new JsonSessionListStorage(Paths.get(filePath))
                    .saveSessionList(sessionList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveSessionList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveSessionList(new SessionList(), null));
    }
}
