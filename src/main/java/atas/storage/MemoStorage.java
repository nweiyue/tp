package atas.storage;

import java.io.IOException;
import java.nio.file.Path;

import atas.commons.exceptions.DataConversionException;
import atas.model.memo.Memo;

/**
 * Represents a storage for {@link Memo}.
 */
public interface MemoStorage {

    /** Message to be shown if data file does not exist when application starts */
    String DEFAULT_MEMO_CONTENT = "Here is a free working space. Press \"ctrl + s\" or \"command + s\""
            + " for MacOS to save.";

    /**
     * Returns the file path of the data file.
     *
     * @return File path of Memo.
     */
    Path getMemoFilePath();

    /**
     * Returns the Memo content as a String.
     *
     * @return Memo content.
     * @throws DataConversionException If the data in storage is not in the expected format.
     * @throws IOException If there was any problem when reading from the storage.
     */
    String readMemo() throws DataConversionException, IOException;

    /**
     * Similar to {@link #readMemo()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @return Memo content.
     * @throws DataConversionException If the file is not in the correct format.
     * @throws IOException If there was any problem when reading from the storage.
     */
    String readMemo(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link Memo} to the storage.
     *
     * @param memo Memo to be saved.
     * @throws IOException If there was any problem when writing into the storage.
     */
    void saveMemo(Memo memo) throws IOException;

    /**
     * Similar to {@link #saveMemo(Memo)}.
     *
     * @param filePath location of the data. Cannot be null.
     * @param memo Memo to be saved.
     * @throws IOException If there was any problem when writing into the storage.
     */
    void saveMemo(Memo memo, Path filePath) throws IOException;

}
