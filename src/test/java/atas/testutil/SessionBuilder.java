package atas.testutil;

import atas.model.session.Session;
import atas.model.session.SessionDate;
import atas.model.session.SessionName;

/**
 * A utility class to help with building Session objects.
 */
public class SessionBuilder {

    public static final String DEFAULT_SESSIONNAME = "Tutorial 1";
    public static final String DEFAULT_SESSIONDATE = "12/12/2020";

    private SessionName sessionName;
    private SessionDate sessionDate;

    /**
     * Creates a {@code SessionBuilder} with the default details.
     */
    public SessionBuilder() {
        sessionName = new SessionName(DEFAULT_SESSIONNAME);
        sessionDate = new SessionDate(DEFAULT_SESSIONDATE);
    }

    /**
     * Initializes the SessionBuilder with the data of {@code sessionToCopy}.
     */
    public SessionBuilder(Session sessionToCopy) {
        sessionName = sessionToCopy.getSessionName();
        sessionDate = sessionToCopy.getSessionDate();
    }

    /**
     * Sets the {@code SessionName} of the {@code Session} that we are building.
     */
    public SessionBuilder withSessionName(String name) {
        this.sessionName = new SessionName(name);
        return this;
    }

    /**
     * Sets the {@code SessionDate} of the {@code Session} that we are building.
     */
    public SessionBuilder withSessionDate(String date) {
        this.sessionDate = new SessionDate(date);
        return this;
    }

    /**
     * Builds a default session using {@code DEFAULT_SESSIONNAME} and {@code DEFAULT_SESSIONDATE}
     */
    public Session build() {
        return new Session(sessionName, sessionDate);
    }
}
