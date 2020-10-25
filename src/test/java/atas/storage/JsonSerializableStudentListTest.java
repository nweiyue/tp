package atas.storage;

import static atas.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import atas.commons.exceptions.IllegalValueException;
import atas.commons.util.JsonUtil;
import atas.model.student.StudentList;
import atas.testutil.TypicalStudents;

public class JsonSerializableStudentListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableStudentListTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsStudentList.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentInStudentList.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentsInStudentList.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableStudentList dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableStudentList.class).get();
        StudentList studentListFromFile = dataFromFile.toModelType();
        StudentList typicalStudentsStudentList = TypicalStudents.getTypicalStudentList();
        assertEquals(studentListFromFile, typicalStudentsStudentList);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableStudentList dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableStudentList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableStudentList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableStudentList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableStudentList.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

}
