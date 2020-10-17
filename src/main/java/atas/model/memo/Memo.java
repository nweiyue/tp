package atas.model.memo;

public class Memo {
    private String content;

    public Memo() {
        content = "";
    }

    public Memo(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    private void setContent(String content) {
        this.content = content;
    }

    public void saveMemoContent(String content) {
        setContent(content);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Memo // instanceof handles nulls
                && content.equals(((Memo) other).content)); // state check
    }
}
