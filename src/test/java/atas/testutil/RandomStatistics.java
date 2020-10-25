package atas.testutil;

import atas.commons.core.index.Index;
import atas.model.session.Session;
import atas.model.session.SessionList;
import atas.model.statistics.Statistics;

public class RandomStatistics implements Statistics {
    @Override
    public Statistics getSessionStatistics(Session session) {
        return null;
    }

    @Override
    public Statistics getStudentStatistics(SessionList sessionList, Index index) {
        return null;
    }

    @Override
    public String getDataAsRatio() {
        return null;
    }

    @Override
    public String getDataAsPercentage() {
        return null;
    }

    @Override
    public boolean isSameStats(Statistics statistics) {
        return false;
    }

    @Override
    public int getStatValue() {
        return 0;
    }
}
