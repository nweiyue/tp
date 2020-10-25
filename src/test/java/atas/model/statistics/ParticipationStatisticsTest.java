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


public class ParticipationStatisticsTest {

    @Test
    public void getDataAsRatioStringTest() {

        ParticipationStatistics participationStatisticsOne = new ParticipationStatistics(0, 0);
        ParticipationStatistics participationStatisticsTwo = new ParticipationStatistics(1, 2);
        ParticipationStatistics participationStatisticsThree = new ParticipationStatistics(10, 100);
        ParticipationStatistics participationStatisticsFour = new ParticipationStatistics(2, 2);
        ParticipationStatistics participationStatisticsFive = new ParticipationStatistics(20, 20);

        String expectedStringOne = "Participation : 0/0";
        String expectedStringTwo = "Participation : 1/2";
        String expectedStringThree = "Participation : 10/100";
        String expectedStringFour = "Participation : 2/2";
        String expectedStringFive = "Participation : 20/20";

        assertEquals(participationStatisticsOne.getDataAsRatio(), expectedStringOne);
        assertEquals(participationStatisticsTwo.getDataAsRatio(), expectedStringTwo);
        assertEquals(participationStatisticsThree.getDataAsRatio(), expectedStringThree);
        assertEquals(participationStatisticsFour.getDataAsRatio(), expectedStringFour);
        assertEquals(participationStatisticsFive.getDataAsRatio(), expectedStringFive);
    }

    @Test
    public void getDataAsPercentageStringTest() {

        ParticipationStatistics participationStatisticsOne = new ParticipationStatistics(0, 0);
        ParticipationStatistics participationStatisticsTwo = new ParticipationStatistics(1, 2);
        ParticipationStatistics participationStatisticsThree = new ParticipationStatistics(11, 200);
        ParticipationStatistics participationStatisticsFour = new ParticipationStatistics(2, 2);
        ParticipationStatistics participationStatisticsFive = new ParticipationStatistics(20, 20);

        String expectedStringOne = "Participation : 0%";
        String expectedStringTwo = "Participation : 50%";
        String expectedStringThree = "Participation : 5%";
        String expectedStringFour = "Participation : 100%";
        String expectedStringFive = "Participation : 100%";

        assertEquals(participationStatisticsOne.getDataAsPercentage(), expectedStringOne);
        assertEquals(participationStatisticsTwo.getDataAsPercentage(), expectedStringTwo);
        assertEquals(participationStatisticsThree.getDataAsPercentage(), expectedStringThree);
        assertEquals(participationStatisticsFour.getDataAsPercentage(), expectedStringFour);
        assertEquals(participationStatisticsFive.getDataAsPercentage(), expectedStringFive);
    }

    @Test
    public void getSessionStatisticsTest() {

        //dummy stats
        ParticipationStatistics stats = new ParticipationStatistics();

        Session sessionWeekOne = TypicalSessions.duplicateSession(TypicalSessions.SESSION_WEEK_ONE);
        Session sessionWeekTwo = TypicalSessions.duplicateSession(TypicalSessions.SESSION_WEEK_TWO);
        Session sessionWeekThree = TypicalSessions.duplicateSession(TypicalSessions.SESSION_WEEK_THREE);

        List<Student> studentList = TypicalStudents.getTypicalStudents();

        //initialize sessions with a list of students
        sessionWeekOne.initializeSession(studentList);
        sessionWeekTwo.initializeSession(studentList);
        sessionWeekThree.initializeSession(studentList);

        //Customize and test week one
        sessionWeekOne.toggleStudentParticipation(Index.fromOneBased(1));
        sessionWeekOne.toggleStudentParticipation(Index.fromOneBased(6));
        ParticipationStatistics actualWeekOneStats = stats.getSessionStatistics(sessionWeekOne);
        assertEquals(2, actualWeekOneStats.getActual());
        assertEquals(studentList.size(), actualWeekOneStats.getExpected());

        //Customize and test week two
        sessionWeekTwo.updateParticipation(new IndexRange("1-7"));
        ParticipationStatistics actualWeekTwoStats = stats.getSessionStatistics(sessionWeekTwo);
        assertEquals(studentList.size(), actualWeekTwoStats.getActual());
        assertEquals(studentList.size(), actualWeekTwoStats.getExpected());

        //Customize and test week three
        sessionWeekThree.toggleStudentParticipation(Index.fromOneBased(1));
        sessionWeekThree.toggleStudentParticipation(Index.fromOneBased(1));
        ParticipationStatistics actualWeekThreeStats = stats.getSessionStatistics(sessionWeekThree);
        assertEquals(0, actualWeekThreeStats.getActual());
        assertEquals(studentList.size(), actualWeekThreeStats.getExpected());
    }

