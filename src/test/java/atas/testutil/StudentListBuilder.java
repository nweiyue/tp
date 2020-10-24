package atas.testutil;

import atas.model.student.Student;
import atas.model.student.StudentList;

/**
 * A utility class to help with building StudentList objects.
 * Example usage: <br>
 *     {@code StudentList ab = new StudentListBuilder().withStudent("John", "Doe").build();}
 */
public class StudentListBuilder {

    private StudentList studentList;

    public StudentListBuilder() {
        studentList = new StudentList();
    }

    public StudentListBuilder(StudentList studentList) {
        this.studentList = studentList;
    }

    /**
     * Adds a new {@code Student} to the {@code StudentList} that we are building.
     */
    public StudentListBuilder withStudent(Student student) {
        studentList.addStudent(student);
        return this;
    }

    public StudentList build() {
        return studentList;
    }
}
