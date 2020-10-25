package atas.model.statistics;

import static atas.testutil.TypicalSessions.LAB1;
import static atas.testutil.TypicalSessions.LAB2;
import static atas.testutil.TypicalSessions.TUT1;
import static atas.testutil.TypicalSessions.TUT2;
import static atas.testutil.TypicalSessions.TUT3;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import atas.commons.core.index.Index;
import atas.commons.core.statistics.StatisticalValues;
import atas.model.session.IndexRange;
import atas.model.session.Session;
import atas.model.session.SessionList;
import atas.model.student.Student;
import atas.testutil.TypicalSessions;
import atas.testutil.TypicalStudents;


public class PresenceStatisticsTest {

    @Test
    public void getDataAsRatioStringTest() {

        PresenceStatistics presenceStatisticsOne = new PresenceStatistics(0, 0);
        PresenceStatistics presenceStatisticsTwo = new PresenceStatistics(1, 2);
        PresenceStatistics presenceStatisticsThree = new PresenceStatistics(10, 100);
        PresenceStatistics presenceStatisticsFour = new PresenceStatistics(2, 2);
        PresenceStatistics presenceStatisticsFive = new PresenceStatistics(20, 20);

        String expectedStringOne = "Presence : 0/0";
        String expectedStringTwo = "Presence : 1/2";
        String expectedStringThree = "Presence : 10/100";
        String expectedStringFour = "Presence : 2/2";
        String expectedStringFive = "Presence : 20/20";

        assertEquals(presenceStatisticsOne.getDataAsRatio(), expectedStringOne);
        assertEquals(presenceStatisticsTwo.getDataAsRatio(), expectedStringTwo);
        assertEquals(presenceStatisticsThree.getDataAsRatio(), expectedStringThree);
        assertEquals(presenceStatisticsFour.getDataAsRatio(), expectedStringFour);
        assertEquals(presenceStatisticsFive.getDataAsRatio(), expectedStringFive);
    }

    @Test
    public void getDataAsPercentageStringTest() {

        PresenceStatistics presenceStatisticsOne = new PresenceStatistics(0, 0);
        PresenceStatistics presenceStatisticsTwo = new PresenceStatistics(1, 2);
        PresenceStatistics presenceStatisticsThree = new PresenceStatistics(11, 200);
        PresenceStatistics presenceStatisticsFour = new PresenceStatistics(2, 2);
        PresenceStatistics presenceStatisticsFive = new PresenceStatistics(20, 20);

        String expectedStringOne = "Presence : 0%";
        String expectedStringTwo = "Presence : 50%";
        String expectedStringThree = "Presence : 5%";
        String expectedStringFour = "Presence : 100%";
        String expectedStringFive = "Presence : 100%";

        assertEquals(presenceStatisticsOne.getDataAsPercentage(), expectedStringOne);
        assertEquals(presenceStatisticsTwo.getDataAsPercentage(), expectedStringTwo);
        assertEquals(presenceStatisticsThree.getDataAsPercentage(), expectedStringThree);
        assertEquals(presenceStatisticsFour.getDataAsPercentage(), expectedStringFour);
        assertEquals(presenceStatisticsFive.getDataAsPercentage(), expectedStringFive);
    }

    @Test
    public void getSessionStatisticsTest() {

        //dummy stats
        PresenceStatistics stats = new PresenceStatistics();

        Session sessionWeekOne = TypicalSessions.duplicateSession(TypicalSessions.SESSION_WEEK_ONE);
        Session sessionWeekTwo = TypicalSessions.duplicateSession(TypicalSessions.SESSION_WEEK_TWO);
        Session sessionWeekThree = TypicalSessions.duplicateSession(TypicalSessions.SESSION_WEEK_THREE);

        List<Student> studentList = TypicalStudents.getTypicalStudents();

        //initialize sessions with a list of students
        sessionWeekOne.initializeSession(studentList);
        sessionWeekTwo.initializeSession(studentList);
        sessionWeekThree.initializeSession(studentList);

        //Customize and test week one
        sessionWeekOne.toggleStudentPresence(Index.fromOneBased(1));
        sessionWeekOne.toggleStudentPresence(Index.fromOneBased(6));
        PresenceStatistics actualWeekOneStats = stats.getSessionStatistics(sessionWeekOne);
        assertEquals(2, actualWeekOneStats.getActual());
        assertEquals(studentList.size(), actualWeekOneStats.getExpected());

        //Customize and test week two
        sessionWeekTwo.updatePresence(new IndexRange("1-7"));
        PresenceStatistics actualWeekTwoStats = stats.getSessionStatistics(sessionWeekTwo);
        assertEquals(studentList.size(), actualWeekTwoStats.getActual());
        assertEquals(studentList.size(), actualWeekTwoStats.getExpected());

        //Customize and test week three
        sessionWeekThree.toggleStudentPresence(Index.fromOneBased(1));
        sessionWeekThree.toggleStudentPresence(Index.fromOneBased(1));
        PresenceStatistics actualWeekThreeStats = stats.getSessionStatistics(sessionWeekThree);
        assertEquals(0, actualWeekThreeStats.getActual());
        assertEquals(studentList.size(), actualWeekThreeStats.getExpected());
    }

