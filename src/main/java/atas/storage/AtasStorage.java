package atas.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import atas.commons.exceptions.DataConversionException;
import atas.model.student.ReadOnlyStudentList;
import atas.model.student.StudentList;

/**
 * Represents a storage for {@link StudentList}.
 */
public interface AtasStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getStudentListFilePath();

    /**
     * Returns StudentList data as a {@link ReadOnlyStudentList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyStudentList> readStudentList() throws DataConversionException, IOException;

    /**
     * @see #getStudentListFilePath()
     */
    Optional<ReadOnlyStudentList> readStudentList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyStudentList} to the storage.
     * @param studentList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveStudentList(ReadOnlyStudentList studentList) throws IOException;

    /**
     * @see #saveStudentList(ReadOnlyStudentList)
     */
    void saveStudentList(ReadOnlyStudentList studentList, Path filePath) throws IOException;

}
