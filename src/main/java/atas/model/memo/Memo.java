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

    public void setMemoContent(String content) {
        this.content = content;
    }

    public void saveMemo(String content) {
        setMemoContent(content);
    }

}
