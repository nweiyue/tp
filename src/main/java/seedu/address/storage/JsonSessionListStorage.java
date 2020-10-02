package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlySessionList;

public class JsonSessionListStorage implements SessionListStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonSessionListStorage.class);

    private Path filePath;

    public JsonSessionListStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getSessionListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlySessionList> readSessionList() throws DataConversionException, IOException {
        return readSessionList(filePath);
    }

    @Override
    public Optional<ReadOnlySessionList> readSessionList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableSessionList> jsonSessionList = JsonUtil.readJsonFile(
                filePath, JsonSerializableSessionList.class);
        if (jsonSessionList.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonSessionList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveSessionList(ReadOnlySessionList sessionList) throws IOException {
        saveSessionList(sessionList, filePath);
    }

    @Override
    public void saveSessionList(ReadOnlySessionList sessionList, Path filePath) throws IOException {
        requireNonNull(sessionList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableSessionList(sessionList), filePath);
    }
}
