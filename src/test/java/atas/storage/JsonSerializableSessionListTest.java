package atas.storage;

import static atas.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import atas.commons.exceptions.IllegalValueException;
import atas.commons.util.JsonUtil;
import atas.model.session.SessionList;
import atas.testutil.TypicalSessions;

public class JsonSerializableSessionListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableSessionListTest");
    private static final Path TYPICAL_SESSIONS_FILE = TEST_DATA_FOLDER.resolve("typicalSessionSessionList.json");
    private static final Path INVALID_SESSION_FILE = TEST_DATA_FOLDER.resolve("invalidSessionSessionList.json");
    private static final Path DUPLICATE_SESSION_FILE = TEST_DATA_FOLDER.resolve("duplicateSessionSessionList.json");

    @Test
    public void toModelType_typicalSessionFile_success() throws Exception {
        JsonSerializableSessionList dataFromFile = JsonUtil.readJsonFile(TYPICAL_SESSIONS_FILE,
                JsonSerializableSessionList.class).get();
        SessionList sessionListFromFile = dataFromFile.toModelType();
        SessionList typicalSessionsSessionList = TypicalSessions.getTypicalSessionList();
        assertEquals(sessionListFromFile, typicalSessionsSessionList);
    }

    @Test
    public void toModelType_invalidSessionFile_throwsIllegalValueException() throws Exception {
        JsonSerializableSessionList dataFromFile = JsonUtil.readJsonFile(INVALID_SESSION_FILE,
                JsonSerializableSessionList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateSessions_throwsIllegalValueException() throws Exception {
        JsonSerializableSessionList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_SESSION_FILE,
                JsonSerializableSessionList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableSessionList.MESSAGE_DUPLICATE_SESSION,
                dataFromFile::toModelType);
    }

}
