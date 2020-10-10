package atas.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import atas.commons.core.LogsCenter;
import atas.commons.exceptions.DataConversionException;
import atas.model.ReadOnlyAddressBook;
import atas.model.ReadOnlySessionList;
import atas.model.ReadOnlyUserPrefs;
import atas.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private SessionListStorage sessionListStorage;
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code SessionListStorage}, {@code AddressBookStorage}
     * and {@code UserPrefStorage}.
     */
    public StorageManager(SessionListStorage sessionListStorage, AddressBookStorage addressBookStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.sessionListStorage = sessionListStorage;
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ SessionList methods ==============================

    @Override
    public Path getSessionListFilePath() {
        return sessionListStorage.getSessionListFilePath();
    }

    @Override
    public Optional<ReadOnlySessionList> readSessionList() throws DataConversionException, IOException {
        return readSessionList(sessionListStorage.getSessionListFilePath());
    }

    @Override
    public Optional<ReadOnlySessionList> readSessionList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return sessionListStorage.readSessionList(filePath);
    }

    @Override
    public void saveSessionList(ReadOnlySessionList sessionList) throws IOException {
        saveSessionList(sessionList, sessionListStorage.getSessionListFilePath());
    }

    @Override
    public void saveSessionList(ReadOnlySessionList sessionList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        sessionListStorage.saveSessionList(sessionList, filePath);
    }

}
