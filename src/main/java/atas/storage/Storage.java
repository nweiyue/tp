package atas.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import atas.commons.exceptions.DataConversionException;
import atas.model.ReadOnlyAddressBook;
import atas.model.ReadOnlySessionList;
import atas.model.ReadOnlyUserPrefs;
import atas.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends SessionListStorage, AddressBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    @Override
    Path getSessionListFilePath();

    @Override
    Optional<ReadOnlySessionList> readSessionList() throws DataConversionException, IOException;

    @Override
    void saveSessionList(ReadOnlySessionList sessionList) throws IOException;

}
