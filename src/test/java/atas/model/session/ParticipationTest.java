package atas.model.session;

import static atas.commons.core.Messages.MESSAGE_NOT_IN_SESSION;
import static atas.logic.commands.CommandTestUtil.assertCommandFailure;
import static atas.logic.commands.CommandTestUtil.assertCommandSuccess;
import static atas.testutil.TypicalMemoContents.EMPTY_MEMO_CONTENT;
import static atas.testutil.TypicalSessions.SESSION_WEEK_ONE;
import static atas.testutil.TypicalSessions.getTypicalSessionList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import atas.commons.core.index.Index;
import atas.logic.commands.sessionlist.session.ParticipateCommand;
import atas.model.Model;
import atas.model.ModelManager;
import atas.model.UserPrefs;
import atas.model.student.StudentList;
import atas.testutil.ModelManagerBuilder;

class ParticipationTest {

    private static final boolean HAS_PARTICIPATED = true;

    private static final Participation DEFAULT_PARTICIPATION = new Participation();
    private static final Participation POSITIVE_PARTICIPATION = new Participation(HAS_PARTICIPATED);
    private Model model = ModelManagerBuilder.buildTypicalModelManager();

    @Test
    public void toggleParticipationWithoutEnterSessionTest() {
        IndexRange indexRange = new IndexRange("1-4");
        model.addSession(SESSION_WEEK_ONE);
        assertCommandFailure(new ParticipateCommand(indexRange), model,
                MESSAGE_NOT_IN_SESSION);
        model.enterSession(Index.fromZeroBased(1));
        model.updateParticipationBySessionName(SESSION_WEEK_ONE.getSessionName(), indexRange);

        Model expectedModel = new ModelManager(getTypicalSessionList(model.getStudentList().getStudentList()),
            new StudentList(model.getStudentList()), new UserPrefs(), EMPTY_MEMO_CONTENT);
        expectedModel.addSession(SESSION_WEEK_ONE);
        expectedModel.enterSession(Index.fromZeroBased(1));
        expectedModel.updateParticipationBySessionName(SESSION_WEEK_ONE.getSessionName(), indexRange);



        model.setCurrentSessionTrue();

        assertCommandSuccess(new ParticipateCommand(indexRange), model,
                ParticipateCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(DEFAULT_PARTICIPATION.equals(DEFAULT_PARTICIPATION));

        // null -> returns false
        assertFalse(DEFAULT_PARTICIPATION.equals(null));
    }

    @Test
    public void participate() {
        assertEquals(POSITIVE_PARTICIPATION, DEFAULT_PARTICIPATION.toggleParticipation());
        assertEquals(DEFAULT_PARTICIPATION, POSITIVE_PARTICIPATION.toggleParticipation());
    }

    @Test
    public void to_string() {
        Participation actual = new Participation(true);
        Participation expected = new Participation(true);
        assertEquals(expected.toString(), actual.toString());
    }
}
