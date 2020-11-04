package atas.testutil;

import static atas.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static atas.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static atas.logic.commands.CommandTestUtil.VALID_MATRICULATION_AMY;
import static atas.logic.commands.CommandTestUtil.VALID_MATRICULATION_BOB;
import static atas.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static atas.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static atas.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static atas.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import atas.model.student.Student;
import atas.model.student.StudentList;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final String UNUSED_VALID_MATRICULATION = "A9999999A";

    public static final String UNUSED_VALID_EMAIL = "nussu@u.nus.edu";

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withEmail("alice@u.nus.edu").withMatriculation("A2395823W").withTags("friends").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withEmail("johnd@u.nus.edu").withMatriculation("A8473948R").withTags("owesMoney", "friends").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withMatriculation("A2345893J")
            .withEmail("heinz@u.nus.edu").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withMatriculation("A2837453G")
            .withEmail("cornelia@u.nus.edu").withTags("friends").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withMatriculation("A2384576V")
            .withEmail("werner@u.nus.edu").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withMatriculation("A7984295F")
            .withEmail("lydia@u.nus.edu").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withMatriculation("A4958234S")
            .withEmail("anna@u.nus.edu").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withMatriculation("A5720936H")
            .withEmail("stefan@u.nus.edu").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withMatriculation("A6398734N")
            .withEmail("hans@u.nus.edu").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY)
            .withMatriculation(VALID_MATRICULATION_AMY).withEmail(VALID_EMAIL_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB)
            .withMatriculation(VALID_MATRICULATION_BOB).withEmail(VALID_EMAIL_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code StudentList} with all the typical students.
     */
    public static StudentList getTypicalStudentList() {
        StudentList ab = new StudentList();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        List<Student> result = new ArrayList<>();
        result.addAll(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
        return result;
    }

    public static List<Student> getTypicalStudentsMinusAlice() {
        return new ArrayList<>(Arrays.asList(BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }

    public static Student duplicateStudent(Student student) {
        return new Student(student.getName(), student.getMatriculation(), student.getEmail(), student.getTags());
    }
}
