package atas.logic.commands.sessionlist.session;

import static atas.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX_RANGE;
import static atas.logic.commands.CommandTestUtil.assertCommandFailure;
import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static atas.testutil.Assert.assertThrows;
import static atas.testutil.TypicalSessions.SESSIONDATE_WEEK_ONE;
import static atas.testutil.TypicalSessions.SESSIONNAME_WEEK_ONE;
import static atas.testutil.TypicalSessions.SESSIONNAME_WEEK_TWO;
import static atas.testutil.TypicalSessions.SESSION_WEEK_ONE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atas.commons.core.Messages;
import atas.commons.core.index.Index;
import atas.logic.commands.exceptions.CommandException;
import atas.logic.commands.studentlist.ClearStudentListCommand;
import atas.model.Model;
import atas.model.session.IndexRange;
import atas.model.session.Session;
import atas.testutil.ModelManagerBuilder;

public class PresenceCommandTest {

    private static final String INDEXRANGE_ONE_NUMBER = "1";
    private static final String INDEXRANGE_POSITIVE_RANGE = "1-3";
    private static final String INDEXRANGE_SAME_NUMBER = "2-2";
    private static final String INDEXRANGE_INVALID_RANGE = "1-11";
    private static final String INDEXRANGE_INVALID_SAME_NUMBER = "11-11";

    private final Model model = ModelManagerBuilder.buildTypicalModelManager();


    @BeforeEach
    public void setup() {
        model.addSession(SESSION_WEEK_ONE);
        model.setCurrentSessionTrue();
    }

    @Test
    public void execute_presenceOneNumber_success() {
        // actual model
        model.enterSession(Index.fromZeroBased(1));
        IndexRange indexRange = new IndexRange(INDEXRANGE_ONE_NUMBER);
        PresenceCommand presenceCommand = new PresenceCommand(SESSIONNAME_WEEK_ONE, indexRange);

        // expected model
        Model expectedModel = ModelManagerBuilder.buildTypicalModelManager();
        expectedModel.setCurrentSessionTrue();
        Session session = new Session(SESSIONNAME_WEEK_ONE, SESSIONDATE_WEEK_ONE);
        expectedModel.addSession(session);
        session.updatePresence(indexRange);

        assertCommandSuccess(presenceCommand, model, PresenceCommand.MESSAGE_SUCCESS, expectedModel);

        for (Session s: expectedModel.getSessionList().getSessions()) {
            if (s.isSameSession(SESSION_WEEK_ONE)) {
                assertTrue(s.getAttributeList().get(0).getPresenceStatus());
            }
        }
    }

    @Test
    public void execute_presenceIndexRange_success() {
        // actual model
        model.enterSession(Index.fromZeroBased(1));
        IndexRange indexRange = new IndexRange(INDEXRANGE_POSITIVE_RANGE);
        PresenceCommand presenceCommand = new PresenceCommand(SESSIONNAME_WEEK_ONE, indexRange);

        // expected model
        Model expectedModel = ModelManagerBuilder.buildTypicalModelManager();
        expectedModel.setCurrentSessionTrue();
        Session session = new Session(SESSIONNAME_WEEK_ONE, SESSIONDATE_WEEK_ONE);
        expectedModel.addSession(session);
        session.updatePresence(indexRange);

        assertCommandSuccess(presenceCommand, model, PresenceCommand.MESSAGE_SUCCESS, expectedModel);

        for (Session s: expectedModel.getSessionList().getSessions()) {
            if (s.isSameSession(SESSION_WEEK_ONE)) {
                assertTrue(s.getAttributeList().get(0).getPresenceStatus());
                assertTrue(s.getAttributeList().get(1).getPresenceStatus());
                assertTrue(s.getAttributeList().get(2).getPresenceStatus());
            }
        }
    }

    @Test
    public void execute_presenceSameNumberIndexRange_success() {
        // actual model
        model.enterSession(Index.fromZeroBased(1));
        IndexRange indexRange = new IndexRange(INDEXRANGE_SAME_NUMBER);
        PresenceCommand presenceCommand = new PresenceCommand(SESSIONNAME_WEEK_ONE, indexRange);

        // expected model
        Model expectedModel = ModelManagerBuilder.buildTypicalModelManager();
        expectedModel.setCurrentSessionTrue();
        Session session = new Session(SESSIONNAME_WEEK_ONE, SESSIONDATE_WEEK_ONE);
        expectedModel.addSession(session);
        session.updatePresence(indexRange);

        assertCommandSuccess(presenceCommand, model, PresenceCommand.MESSAGE_SUCCESS, expectedModel);

        for (Session s: expectedModel.getSessionList().getSessions()) {
            if (s.isSameSession(SESSION_WEEK_ONE)) {
                assertTrue(s.getAttributeList().get(1).getPresenceStatus());
            }
        }
    }

    @Test
    public void execute_presenceInvalidIndexRange_failure() {
        Model model = ModelManagerBuilder.buildTypicalModelManager();
        model.enterSession(Index.fromOneBased(1));
        IndexRange invalidIndexRange = new IndexRange(INDEXRANGE_INVALID_RANGE);
        PresenceCommand presenceCommand = new PresenceCommand(SESSIONNAME_WEEK_ONE, invalidIndexRange);

        assertCommandFailure(presenceCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX_RANGE);

        IndexRange invalidSameIndex = new IndexRange(INDEXRANGE_INVALID_SAME_NUMBER);
        presenceCommand = new PresenceCommand(SESSIONNAME_WEEK_ONE, invalidSameIndex);

        assertCommandFailure(presenceCommand, model, MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX_RANGE);
    }

    @Test
    public void execute_notInSessionTab_throwsCommandException() {
        // actual model
        IndexRange indexRange = new IndexRange(INDEXRANGE_ONE_NUMBER);
        PresenceCommand presenceCommand = new PresenceCommand(SESSIONNAME_WEEK_ONE, indexRange);

        // expected model
        Model expectedModel = ModelManagerBuilder.buildTypicalModelManager();
        Session session = new Session(SESSIONNAME_WEEK_ONE, SESSIONDATE_WEEK_ONE);
        expectedModel.addSession(session);
        session.updatePresence(indexRange);

        assertThrows(CommandException.class,
                Messages.MESSAGE_NOT_IN_SESSION, () -> presenceCommand.execute(expectedModel));
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
        assertFalse(presenceCommand.equals(new ClearStudentListCommand()));

        // different session name -> returns false
        assertFalse(presenceCommand.equals(new PresenceCommand(SESSIONNAME_WEEK_TWO, indexRange)));

    }
}
