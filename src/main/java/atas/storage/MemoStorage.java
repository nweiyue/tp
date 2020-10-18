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
    String DEFAULT_MEMO_CONTENT = "Here is a free working space. Press \"Ctrl + s\" or \"Cmd + s\" for MacOS to save.";

    /**
     * Returns the file path of the data file.
     */
    Path getMemoFilePath();

    /**
     * Returns the memo content as a String.
     * @return memo content.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    String readMemo() throws IOException, DataConversionException;

    /**
     * @see #getMemoFilePath()
     */
    String readMemo(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link Memo} to the storage.
     * @param memo Memo to be saved.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMemo(Memo memo) throws IOException;

    /**
     * @see #saveMemo(Memo)
     */
    void saveMemo(Memo memo, Path filePath) throws IOException;

}
