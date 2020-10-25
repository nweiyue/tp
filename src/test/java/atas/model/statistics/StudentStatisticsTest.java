package atas.model.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.model.student.Student;
import atas.testutil.RandomStatistics;
import atas.testutil.TypicalStudents;


public class StudentStatisticsTest {

    @Test
    public void addStatisticsTest() {

        StudentStatistics stats1 = new StudentStatistics();
        StudentStatistics stats2 = new StudentStatistics();

        ParticipationStatistics partStat = new ParticipationStatistics();
        PresenceStatistics presStat = new PresenceStatistics();
        ParticipationStatistics partStatDup = new ParticipationStatistics();

        // single param
        stats1.addStatistics(partStat);
        stats1.addStatistics(presStat);
        stats1.addStatistics(partStatDup);


        assertTrue(stats1.getStats().contains(partStat));
        assertTrue(stats1.getStats().contains(presStat));
        assertTrue(stats1.getStats().contains(partStatDup));

        // multi param
        stats2.addStatistics(partStat, presStat);

        assertTrue(stats1.getStats().contains(partStat));
        assertTrue(stats1.getStats().contains(presStat));
    }

    @Test
    public void replaceStatisticsTest() {

        StudentStatistics stats1 = new StudentStatistics(0);
        StudentStatistics stats2 = new StudentStatistics(0);

        ParticipationStatistics partStat = new ParticipationStatistics();
        PresenceStatistics presStat = new PresenceStatistics();

        stats1.addStatistics(partStat, presStat);

        // test variables
        ParticipationStatistics partSingleParamTest = null;
        PresenceStatistics presSingleParamTest = null;

        // single param
        ParticipationStatistics partStatNew = new ParticipationStatistics(1, 1);
        PresenceStatistics presStatNew = new PresenceStatistics(1, 1);
        stats1.replaceStatistics(partStatNew);
        stats1.replaceStatistics(presStatNew);

        for (Statistics statistics: stats1.getStats()) {
            if (statistics instanceof ParticipationStatistics) {
                partSingleParamTest = (ParticipationStatistics) statistics;
            } else if (statistics instanceof PresenceStatistics) {
                presSingleParamTest = (PresenceStatistics) statistics;
            }
        }

        assert partSingleParamTest != null;
        assertEquals(1, partSingleParamTest.getActual());
        assertEquals(1, partSingleParamTest.getExpected());
        assert presSingleParamTest != null;
        assertEquals(1, presSingleParamTest.getActual());
        assertEquals(1, presSingleParamTest.getExpected());

        // multi param
        stats2.replaceStatistics(partStatNew, presStatNew);

        partSingleParamTest = null;
        presSingleParamTest = null;

        for (Statistics statistics: stats2.getStats()) {
            if (statistics instanceof ParticipationStatistics) {
                partSingleParamTest = (ParticipationStatistics) statistics;
            } else if (statistics instanceof PresenceStatistics) {
                presSingleParamTest = (PresenceStatistics) statistics;
            }
        }

        assert partSingleParamTest != null;
        assertEquals(1, partSingleParamTest.getActual());
        assertEquals(1, partSingleParamTest.getExpected());
        assert presSingleParamTest != null;
        assertEquals(1, presSingleParamTest.getActual());
        assertEquals(1, presSingleParamTest.getExpected());
    }

    @Test
    public void getParticipationStatisticsTest() {

        Student student = TypicalStudents.duplicateStudent(TypicalStudents.ALICE);

        ParticipationStatistics stat = (ParticipationStatistics) student.getStats().getParticipationStatistics();

        assertEquals(0, stat.getActual());
        assertEquals(0, stat.getExpected());
    }

    @Test
    public void getPresenceStatisticsTest() {

        Student student = TypicalStudents.duplicateStudent(TypicalStudents.ALICE);

        PresenceStatistics stat = (PresenceStatistics) student.getStats().getPresenceStatistics();

        assertEquals(0, stat.getActual());
        assertEquals(0, stat.getExpected());
    }

    @Test
    public void containsTest() {

        StudentStatistics stat = new StudentStatistics(7);

        // participation stats -> true
        ParticipationStatistics participationStatistics = new ParticipationStatistics();
        assertTrue(stat.contains(participationStatistics));

        // presence stats -> true
        PresenceStatistics presenceStatistics = new PresenceStatistics();
        assertTrue(stat.contains(presenceStatistics));

        // ? stats -> false
        RandomStatistics randomStatistics = new RandomStatistics();
        assertFalse(stat.contains(randomStatistics));
    }

    @Test
    public void equalsTest() {

        StudentStatistics stat = new StudentStatistics(7);

        //null -> false
        StudentStatistics nullStat = null;
        assertFalse(stat.equals(nullStat));

        //different class -> false
        SessionStatistics diffStat = new SessionStatistics();
        assertFalse(stat.equals(diffStat));

        //same stats -> true
        StudentStatistics sameStat = new StudentStatistics();
        assertTrue(stat.equals(sameStat));

        //same type of stats & different stat values -> true
        StudentStatistics sameStatDiffVal = new StudentStatistics();
        sameStatDiffVal.replaceStatistics(new ParticipationStatistics(1, 1));
        assertTrue(stat.equals(sameStatDiffVal));

        //different stat class included -> false
        StudentStatistics diffStatClassStat = new StudentStatistics();
        diffStatClassStat.addStatistics(new RandomStatistics());
        assertFalse(stat.equals(diffStatClassStat));
    }
}
