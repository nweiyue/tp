package atas.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import atas.commons.exceptions.DataConversionException;
import atas.model.ReadOnlyUserPrefs;
import atas.model.UserPrefs;
import atas.model.memo.Memo;
import atas.model.session.ReadOnlySessionList;
import atas.model.student.ReadOnlyStudentList;

/**
 * API of the Storage component
 */
public interface Storage extends SessionListStorage, AtasStorage, UserPrefsStorage, MemoStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getStudentListFilePath();

    @Override
    Optional<ReadOnlyStudentList> readStudentList() throws DataConversionException, IOException;

    @Override
    void saveStudentList(ReadOnlyStudentList studentList) throws IOException;

    @Override
    Path getSessionListFilePath();

    @Override
    Optional<ReadOnlySessionList> readSessionList() throws DataConversionException, IOException;

    @Override
    void saveSessionList(ReadOnlySessionList sessionList) throws IOException;

    @Override
    Path getMemoFilePath();

    @Override
    String readMemo() throws IOException, DataConversionException;

    @Override
    void saveMemo(Memo memo) throws IOException;

}
