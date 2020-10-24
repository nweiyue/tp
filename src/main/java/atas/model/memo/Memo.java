package atas.model.memo;

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
     * @param content String content to be shown in Memo.
     */
    public Memo(String content) {
        this.content = content;
    }

    /**
     * Returns the content of the Memo.
     * @return content of the Memo.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the Memo.
     * @param content String content to be written to the Memo.
     */
    private void setContent(String content) {
        this.content = content;
    }

    /**
     * Saves the Memo's content.
     * @param content String content to be saved.
     */
    public void saveMemoContent(String content) {
        setContent(content);
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