    @Test
    public void getStudentStatisticsTest() {

        //dummy
        ParticipationStatistics stats = new ParticipationStatistics();

        //create session lists with sessions
        List<Student> studentList = TypicalStudents.getTypicalStudents();
        SessionList sessionList = TypicalSessions.getTypicalSessionList();
        for (Session session: sessionList.getSessions()) {
            session.initializeSession(studentList);
        }

        //initialize sessions
        sessionList.updateStudentList(studentList);

        //customize first sessionList and test
        sessionList.updateStudentParticipation(TUT1.getSessionName(), new IndexRange("1-5"));
        sessionList.updateStudentParticipation(TUT2.getSessionName(), new IndexRange("2-6"));
        sessionList.updateStudentParticipation(TUT3.getSessionName(), new IndexRange("3-5"));
        sessionList.updateStudentParticipation(LAB1.getSessionName(), new IndexRange("4-5"));
        sessionList.updateStudentParticipation(LAB2.getSessionName(), new IndexRange("5-5"));

        int counter = 1;
        ParticipationStatistics[] actualStatistics = new ParticipationStatistics[studentList.size()];

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

        ParticipationStatistics stat = new ParticipationStatistics(1, 1);

        // null -> false
        ParticipationStatistics stat1 = null;
        assertFalse(stat.isSameStats(stat1));

        // different stat class -> false
        PresenceStatistics stat2 = new PresenceStatistics(1, 1);
        assertFalse(stat.isSameStats(stat2));

        // same stat class & different values-> true
        ParticipationStatistics stat3 = new ParticipationStatistics(1, 2);
        assertTrue(stat.isSameStats(stat3));

        // same stat class & same values -> true
        ParticipationStatistics stat4 = new ParticipationStatistics(1, 1);
        assertTrue(stat.isSameStats(stat4));
    }

    @Test
    public void getStatValueTest() {

        // init stat
        ParticipationStatistics stat1 = new ParticipationStatistics(0, 0);
        // full stat
        ParticipationStatistics stat2 = new ParticipationStatistics(1, 1);
        // not-full stat
        ParticipationStatistics stat3 = new ParticipationStatistics(0, 1);
        // invalid stat
        ParticipationStatistics stat4 = new ParticipationStatistics(-1, -1);

        assertEquals(StatisticalValues.STAT_NULL, stat1.getStatValue());
        assertEquals(StatisticalValues.STAT_FULL, stat2.getStatValue());
        assertEquals(StatisticalValues.STAT_NOTFULL, stat3.getStatValue());
        assertEquals(StatisticalValues.STAT_INVALID, stat4.getStatValue());
    }

    @Test
    public void getterAndSetterTest() {

        ParticipationStatistics stat1 = new ParticipationStatistics(0, 0);
        ParticipationStatistics stat2 = new ParticipationStatistics(1, 1);
        ParticipationStatistics stat3 = new ParticipationStatistics(0, 0);
        ParticipationStatistics stat4 = new ParticipationStatistics(1, 1);

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
