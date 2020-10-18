package atas.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import atas.model.ReadOnlySessionList;
import atas.model.ReadOnlyStudentList;
import atas.model.StudentList;
import atas.model.session.Session;
import atas.model.session.SessionDate;
import atas.model.session.SessionList;
import atas.model.session.SessionName;
import atas.model.student.Email;
import atas.model.student.Matriculation;
import atas.model.student.Name;
import atas.model.student.Student;
import atas.model.tag.Tag;

/**
 * Contains utility methods for populating {@code StudentList} or {@code SessionList} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Matriculation("A1234567X"), new Email("alexyeoh@u.nus.edu"),
                getTagSet("friends")),
            new Student(new Name("Bernice Yu"), new Matriculation("A7654321X"), new Email("berniceyu@u.nus.edu"),
                getTagSet("colleagues", "friends")),
            new Student(new Name("Charlotte Oliveiro"), new Matriculation("A0962345D"),
                new Email("charlotte@u.nus.edu"), getTagSet("neighbours"))
        };
    }

    public static ReadOnlyStudentList getSampleStudentList() {
        StudentList sampleAb = new StudentList();
        for (Student sampleStudent : getSampleStudents()) {
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

    public static Session[] getSampleSessions() {
        return new Session[] {
            new Session(new SessionName("Tutorial 1"), new SessionDate("12/8/2020")),
            new Session(new SessionName("Tutorial 2"), new SessionDate("19/8/2020")),
            new Session(new SessionName("Tutorial 3"), new SessionDate("26/8/2020"))
        };
    }

    public static ReadOnlySessionList getSampleSessionList() {
        SessionList sampleSl = new SessionList();
        sampleSl.updateStudentList(getSampleStudentList().getStudentList());
        for (Session sampleSession : getSampleSessions()) {
            sampleSl.addSession(sampleSession);
        }
        return sampleSl;
    }

}
