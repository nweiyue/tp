package atas.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import atas.commons.core.LogsCenter;
import atas.commons.exceptions.DataConversionException;
import atas.model.ReadOnlySessionList;
import atas.model.ReadOnlyStudentList;
import atas.model.ReadOnlyUserPrefs;
import atas.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private SessionListStorage sessionListStorage;
    private AtasStorage atasStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code SessionListStorage}, {@code AddressBookStorage}
     * and {@code UserPrefStorage}.
     */
    public StorageManager(SessionListStorage sessionListStorage, AtasStorage atasStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.sessionListStorage = sessionListStorage;
        this.atasStorage = atasStorage;
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
        return atasStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyStudentList> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(atasStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyStudentList> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return atasStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyStudentList addressBook) throws IOException {
        saveAddressBook(addressBook, atasStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyStudentList addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        atasStorage.saveAddressBook(addressBook, filePath);
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
