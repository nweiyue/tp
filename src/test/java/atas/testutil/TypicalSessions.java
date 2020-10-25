package atas.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import atas.model.session.Session;
import atas.model.session.SessionDate;
import atas.model.session.SessionList;
import atas.model.session.SessionName;
import atas.model.student.Student;

/**
 * A utility class containing a list of {@code Session} objects to be used in tests.
 */
public class TypicalSessions {

    public static final Session TUT1 = new SessionBuilder().withSessionName("tut1")
            .withSessionDate("1/1/2020").build();
    public static final Session TUT2 = new SessionBuilder().withSessionName("tut2")
            .withSessionDate("1/2/2020").build();
    public static final Session TUT3 = new SessionBuilder().withSessionName("tut3")
            .withSessionDate("1/3/2020").build();
    public static final Session LAB1 = new SessionBuilder().withSessionName("lab1")
            .withSessionDate("1/4/2020").build();
    public static final Session LAB2 = new SessionBuilder().withSessionName("lab2")
            .withSessionDate("1/5/2020").build();
    public static final Session EMPTY_SESSION_1 = new SessionBuilder().withSessionName("tut99")
            .withSessionDate("1/1/2020").build();
    public static final Session EMPTY_SESSION_2 = new SessionBuilder().withSessionName("tut100")
            .withSessionDate("1/1/2020").build();

    // Manually added
    public static final Session CONSULTATION = new SessionBuilder().withSessionName("consultation")
            .withSessionDate("7/5/2020").build();
    public static final Session REMEDIAL = new SessionBuilder().withSessionName("remedial")
            .withSessionDate("14/6/2020").build();

    // sample sessions
    public static final String SESSIONDATE_WEEK_ONE_STRING = "10/8/2020";
    public static final String SESSIONDATE_WEEK_TWO_STRING = "17/8/2020";
    public static final String SESSIONDATE_WEEK_THREE_STRING = "24/8/2020";

    public static final SessionDate SESSIONDATE_WEEK_ONE = new SessionDate(SESSIONDATE_WEEK_ONE_STRING);
    public static final SessionDate SESSIONDATE_WEEK_TWO = new SessionDate(SESSIONDATE_WEEK_TWO_STRING);
    public static final SessionDate SESSIONDATE_WEEK_THREE = new SessionDate(SESSIONDATE_WEEK_THREE_STRING);

    public static final String SESSIONNAME_WEEK_ONE_STRING = "week 1";
    public static final String SESSIONNAME_WEEK_TWO_STRING = "week 2";
    public static final String SESSIONNAME_WEEK_THREE_STRING = "week 3";

    public static final SessionName SESSIONNAME_WEEK_ONE = new SessionName(SESSIONNAME_WEEK_ONE_STRING);
    public static final SessionName SESSIONNAME_WEEK_TWO = new SessionName(SESSIONNAME_WEEK_TWO_STRING);
    public static final SessionName SESSIONNAME_WEEK_THREE = new SessionName(SESSIONNAME_WEEK_THREE_STRING);

    public static final Session SESSION_WEEK_ONE = new Session(SESSIONNAME_WEEK_ONE, SESSIONDATE_WEEK_ONE);
    public static final Session SESSION_WEEK_TWO = new Session(SESSIONNAME_WEEK_TWO, SESSIONDATE_WEEK_TWO);
    public static final Session SESSION_WEEK_THREE = new Session(SESSIONNAME_WEEK_THREE, SESSIONDATE_WEEK_THREE);



    private TypicalSessions() {} // prevents instantiation

    /**
     * Returns an {@code SessionList} with all the typical sessions.
     */
    public static SessionList getTypicalSessionList(List<Student> list) {
        SessionList sessionList = new SessionList(list);
        for (Session session : getTypicalSessions()) {
            sessionList.addSession(session);
        }
        return sessionList;
    }

    /**
     * Returns an {@code SessionList} with all the typical sessions.
     */
    public static SessionList getTypicalSessionList() {
        SessionList sessionList = new SessionList();
        for (Session session : getTypicalSessions()) {
            sessionList.addSession(session);
        }
        return sessionList;
    }

    public static List<Session> getTypicalSessions() {
        List<Session> result = new ArrayList<>();
        result.addAll(Arrays.asList(duplicateSession(TUT1), duplicateSession(TUT2),
                duplicateSession(TUT3), duplicateSession(LAB1), duplicateSession(LAB2)));
        return result;
    }

    public static SessionList getTypicalSessionListMinusTut1() {
        SessionList sessionList = new SessionList();
        for (Session session : getTypicalSessionsMinusTut1()) {
            sessionList.addSession(session);
        }
        return sessionList;
    }

    public static List<Session> getTypicalSessionsMinusTut1() {
        return new ArrayList<>(Arrays.asList(duplicateSession(TUT2), duplicateSession(TUT3),
                duplicateSession(LAB1), duplicateSession(LAB2)));
    }

    public static Session duplicateSession(Session session) {
        return new Session(session.getSessionName(), session.getSessionDate());
    }
}