    @Test
    public void getStudentStatisticsTest() {

        //dummy
        PresenceStatistics stats = new PresenceStatistics();

        //create session lists with sessions
        List<Student> studentList = TypicalStudents.getTypicalStudents();
        SessionList sessionList = TypicalSessions.getTypicalSessionList();
        for (Session session: sessionList.getSessions()) {
            session.initializeSession(studentList);
        }

        //initialize sessions
        sessionList.updateStudentList(studentList);

        //customize first sessionList and test
        sessionList.updateStudentPresence(TUT1.getSessionName(), new IndexRange("1-5"));
        sessionList.updateStudentPresence(TUT2.getSessionName(), new IndexRange("2-6"));
        sessionList.updateStudentPresence(TUT3.getSessionName(), new IndexRange("3-5"));
        sessionList.updateStudentPresence(LAB1.getSessionName(), new IndexRange("4-5"));
        sessionList.updateStudentPresence(LAB2.getSessionName(), new IndexRange("5-5"));

        int counter = 1;
        PresenceStatistics[] actualStatistics = new PresenceStatistics[studentList.size()];

        for (int i = 0; i < actualStatistics.length; i++) {
            actualStatistics[i] = stats.getStudentStatistics(sessionList, Index.fromOneBased(counter));
            assertEquals(sessionList.getSessions().size(), actualStatistics[i].getExpected());
            counter++;
        }

        assertEquals(1, actualStatistics[0].getActual());
        assertEquals(2, actualStatistics[1].getActual());
        assertEquals(3, actualStatistics[2].getActual());
        assertEquals(4, actualStatistics[3].getActual());
        assertEquals(5, actualStatistics[4].getActual());
        assertEquals(1, actualStatistics[5].getActual());
        assertEquals(0, actualStatistics[6].getActual());
    }

    @Test
    public void isSameStatTest() {

        PresenceStatistics stat = new PresenceStatistics(1, 1);

        // null -> false
        PresenceStatistics stat1 = null;
        assertFalse(stat.isSameStats(stat1));

        // different stat class -> false
        ParticipationStatistics stat2 = new ParticipationStatistics(1, 1);
        assertFalse(stat.isSameStats(stat2));

        // same stat class & different values-> true
        PresenceStatistics stat3 = new PresenceStatistics(1, 2);
        assertTrue(stat.isSameStats(stat3));

        // same stat class & same values -> true
        PresenceStatistics stat4 = new PresenceStatistics(1, 1);
        assertTrue(stat.isSameStats(stat4));
    }

    @Test
    public void getStatValueTest() {

        // init stat
        PresenceStatistics stat1 = new PresenceStatistics(0, 0);
        // full stat
        PresenceStatistics stat2 = new PresenceStatistics(1, 1);
        // not-full stat
        PresenceStatistics stat3 = new PresenceStatistics(0, 1);
        // invalid stat
        PresenceStatistics stat4 = new PresenceStatistics(-1, -1);

        assertEquals(StatisticalValues.STAT_NULL, stat1.getStatValue());
        assertEquals(StatisticalValues.STAT_FULL, stat2.getStatValue());
        assertEquals(StatisticalValues.STAT_NOTFULL, stat3.getStatValue());
        assertEquals(StatisticalValues.STAT_INVALID, stat4.getStatValue());
    }

    @Test
    public void getterAndSetterTest() {

        PresenceStatistics stat1 = new PresenceStatistics(0, 0);
        PresenceStatistics stat2 = new PresenceStatistics(1, 1);
        PresenceStatistics stat3 = new PresenceStatistics(0, 0);
        PresenceStatistics stat4 = new PresenceStatistics(1, 1);

        // test getters
        assertEquals(0, stat1.getActual());
        assertEquals(0, stat1.getExpected());
        assertEquals(1, stat2.getActual());
        assertEquals(1, stat2.getExpected());

        // test setters
        stat3 = stat3.setActual(3);
        assertEquals(3, stat3.getActual());
        stat3 = stat3.setExpected(3);
        assertEquals(3, stat3.getExpected());
        stat4 = stat4.setActual(1);
        assertEquals(1, stat4.getActual());
        stat4 = stat4.setExpected(1);
        assertEquals(1, stat4.getExpected());
    }
}
