package atas.model.util;

import static atas.model.util.SampleDataUtil.getSampleSessions;
import static atas.model.util.SampleDataUtil.getSampleStudents;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import atas.model.session.Session;
import atas.model.student.Student;

public class SampleDataUtilTest {

    @Test
    public void getSampleStudentsTest() {
        Student[] sampleStudents = getSampleStudents();
        assert(sampleStudents.length == 5);

        // check names
        assertEquals(sampleStudents[0].getName().fullName, "Alvin Lee");
        assertEquals(sampleStudents[1].getName().fullName, "Bobby Bob");
        assertEquals(sampleStudents[2].getName().fullName, "Clement Koh");
        assertEquals(sampleStudents[3].getName().fullName, "Dianne Loh");
        assertEquals(sampleStudents[4].getName().fullName, "Elon Gates");

        // check email
        assertEquals(sampleStudents[0].getMatriculation().value, "A1234567X");
        assertEquals(sampleStudents[1].getMatriculation().value, "A7654321X");
        assertEquals(sampleStudents[2].getMatriculation().value, "A0962345D");
        assertEquals(sampleStudents[3].getMatriculation().value, "A0155555C");
        assertEquals(sampleStudents[4].getMatriculation().value, "A0123123X");

        // check matriculation
        assertEquals(sampleStudents[0].getEmail().value, "alvinlee@u.nus.edu");
        assertEquals(sampleStudents[1].getEmail().value, "bobbob@u.nus.edu");
        assertEquals(sampleStudents[2].getEmail().value, "fruitman@u.nus.edu");
        assertEquals(sampleStudents[3].getEmail().value, "diannecap5@u.nus.edu");
        assertEquals(sampleStudents[4].getEmail().value, "tesloft@u.nus.edu");
    }


    @Test
    public void getSampleSessionsTest() {
        Session[] sessions = getSampleSessions();
        assert(sessions.length == 5);

        // check for unique names
        assertEquals(sessions[0].getSessionName().value, "Tutorial 1");
        assertEquals(sessions[1].getSessionName().value, "Tutorial 2");
        assertEquals(sessions[2].getSessionName().value, "Tutorial 3");
        assertEquals(sessions[3].getSessionName().value, "Tutorial 4");
        assertEquals(sessions[4].getSessionName().value, "Tutorial 5");
    }

}
