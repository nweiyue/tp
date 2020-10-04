package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalSessions.SESSIONDATE_WEEK_ONE;
import static seedu.address.testutil.TypicalSessions.SESSIONNAME_WEEK_ONE;
import static seedu.address.testutil.TypicalSessions.SESSIONNAME_WEEK_TWO;
import static seedu.address.testutil.TypicalSessions.SESSION_WEEK_ONE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.attendance.IndexRange;
import seedu.address.model.attendance.Session;
import seedu.address.testutil.ModelManagerBuilder;


public class ParticipateCommandTest {

    private static final String INDEXRANGE_ONE_NUMBER = "1";
    private static final String INDEXRANGE_ZERO_INDEX = "0";
    private static final String INDEXRANGE_POSITIVE_RANGE = "1-3";
    private static final String INDEXRANGE_SAME_NUMBER = "2-2";

    private Model model = ModelManagerBuilder.buildTypicalModelManager();


    @BeforeEach
    public void setup() {
        model.addSession(SESSION_WEEK_ONE);
    }

    @Test
    public void execute_participateOneNumber_success() {
        // actual model
        IndexRange indexRange = new IndexRange(INDEXRANGE_ONE_NUMBER);
        ParticipateCommand participateCommand = new ParticipateCommand(SESSIONNAME_WEEK_ONE, indexRange);

        // expected model
        Model expectedModel = ModelManagerBuilder.buildTypicalModelManager();
        Session session = new Session(SESSIONNAME_WEEK_ONE, SESSIONDATE_WEEK_ONE);
        expectedModel.addSession(session);
        session.updateParticipation(indexRange);

        assertCommandSuccess(participateCommand, model, ParticipateCommand.MESSAGE_SUCCESS, expectedModel);

        for (Session s: model.getSessionList().getSessions()) {
            if (s.isSameSession(SESSION_WEEK_ONE)) {
                assertTrue(s.getStudentList().get(0).getParticipationStatus());
            }
        }
    }

    @Test
    public void execute_participateIndexRange_success() {
        // actual model
        IndexRange indexRange = new IndexRange(INDEXRANGE_POSITIVE_RANGE);
        ParticipateCommand participateCommand = new ParticipateCommand(SESSIONNAME_WEEK_ONE, indexRange);

        // expected model
        Model expectedModel = ModelManagerBuilder.buildTypicalModelManager();
        Session session = new Session(SESSIONNAME_WEEK_ONE, SESSIONDATE_WEEK_ONE);
        expectedModel.addSession(session);
        session.updateParticipation(indexRange);

        assertCommandSuccess(participateCommand, model, ParticipateCommand.MESSAGE_SUCCESS, expectedModel);

        for (Session s: model.getSessionList().getSessions()) {
            if (s.isSameSession(SESSION_WEEK_ONE)) {
                assertTrue(s.getStudentList().get(0).getParticipationStatus());
                assertTrue(s.getStudentList().get(1).getParticipationStatus());
                assertTrue(s.getStudentList().get(2).getParticipationStatus());
            }
        }
    }

    @Test
    public void execute_participateZeroIndex_success() {
        // actual model
        IndexRange indexRange = new IndexRange(INDEXRANGE_ZERO_INDEX);
        ParticipateCommand participateCommand = new ParticipateCommand(SESSIONNAME_WEEK_ONE, indexRange);

        // expected model
        Model expectedModel = ModelManagerBuilder.buildTypicalModelManager();
        Session session = new Session(SESSIONNAME_WEEK_ONE, SESSIONDATE_WEEK_ONE);
        expectedModel.addSession(session);
        session.updateParticipation(indexRange);

        assertCommandSuccess(participateCommand, model, ParticipateCommand.MESSAGE_SUCCESS, expectedModel);

        for (Session s: model.getSessionList().getSessions()) {
            if (s.isSameSession(SESSION_WEEK_ONE)) {
                assertTrue(s.getStudentList().get(0).getParticipationStatus());
            }
        }
    }

    @Test
    public void execute_participateSameNumberIndexRange_success() {
        // actual model
        IndexRange indexRange = new IndexRange(INDEXRANGE_SAME_NUMBER);
        ParticipateCommand participateCommand = new ParticipateCommand(SESSIONNAME_WEEK_ONE, indexRange);

        // expected model
        Model expectedModel = ModelManagerBuilder.buildTypicalModelManager();
        Session session = new Session(SESSIONNAME_WEEK_ONE, SESSIONDATE_WEEK_ONE);
        expectedModel.addSession(session);
        session.updateParticipation(indexRange);

        assertCommandSuccess(participateCommand, model, ParticipateCommand.MESSAGE_SUCCESS, expectedModel);

        for (Session s: model.getSessionList().getSessions()) {
            if (s.isSameSession(SESSION_WEEK_ONE)) {
                assertTrue(s.getStudentList().get(1).getParticipationStatus());
            }
        }
    }

    @Test
    public void equals() {
        final IndexRange indexRange = new IndexRange(INDEXRANGE_ONE_NUMBER);
        final ParticipateCommand participateCommand = new ParticipateCommand(SESSIONNAME_WEEK_ONE, indexRange);



        // same values -> returns true
        IndexRange index = new IndexRange(INDEXRANGE_ONE_NUMBER);
        ParticipateCommand commandWithSameValues = new ParticipateCommand(SESSIONNAME_WEEK_ONE, index);
        assertTrue(participateCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(participateCommand.equals(participateCommand));

        // null -> returns false
        assertFalse(participateCommand.equals(null));

        // different types -> returns false
        assertFalse(participateCommand.equals(new ClearCommand()));

        // different session name -> returns false
        assertFalse(participateCommand.equals(new ParticipateCommand(SESSIONNAME_WEEK_TWO, indexRange)));

    }
}
