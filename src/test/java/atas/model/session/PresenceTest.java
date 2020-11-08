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
import atas.logic.commands.sessionlist.session.PresenceCommand;
import atas.model.Model;
import atas.model.ModelManager;
import atas.model.UserPrefs;
import atas.model.student.StudentList;
import atas.testutil.ModelManagerBuilder;

class PresenceTest {

    private static final boolean IS_PRESENT = true;

    private static final Presence DEFAULT_PRESENCE = new Presence();
    private static final Presence POSITIVE_PRESENCE = new Presence(IS_PRESENT);
    private Model model = ModelManagerBuilder.buildTypicalModelManager();

    @Test
    public void togglePresenceWithoutEnterSessionTest() {
        IndexRange indexRange = new IndexRange("1-4");
        model.addSession(SESSION_WEEK_ONE);
        assertCommandFailure(new PresenceCommand(indexRange), model, MESSAGE_NOT_IN_SESSION);

        model.enterSession(Index.fromZeroBased(1));
        model.updatePresenceBySessionName(SESSION_WEEK_ONE.getSessionName(), indexRange);

        Model expectedModel = new ModelManager(getTypicalSessionList(model.getStudentList().getStudentList()),
            new StudentList(model.getStudentList()), new UserPrefs(), EMPTY_MEMO_CONTENT);
        expectedModel.addSession(SESSION_WEEK_ONE);
        expectedModel.enterSession(Index.fromZeroBased(1));
        expectedModel.updatePresenceBySessionName(SESSION_WEEK_ONE.getSessionName(), indexRange);


        model.setCurrentSessionTrue();

        assertCommandSuccess(new PresenceCommand(indexRange), model, PresenceCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(DEFAULT_PRESENCE.equals(DEFAULT_PRESENCE));

        // null -> returns false
        assertFalse(DEFAULT_PRESENCE.equals(null));
    }

    @Test
    public void becomePresent() {
        assertEquals(POSITIVE_PRESENCE, DEFAULT_PRESENCE.togglePresence());
        assertEquals(DEFAULT_PRESENCE, POSITIVE_PRESENCE.togglePresence());
    }

    @Test
    public void to_string() {
        Presence actual = new Presence(true);
        Presence expected = new Presence(true);
        assertEquals(expected.toString(), actual.toString());
    }
}
