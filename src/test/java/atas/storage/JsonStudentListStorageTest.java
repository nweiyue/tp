package atas.storage;

import static atas.testutil.Assert.assertThrows;
import static atas.testutil.TypicalStudents.ALICE;
import static atas.testutil.TypicalStudents.HOON;
import static atas.testutil.TypicalStudents.IDA;
import static atas.testutil.TypicalStudents.getTypicalStudentList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import atas.commons.exceptions.DataConversionException;
import atas.model.student.ReadOnlyStudentList;
import atas.model.student.StudentList;

public class JsonStudentListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAtasStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readStudentList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readStudentList(null));
    }

    private java.util.Optional<ReadOnlyStudentList> readStudentList(String filePath) throws Exception {
        return new JsonAtasStorage(Paths.get(filePath)).readStudentList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readStudentList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readStudentList("notJsonFormatStudentList.json"));
    }

    @Test
    public void readStudentList_invalidStudentInStudentList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readStudentList("invalidStudentInStudentList.json"));
    }

    @Test
    public void readStudentList_invalidAndValidStudentInStudentList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readStudentList("invalidAndValidStudentInStudentList.json"));
    }

    @Test
    public void readAndSaveStudentList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempStudentList.json");
        StudentList original = getTypicalStudentList();
        JsonAtasStorage jsonStudentListStorage = new JsonAtasStorage(filePath);

        // Save in new file and read back
        jsonStudentListStorage.saveStudentList(original, filePath);
        ReadOnlyStudentList readBack = jsonStudentListStorage.readStudentList(filePath).get();
        assertEquals(original, new StudentList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonStudentListStorage.saveStudentList(original, filePath);
        readBack = jsonStudentListStorage.readStudentList(filePath).get();
        assertEquals(original, new StudentList(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonStudentListStorage.saveStudentList(original); // file path not specified
        readBack = jsonStudentListStorage.readStudentList().get(); // file path not specified
        assertEquals(original, new StudentList(readBack));

    }

    @Test
    public void saveStudentList_nullStudentList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveStudentList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code studentList} at the specified {@code filePath}.
     */
    private void saveStudentList(ReadOnlyStudentList studentList, String filePath) {
        try {
            new JsonAtasStorage(Paths.get(filePath))
                    .saveStudentList(studentList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveStudentList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveStudentList(new StudentList(), null));
    }
}
