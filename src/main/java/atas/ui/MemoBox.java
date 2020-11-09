package atas.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * Contains a TextArea that is displayed at the middle of the Memo tab.
 */
public class MemoBox extends UiPart<Region> {

    private static final String FXML = "MemoBox.fxml";

    @FXML
    private TextArea memoTextBox;

    /**
     * Constructs a MemoBox to be displayed.
     *
     * @param content String content of the Memo of ATAS.
     */
    public MemoBox(String content) {
        super(FXML);
        memoTextBox.setText(content);
    }

    /**
     * Writes text into the MemoBox.
     *
     * @param content String content to be displayed in the MemoBox.
     */
    public void setTextContent(String content) {
        requireNonNull(content);
        memoTextBox.setText(content);
    }

    /**
     * Reads text from the MemoBox.
     *
     * @return String content in the MemoBox.
     */
    public String getTextContent() {
        String content = memoTextBox.getText();
        requireNonNull(content);
        return content;
    }

    /**
     * Returns the text box component of the MemoBox.
     *
     * @return JavaFx's TextArea component.
     */
    public TextArea getMemoTextBox() {
        return memoTextBox;
    }
}
