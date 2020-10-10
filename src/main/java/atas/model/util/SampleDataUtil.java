package atas.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import atas.model.ReadOnlyStudentList;
import atas.model.StudentList;
import atas.model.student.Email;
import atas.model.student.Matriculation;
import atas.model.student.Name;
import atas.model.student.Student;
import atas.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSamplePersons() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Matriculation("A1234567X"), new Email("alexyeoh@u.nus.edu"),
                getTagSet("friends")),
            new Student(new Name("Bernice Yu"), new Matriculation("A7654321X"), new Email("berniceyu@u.nus.edu"),
                getTagSet("colleagues", "friends")),
            new Student(new Name("Charlotte Oliveiro"), new Matriculation("A0962345D"),
                new Email("charlotte@u.nus.edu"), getTagSet("neighbours"))
        };
    }

    public static ReadOnlyStudentList getSampleAddressBook() {
        StudentList sampleAb = new StudentList();
        for (Student sampleStudent : getSamplePersons()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
