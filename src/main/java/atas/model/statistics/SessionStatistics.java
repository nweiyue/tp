package atas.model.statistics;

import java.util.HashSet;
import java.util.Set;

import atas.model.session.Session;


/**
 * Represents a set of statistical data of students in a session.
 */
public class SessionStatistics {

    private final Set<Statistics> stats;

    /**
     * A constructor which initializes with default statistical values.
     */
    public SessionStatistics() {
        this.stats = new HashSet<>();
        this.stats.add(new PresenceStatistics());
        this.stats.add(new ParticipationStatistics());
    }

    /**
     * A constructor which initializes with given sample size.
     */
    public SessionStatistics(int sampleSize) {
        this.stats = new HashSet<>();
        this.stats.add(new PresenceStatistics(0, sampleSize));
        this.stats.add(new ParticipationStatistics(0, sampleSize));
    }

    /**
     * Adds the given statistic to the current set of statistics in the session.
     */
    public void addStatistics(Statistics statistics) {
        stats.add(statistics);
    }

    /**
     * Adds the given statistics to the current set of statistics in the session.
     */
    public void addStatistics(Statistics... statistics) {
        // do not change this to Collections.addAll, they are not the same.
        for (Statistics stat: statistics) {
            stats.add(stat);
        }
    }

    /**
     * Replaces statistic in the set with the given one.
     * If there is already a statistic of the same type, it will be replaced with the new one. Or else
     * if there isn't a static of the same type, it will be added to the set.
     */
    public void replaceStatistics(Statistics statistics) {
        stats.removeIf(statistics::isSameStats);
        stats.add(statistics);
    }

    /**
     * Replaces statistics in the set with the given collection of statistics.
     * Statistic type that already exists in the set will be removed and replaced with the new one.
     * A new one will be added to the set if does not exist.
     */
    public void replaceStatistics(Statistics... statistics) {
        for (Statistics stat: statistics) {
            stats.removeIf(stat::isSameStats);
            stats.add(stat);
        }
    }

    /**
     * Updates the current participation statistic data with the given session.
     */
    public void updateParticipationStatistics(Session session) {
        replaceStatistics(new ParticipationStatistics().getSessionStatistics(session));
    }

    /**
     * Updates the current presence statistic data with the given session.
     */
    public void updatePresenceStatistics(Session session) {
        replaceStatistics(new PresenceStatistics().getSessionStatistics(session));
    }

    /**
     * Searches the set of statistics for a {@code ParticipationStatistics}.
     */
    public Statistics getParticipationStatistics() {
        Statistics participationStatistics = null;
        for (Statistics statistics: stats) {
            if (statistics instanceof ParticipationStatistics) {
                participationStatistics = statistics;
                break;
            }
        }
        return participationStatistics;
    }

    /**
     * Searches the set of statistics for a {@code PresenceStatistics}.
     */
    public Statistics getPresenceStatistics() {
        Statistics presenceStatistics = null;
        for (Statistics statistics: stats) {
            if (statistics instanceof PresenceStatistics) {
                presenceStatistics = statistics;
                break;
            }
        }
        return presenceStatistics;
    }

    public Set<Statistics> getStats() {
        return stats;
    }

    /**
     * Checks if the set of statistics contain the same type of given statistics.
     */
    public boolean contains(Statistics statistics) {
        for (Statistics stat: stats) {
            if (statistics.getClass() == stat.getClass()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != getClass()) {
            return false;
        } else {
            for (Statistics stat: ((SessionStatistics) o).getStats()) {
                if (!contains(stat)) {
                    return false;
                }
            }
            return true;
        }
    }
}
