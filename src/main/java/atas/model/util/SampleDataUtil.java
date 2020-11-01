package atas.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import atas.model.session.ReadOnlySessionList;
import atas.model.session.Session;
import atas.model.session.SessionDate;
import atas.model.session.SessionList;
import atas.model.session.SessionName;
import atas.model.student.Email;
import atas.model.student.Matriculation;
import atas.model.student.Name;
import atas.model.student.ReadOnlyStudentList;
import atas.model.student.Student;
import atas.model.student.StudentList;
import atas.model.tag.Tag;

/**
 * Contains utility methods for populating {@code StudentList} or {@code SessionList} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alvin Lee"), new Matriculation("A1234567X"),
                new Email("alvinlee@u.nus.edu"), getTagSet("active")),
            new Student(new Name("Bobby Bob"), new Matriculation("A7654321X"),
                new Email("bobbob@u.nus.edu"), getTagSet("active", "helpful")),
            new Student(new Name("Clement Koh"), new Matriculation("A0962345D"),
                new Email("fruitman@u.nus.edu"), getTagSet("helpful")),
            new Student(new Name("Dianne Loh"), new Matriculation("A0155555C"),
                new Email("diannecap5@u.nus.edu"), getTagSet("smart")),
            new Student(new Name("Elon Gates"), new Matriculation("A0123123X"),
                new Email("tesloft@u.nus.edu"), getTagSet())
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
            new Session(new SessionName("Tutorial 3"), new SessionDate("26/8/2020")),
            new Session(new SessionName("Tutorial 4"), new SessionDate("2/9/2020")),
            new Session(new SessionName("Tutorial 5"), new SessionDate("9/9/2020"))
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
