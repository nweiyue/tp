package atas.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import atas.commons.exceptions.DataConversionException;
import atas.model.ReadOnlySessionList;
import atas.model.ReadOnlyStudentList;
import atas.model.ReadOnlyUserPrefs;
import atas.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends SessionListStorage, AtasStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyStudentList> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyStudentList addressBook) throws IOException;

    @Override
    Path getSessionListFilePath();

    @Override
    Optional<ReadOnlySessionList> readSessionList() throws DataConversionException, IOException;

    @Override
    void saveSessionList(ReadOnlySessionList sessionList) throws IOException;

}
