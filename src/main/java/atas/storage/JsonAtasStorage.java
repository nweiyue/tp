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
import atas.model.student.ReadOnlyStudentList;

/**
 * A class to access Atas data stored as a json file on the hard disk.
 */
public class JsonAtasStorage implements AtasStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAtasStorage.class);

    private Path filePath;

    public JsonAtasStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getStudentListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyStudentList> readStudentList() throws DataConversionException {
        return readStudentList(filePath);
    }

    /**
     * Similar to {@link #readStudentList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyStudentList> readStudentList(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableStudentList> jsonStudentList = JsonUtil.readJsonFile(
                filePath, JsonSerializableStudentList.class);
        if (!jsonStudentList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonStudentList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveStudentList(ReadOnlyStudentList studentList) throws IOException {
        saveStudentList(studentList, filePath);
    }

    /**
     * Similar to {@link #saveStudentList(ReadOnlyStudentList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveStudentList(ReadOnlyStudentList studentList, Path filePath) throws IOException {
        requireNonNull(studentList);
        requireNonNull(filePath);

        createIfMissing(filePath);
        saveJsonFile(new JsonSerializableStudentList(studentList), filePath);
    }

}
