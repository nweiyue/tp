package atas.testutil;

import atas.model.memo.Memo;

/**
 * A utility class to help with building Memo objects.
 */
public class MemoBuilder {
    public static final String DEFAULT_CONTENT = "sample content";

    private String content;

    /**
     * Creates a {@code MemoBuilder} with the default details.
     */
    public MemoBuilder() {
        content = DEFAULT_CONTENT;
    }

    /**
     * Initializes the MemoBuilder with the data of {@code memoToCopy}.
     */
    public MemoBuilder(Memo memoToCopy) {
        content = memoToCopy.getContent();
    }

    /**
     * Sets the {@code SessionName} of the {@code Session} that we are building.
     */
    public MemoBuilder withConTent(String content) {
        this.content = content;
        return this;
    }

    /**
     * Builds a memo using {@code content}
     */
    public Memo build() {
        return new Memo(content);
    }
}
