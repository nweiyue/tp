package atas.storage;

import static atas.commons.util.FileUtil.createIfMissing;
import static atas.commons.util.JsonUtil.saveJsonFile;
import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import atas.commons.core.LogsCenter;
import atas.commons.exceptions.DataConversionException;
import atas.commons.exceptions.IllegalValueException;
import atas.commons.util.JsonUtil;
import atas.model.session.ReadOnlySessionList;

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

        createIfMissing(filePath);
        saveJsonFile(new JsonSerializableSessionList(sessionList), filePath);
    }
}
