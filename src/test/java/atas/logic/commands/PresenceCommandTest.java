package atas.logic.commands;

import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static atas.testutil.TypicalSessions.SESSIONDATE_WEEK_ONE;
import static atas.testutil.TypicalSessions.SESSIONNAME_WEEK_ONE;
import static atas.testutil.TypicalSessions.SESSIONNAME_WEEK_TWO;
import static atas.testutil.TypicalSessions.SESSION_WEEK_ONE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atas.logic.commands.sessioncommands.PresenceCommand;
import atas.logic.commands.studentlistcommands.ClearCommand;
import atas.model.Model;
import atas.model.attendance.IndexRange;
import atas.model.attendance.Session;
import atas.testutil.ModelManagerBuilder;

public class PresenceCommandTest {

    private static final String INDEXRANGE_ONE_NUMBER = "1";
    private static final String INDEXRANGE_ZERO_INDEX = "0";
    private static final String INDEXRANGE_POSITIVE_RANGE = "1-3";
    private static final String INDEXRANGE_SAME_NUMBER = "2-2";

    private Model model = ModelManagerBuilder.buildTypicalModelManager();


    @BeforeEach
    public void setup() {
        model.addSession(SESSION_WEEK_ONE);
        model.setCurrentSessionTrue();
    }

    @Test
    public void execute_presenceOneNumber_success() {
        // actual model
        IndexRange indexRange = new IndexRange(INDEXRANGE_ONE_NUMBER);
        PresenceCommand presenceCommand = new PresenceCommand(SESSIONNAME_WEEK_ONE, indexRange);

        // expected model
        Model expectedModel = ModelManagerBuilder.buildTypicalModelManager();
        expectedModel.setCurrentSessionTrue();
        Session session = new Session(SESSIONNAME_WEEK_ONE, SESSIONDATE_WEEK_ONE);
        expectedModel.addSession(session);
        session.updatePresence(indexRange);

        assertCommandSuccess(presenceCommand, model, PresenceCommand.MESSAGE_SUCCESS, expectedModel);

        for (Session s: model.getSessionList().getSessions()) {
            if (s.isSameSession(SESSION_WEEK_ONE)) {
                //assertTrue(s.getStudentList().get(0).getPresenceStatus());
            }
        }
    }

    @Test
    public void execute_presenceIndexRange_success() {
        // actual model
        IndexRange indexRange = new IndexRange(INDEXRANGE_POSITIVE_RANGE);
        PresenceCommand presenceCommand = new PresenceCommand(SESSIONNAME_WEEK_ONE, indexRange);

        // expected model
        Model expectedModel = ModelManagerBuilder.buildTypicalModelManager();
        expectedModel.setCurrentSessionTrue();
        Session session = new Session(SESSIONNAME_WEEK_ONE, SESSIONDATE_WEEK_ONE);
        expectedModel.addSession(session);
        session.updatePresence(indexRange);

        assertCommandSuccess(presenceCommand, model, PresenceCommand.MESSAGE_SUCCESS, expectedModel);

        for (Session s: model.getSessionList().getSessions()) {
            if (s.isSameSession(SESSION_WEEK_ONE)) {
              /*  assertTrue(s.getStudentList().get(0).getPresenceStatus());
                assertTrue(s.getStudentList().get(1).getPresenceStatus());
                assertTrue(s.getStudentList().get(2).getPresenceStatus());*/
            }
        }
    }

    @Test
    public void execute_presenceZeroIndex_success() {
        // actual model
        IndexRange indexRange = new IndexRange(INDEXRANGE_ZERO_INDEX);
        PresenceCommand presenceCommand = new PresenceCommand(SESSIONNAME_WEEK_ONE, indexRange);

        // expected model
        Model expectedModel = ModelManagerBuilder.buildTypicalModelManager();
        expectedModel.setCurrentSessionTrue();
        Session session = new Session(SESSIONNAME_WEEK_ONE, SESSIONDATE_WEEK_ONE);
        expectedModel.addSession(session);
        session.updatePresence(indexRange);

        assertCommandSuccess(presenceCommand, model, PresenceCommand.MESSAGE_SUCCESS, expectedModel);

        for (Session s: model.getSessionList().getSessions()) {
            if (s.isSameSession(SESSION_WEEK_ONE)) {
                //assertTrue(s.getStudentList().get(0).getPresenceStatus());
            }
        }
    }

    @Test
    public void execute_presenceSameNumberIndexRange_success() {
        // actual model
        IndexRange indexRange = new IndexRange(INDEXRANGE_SAME_NUMBER);
        PresenceCommand presenceCommand = new PresenceCommand(SESSIONNAME_WEEK_ONE, indexRange);

        // expected model
        Model expectedModel = ModelManagerBuilder.buildTypicalModelManager();
        expectedModel.setCurrentSessionTrue();
        Session session = new Session(SESSIONNAME_WEEK_ONE, SESSIONDATE_WEEK_ONE);
        expectedModel.addSession(session);
        session.updatePresence(indexRange);

        assertCommandSuccess(presenceCommand, model, PresenceCommand.MESSAGE_SUCCESS, expectedModel);

        for (Session s: model.getSessionList().getSessions()) {
            if (s.isSameSession(SESSION_WEEK_ONE)) {
                //assertTrue(s.getStudentList().get(1).getPresenceStatus());
            }
        }
    }

    @Test
    public void equals() {
        final IndexRange indexRange = new IndexRange(INDEXRANGE_ONE_NUMBER);
        final PresenceCommand presenceCommand = new PresenceCommand(SESSIONNAME_WEEK_ONE, indexRange);



        // same values -> returns true
        IndexRange index = new IndexRange(INDEXRANGE_ONE_NUMBER);
        PresenceCommand commandWithSameValues = new PresenceCommand(SESSIONNAME_WEEK_ONE, index);
        assertTrue(presenceCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(presenceCommand.equals(presenceCommand));

        // null -> returns false
        assertFalse(presenceCommand.equals(null));

        // different types -> returns false
        assertFalse(presenceCommand.equals(new ClearCommand()));

        // different session name -> returns false
        assertFalse(presenceCommand.equals(new PresenceCommand(SESSIONNAME_WEEK_TWO, indexRange)));

    }
}
