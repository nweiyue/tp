package atas.model.memo;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Memo pad.
 * Each {@code Memo} contains a String of content that users can make changes to through a text area.
 */
public class Memo {

    /** Represents the content stored in a Memo */
    private String content;

    /**
     * Constructs a Memo object with an empty content.
     */
    public Memo() {
        content = "";
    }

    /**
     * Constructs a Memo object with a specified content.
     *
     * @param content String content to be stored in Memo.
     */
    public Memo(String content) {
        this.content = content;
    }

    /**
     * Returns the content of the Memo.
     *
     * @return String content of the Memo.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the Memo.
     *
     * @param content String content to be written to the Memo.
     */
    public void setContent(String content) {
        requireNonNull(content);
        this.content = content;
    }

    /**
     * Appends a String of text at the end of the Memo.
     *
     * @param note String text to be appended.
     */
    public void addNote(String note) {
        requireNonNull(note);
        String oldContent = getContent();
        String newContent = oldContent.concat("\n").concat(note);
        setContent(newContent);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Memo // instanceof handles nulls
                && content.equals(((Memo) other).content)); // state check
    }

    @Override
    public String toString() {
        return content;
    }
}
