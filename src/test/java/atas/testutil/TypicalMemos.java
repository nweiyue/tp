package atas.testutil;

import atas.model.memo.Memo;

/**
 * A utility class containing a list of {@code Memo} objects to be used in tests.
 */
public class TypicalMemos {
    public static final Memo SAMPLE_MEMO_ONE = new MemoBuilder().withConTent("memo 1").build();
    public static final Memo SAMPLE_MEMO_TWO = new MemoBuilder().withConTent("memo 2").build();
}
