package atas.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import atas.commons.core.LogsCenter;
import atas.commons.exceptions.DataConversionException;
import atas.model.ReadOnlyUserPrefs;
import atas.model.UserPrefs;
import atas.model.memo.Memo;
import atas.model.session.ReadOnlySessionList;
import atas.model.student.ReadOnlyStudentList;


/**
 * Manages storage of Atas data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private SessionListStorage sessionListStorage;
    private AtasStorage atasStorage;
    private UserPrefsStorage userPrefsStorage;
    private MemoStorage memoStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code SessionListStorage}, {@code StudentListStorage},
     * {@code UserPrefStorage} and {@code MemoStorage}.
     */
    public StorageManager(SessionListStorage sessionListStorage, AtasStorage atasStorage,
                          UserPrefsStorage userPrefsStorage, MemoStorage memoStorage) {
        super();
        this.sessionListStorage = sessionListStorage;
        this.atasStorage = atasStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.memoStorage = memoStorage;
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


    // ================ StudentList methods ==============================

    @Override
    public Path getStudentListFilePath() {
        return atasStorage.getStudentListFilePath();
    }

    @Override
    public Optional<ReadOnlyStudentList> readStudentList() throws DataConversionException, IOException {
        return readStudentList(atasStorage.getStudentListFilePath());
    }

    @Override
    public Optional<ReadOnlyStudentList> readStudentList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return atasStorage.readStudentList(filePath);
    }

    @Override
    public void saveStudentList(ReadOnlyStudentList studentList) throws IOException {
        saveStudentList(studentList, atasStorage.getStudentListFilePath());
    }

    @Override
    public void saveStudentList(ReadOnlyStudentList studentList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        atasStorage.saveStudentList(studentList, filePath);
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

    // ================ Memo methods ==============================

    @Override
    public Path getMemoFilePath() {
        return memoStorage.getMemoFilePath();
    }

    @Override
    public String readMemo() throws IOException, DataConversionException {
        return readMemo(memoStorage.getMemoFilePath());
    }

    @Override
    public String readMemo(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return memoStorage.readMemo(filePath);
    }

    @Override
    public void saveMemo(Memo memo) throws IOException {
        saveMemo(memo, memoStorage.getMemoFilePath());
    }

    @Override
    public void saveMemo(Memo memo, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        memoStorage.saveMemo(memo, filePath);
    }

}
