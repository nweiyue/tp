package atas.model.statistics;

import java.util.List;

import atas.commons.core.index.Index;
import atas.commons.core.statistics.StatisticalValues;
import atas.model.session.Attributes;
import atas.model.session.Session;
import atas.model.session.SessionList;


/**
 * Represents the statistical presence/absence data of a student or students in the student list.
 * This statistics data is represented in the form of a ratio or fraction, which
 * consists of an actual value and expected value.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class PresenceStatistics implements Statistics {

    private int actual;
    private int expected;

    /**
     * A constructor with default init value.
     */
    public PresenceStatistics() {
        this.actual = 0;
        this.expected = 0;
    }

    /**
     * A parameterized constructor.
     */
    public PresenceStatistics(int actual, int expected) {
        this.actual = actual;
        this.expected = expected;
    }

    @Override
    public String getDataAsRatio() {
        return "Presence : " + this.actual + "/" + this.expected;
    }

    @Override
    public String getDataAsPercentage() {
        if (this.expected == 0) {
            return "Presence : " + this.expected + "%";
        } else {
            return "Presence : " + (this.actual * 100) / this.expected + "%";
        }
    }

    @Override
    public PresenceStatistics getSessionStatistics(Session session) {
        List<Attributes> list = session.getAttributeList();
        int totalActual = 0;
        int totalExpected = list.size();

        for (Attributes attr: list) {
            if (attr.getPresenceStatus()) {
                totalActual++;
            }
        }

        return new PresenceStatistics(totalActual, totalExpected);
    }

    @Override
    public PresenceStatistics getStudentStatistics(SessionList sessionList, Index index) {
        List<Session> list = sessionList.getSessions();
        int totalActual = 0;
        int totalExpected = list.size();

        for (Session session: list) {
            if (session.getAttributeList().get(index.getZeroBased()).getPresenceStatus()) {
                totalActual++;
            }
        }

        return new PresenceStatistics(totalActual, totalExpected);
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

    public PresenceStatistics setActual(int actual) {
        return new PresenceStatistics(actual, this.expected);
    }

    public int getExpected() {
        return expected;
    }

    public PresenceStatistics setExpected(int expected) {
        return new PresenceStatistics(this.actual, expected);
    }

}
