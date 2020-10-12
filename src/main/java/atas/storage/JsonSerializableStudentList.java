package atas.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import atas.commons.exceptions.IllegalValueException;
import atas.model.ReadOnlyStudentList;
import atas.model.StudentList;
import atas.model.student.Student;

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
            if (studentList.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            studentList.addStudent(student);
        }
        return studentList;
    }

}
