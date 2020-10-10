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
import atas.model.ReadOnlyStudentList;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonAtasStorage implements AtasStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAtasStorage.class);

    private Path filePath;

    public JsonAtasStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAddressBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyStudentList> readAddressBook() throws DataConversionException {
        return readAddressBook(filePath);
    }

    /**
     * Similar to {@link #readAddressBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyStudentList> readAddressBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableStudentList> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableStudentList.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveAddressBook(ReadOnlyStudentList addressBook) throws IOException {
        saveAddressBook(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveAddressBook(ReadOnlyStudentList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAddressBook(ReadOnlyStudentList addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        createIfMissing(filePath);
        saveJsonFile(new JsonSerializableStudentList(addressBook), filePath);
    }

}
