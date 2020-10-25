package atas.model.statistics;

import java.util.List;

import atas.commons.core.index.Index;
import atas.commons.core.statistics.StatisticalValues;
import atas.model.session.Attributes;
import atas.model.session.Session;
import atas.model.session.SessionList;


/**
 * Represents the statistical participation data of a student or students in the student list.
 * This statistics data is represented in the form of a ratio or fraction, which
 * consists of an actual value and expected value.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ParticipationStatistics implements Statistics {

    private int actual;
    private int expected;

    /**
     * A constructor with default init value.
     */
    public ParticipationStatistics() {
        this.actual = 0;
        this.expected = 0;
    }

    /**
     * A parameterized constructor.
     */
    public ParticipationStatistics(int actual, int expected) {
        this.actual = actual;
        this.expected = expected;
    }

    @Override
    public String getDataAsRatio() {
        return "Participation : " + this.actual + "/" + this.expected;
    }

    @Override
    public String getDataAsPercentage() {
        if (this.expected == 0) {
            return "Participation : " + this.expected + "%";
        } else {
            return "Participation : " + (this.actual * 100) / this.expected + "%";
        }
    }

    @Override
    public ParticipationStatistics getSessionStatistics(Session session) {
        List<Attributes> list = session.getAttributeList();
        int totalActual = 0;
        int totalExpected = list.size();

        for (Attributes attr: list) {
            if (attr.getParticipationStatus()) {
                totalActual++;
            }
        }

        return new ParticipationStatistics(totalActual, totalExpected);
    }

    @Override
    public ParticipationStatistics getStudentStatistics(SessionList sessionList, Index index) {
        List<Session> list = sessionList.getSessions();
        int totalActual = 0;
        int totalExpected = list.size();

        for (Session session: list) {
            if (session.getAttributeList().get(index.getZeroBased()).getParticipationStatus()) {
                totalActual++;
            }
        }

        return new ParticipationStatistics(totalActual, totalExpected);
    }

    @Override
    public boolean isSameStats(Statistics statistics) {
        if (this == statistics || (statistics != null && getClass() == statistics.getClass())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getStatValue() {
        return StatisticalValues.getValue(actual, expected);
    }

    public int getActual() {
        return actual;
    }

    public ParticipationStatistics setActual(int actual) {
        return new ParticipationStatistics(actual, this.expected);
    }

    public int getExpected() {
        return expected;
    }

    public ParticipationStatistics setExpected(int expected) {
        return new ParticipationStatistics(this.actual, expected);
    }

}
