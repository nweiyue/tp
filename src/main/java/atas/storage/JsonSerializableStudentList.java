package atas.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import atas.commons.exceptions.IllegalValueException;
import atas.model.student.ReadOnlyStudentList;
import atas.model.student.Student;
import atas.model.student.StudentList;
import atas.model.student.exceptions.DuplicateStudentException;

/**
 * An Immutable StudentList that is serializable to JSON format.
 */
@JsonRootName(value = "students")
class JsonSerializableStudentList {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Student list contains duplicate student(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableStudentList} with the given students.
     */
    @JsonCreator
    public JsonSerializableStudentList(@JsonProperty("students") List<JsonAdaptedStudent> students) {
        this.students.addAll(students);
    }

    /**
     * Converts a given {@code ReadOnlyStudentList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableStudentList}.
     */
    public JsonSerializableStudentList(ReadOnlyStudentList source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
    }

    /**
     * Converts this student list into the model's {@code StudentList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public StudentList toModelType() throws IllegalValueException {
        StudentList studentList = new StudentList();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            try {
                studentList.addStudent(student);
            } catch (DuplicateStudentException e) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
        }

        // Recheck once again if student list contains any duplicate students
        List<Student> listToCheck = studentList.getStudentList();
        for (int i = 0; i < listToCheck.size(); i++) {
            for (int j = 0; j < listToCheck.size(); j++) {
                if (i != j && listToCheck.get(i).hasSameField(listToCheck.get(j))) {
                    throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
                }
            }
        }

        return studentList;
    }

}
