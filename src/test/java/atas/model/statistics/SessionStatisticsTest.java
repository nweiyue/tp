package atas.model.statistics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.model.session.IndexRange;
import atas.model.session.Session;
import atas.testutil.RandomStatistics;
import atas.testutil.TypicalSessions;
import atas.testutil.TypicalStudents;


public class SessionStatisticsTest {

    @Test
    public void addStatisticsTest() {

        SessionStatistics stats1 = new SessionStatistics();
        SessionStatistics stats2 = new SessionStatistics();

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

        SessionStatistics stats1 = new SessionStatistics();
        SessionStatistics stats2 = new SessionStatistics();

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
    public void updateParticipationStatisticsTest() {

        // initialize session stats with default value
        SessionStatistics stat1 = new SessionStatistics();
        SessionStatistics stat2 = new SessionStatistics();

        // customize session participation status
        Session sessionWeekOne = TypicalSessions.duplicateSession(TypicalSessions.SESSION_WEEK_ONE);
        Session sessionWeekTwo = TypicalSessions.duplicateSession(TypicalSessions.SESSION_WEEK_TWO);
        sessionWeekOne.initializeSession(TypicalStudents.getTypicalStudents());
        sessionWeekTwo.initializeSession(TypicalStudents.getTypicalStudents());

        sessionWeekOne.updateParticipation(new IndexRange("2-7"));
        sessionWeekTwo.updateParticipation(new IndexRange("4-4"));

        // test and compare
        stat1.updateParticipationStatistics(sessionWeekOne);

        assertEquals(6, ((ParticipationStatistics) stat1.getParticipationStatistics()).getActual());
        assertEquals(7, ((ParticipationStatistics) stat1.getParticipationStatistics()).getExpected());

        stat2.updateParticipationStatistics(sessionWeekTwo);

        assertEquals(1, ((ParticipationStatistics) stat2.getParticipationStatistics()).getActual());
        assertEquals(7, ((ParticipationStatistics) stat2.getParticipationStatistics()).getExpected());
    }

    @Test
    public void updatePresenceStatisticsTest() {

        // initialize session stats with default value
        SessionStatistics stat1 = new SessionStatistics();
        SessionStatistics stat2 = new SessionStatistics();

        // customize session presence status
        Session sessionWeekOne = TypicalSessions.duplicateSession(TypicalSessions.SESSION_WEEK_ONE);
        Session sessionWeekTwo = TypicalSessions.duplicateSession(TypicalSessions.SESSION_WEEK_TWO);
        sessionWeekOne.initializeSession(TypicalStudents.getTypicalStudents());
        sessionWeekTwo.initializeSession(TypicalStudents.getTypicalStudents());

        sessionWeekOne.updatePresence(new IndexRange("2-7"));
        sessionWeekTwo.updatePresence(new IndexRange("4-4"));

        // test and compare
        stat1.updatePresenceStatistics(sessionWeekOne);

        assertEquals(6, ((PresenceStatistics) stat1.getPresenceStatistics()).getActual());
        assertEquals(7, ((PresenceStatistics) stat1.getPresenceStatistics()).getExpected());

        stat2.updatePresenceStatistics(sessionWeekTwo);

        assertEquals(1, ((PresenceStatistics) stat2.getPresenceStatistics()).getActual());
        assertEquals(7, ((PresenceStatistics) stat2.getPresenceStatistics()).getExpected());
    }

    @Test
    public void getParticipationStatisticsTest() {

        Session sessionRemedial = TypicalSessions.duplicateSession(TypicalSessions.REMEDIAL);
        sessionRemedial.initializeSession(TypicalStudents.getTypicalStudents());
        sessionRemedial.updateParticipation(new IndexRange("3-5"));

        ParticipationStatistics stat =
                (ParticipationStatistics) sessionRemedial.getSessionStats().getParticipationStatistics();

        assertEquals(3, stat.getActual());
        assertEquals(7, stat.getExpected());
    }

    @Test
    public void getPresenceStatisticsTest() {

        Session sessionRemedial = TypicalSessions.duplicateSession(TypicalSessions.REMEDIAL);
        sessionRemedial.initializeSession(TypicalStudents.getTypicalStudents());
        sessionRemedial.updatePresence(new IndexRange("3-5"));

        PresenceStatistics stat =
                (PresenceStatistics) sessionRemedial.getSessionStats().getPresenceStatistics();

        assertEquals(3, stat.getActual());
        assertEquals(7, stat.getExpected());
    }

    @Test
    public void containsTest() {

        SessionStatistics stat = new SessionStatistics(7);

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

        SessionStatistics stat = new SessionStatistics(7);

        //null -> false
        SessionStatistics nullStat = null;
        assertFalse(stat.equals(nullStat));

        //different class -> false
        StudentStatistics diffStat = new StudentStatistics();
        assertFalse(stat.equals(diffStat));

        //same stats -> true
        SessionStatistics sameStat = new SessionStatistics();
        assertTrue(stat.equals(sameStat));

        //same type of stats & different stat values -> true
        SessionStatistics sameStatDiffVal = new SessionStatistics();
        sameStatDiffVal.replaceStatistics(new ParticipationStatistics(1, 1));
        assertTrue(stat.equals(sameStatDiffVal));

        //different stat class included -> false
        SessionStatistics diffStatClassStat = new SessionStatistics();
        diffStatClassStat.addStatistics(new RandomStatistics());
        assertFalse(stat.equals(diffStatClassStat));
    }
}
