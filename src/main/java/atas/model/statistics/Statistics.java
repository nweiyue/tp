package atas.model.statistics;

import atas.commons.core.index.Index;
import atas.model.session.Session;
import atas.model.session.SessionList;

/**
 * An API that supports statistical behaviour and provide statistical
 * data under different conditions.
 */
public interface Statistics {

    /**
     * Returns statistics data of students in the sesssion.
     */
    Statistics getSessionStatistics(Session session);

    /**
     * Returns statistics data of a student in the student list given the session list.
     */
    Statistics getStudentStatistics(SessionList sessionList, Index index);

    /**
     * Returns a String representing the current presence statistics in the form of ratio.
     */
    String getDataAsRatio();

    /**
     * Returns a String representing the current presence statistics in the form of percentage.
     */
    String getDataAsPercentage();

    /**
     * Determines if the statistics are the same
     */
    boolean isSameStats(Statistics statistics);

    /**
     * Obtains the statistical value of current statistics.
     */
    int getStatValue();
}
