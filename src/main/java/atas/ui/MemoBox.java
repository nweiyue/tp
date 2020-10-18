package atas.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the memo box that is displayed at the middle of the memo tab.
 */
public class MemoBox extends UiPart<Region> {

    private static final String FXML = "MemoBox.fxml";

    @FXML
    private TextArea memoTextBox;

    /**
     * Creates a memo box to display.
     * @param content String content of the Memo of the application.
     */
    public MemoBox(String content) {
        super(FXML);
        memoTextBox.setText(content);
    }

    /**
     * Writes text into the memo box.
     * @param content String content to be displayed in the TextArea.
     */
    public void setTextContent(String content) {
        requireNonNull(content);
        memoTextBox.setText(content);
    }

    /**
     * Reads text from the memo box.
     * @return content in the memo box.
     */
    public String getTextContent() {
        String content = memoTextBox.getText();
        requireNonNull(content);
        return content;
    }

    /**
     * Returns the text box component of the memo box.
     * @return JavaFx's TextArea component.
     */
    public TextArea getMemoTextBox() {
        return memoTextBox;
    }
}
