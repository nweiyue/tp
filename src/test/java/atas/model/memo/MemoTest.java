package atas.model.memo;

import static atas.testutil.TypicalMemoContents.SAMPLE_MEMO_CONTENT_ONE;
import static atas.testutil.TypicalMemoContents.SAMPLE_MEMO_CONTENT_TWO;
import static atas.testutil.TypicalMemoContents.SAMPLE_MEMO_NOTE_ONE;
import static atas.testutil.TypicalMemos.SAMPLE_MEMO_ONE;
import static atas.testutil.TypicalMemos.SAMPLE_MEMO_TWO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class MemoTest {

    @Test
    public void testGetContent() {
        Memo memo = new Memo(SAMPLE_MEMO_CONTENT_ONE);
        assertEquals(SAMPLE_MEMO_CONTENT_ONE, memo.getContent());
    }

    @Test
    public void testSetContent() {
        Memo memo = new Memo(SAMPLE_MEMO_CONTENT_ONE);
        memo.setContent(SAMPLE_MEMO_CONTENT_TWO);
        assertEquals(SAMPLE_MEMO_CONTENT_TWO, memo.getContent());
    }

    @Test
    public void testAddNote() {
        Memo memo = new Memo(SAMPLE_MEMO_CONTENT_ONE);
        memo.addNote(SAMPLE_MEMO_NOTE_ONE);
        assertEquals(SAMPLE_MEMO_CONTENT_ONE.concat("\n").concat(SAMPLE_MEMO_NOTE_ONE), memo.getContent());
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(SAMPLE_MEMO_ONE.equals(SAMPLE_MEMO_ONE));
        assertTrue(SAMPLE_MEMO_TWO.equals(SAMPLE_MEMO_TWO));

        // same values -> returns true
        Memo memoOneCopy = new Memo(SAMPLE_MEMO_ONE.getContent());
        assertTrue(SAMPLE_MEMO_ONE.equals(memoOneCopy));
        Memo memoTwoCopy = new Memo(SAMPLE_MEMO_TWO.getContent());
        assertTrue(SAMPLE_MEMO_TWO.equals(memoTwoCopy));

        // null -> returns false
        assertFalse(SAMPLE_MEMO_ONE.equals(null));

        // different Types -> returns false
        assertFalse(SAMPLE_MEMO_ONE.equals(1));

        // different Memos -> return false
        assertFalse(SAMPLE_MEMO_ONE.equals(SAMPLE_MEMO_TWO));
    }
}
