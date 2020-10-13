package atas.ui;

import atas.model.attendance.Attributes;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class SessionStudentCard extends UiPart<Region> {

    private static final String FXML = "SessionStudentListCard.fxml";
    private static final String PRESENT = "PRESENT";
    private static final String ABSENT = "ABSENT";
    private static final String PARTICIPATE = "PARTICIPATED";
    private static final String NO_PARTICIPATE = "NOT PARTICIPATED";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Attributes attributes;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label presence;
    @FXML
    private Label participation;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public SessionStudentCard(Attributes attributes, int displayedIndex) {
        super(FXML);
        this.attributes = attributes;
        id.setText(displayedIndex + ". ");
        name.setText(attributes.getName());
        String presenceStatus = attributes.getPresenceStatus() ? PRESENT : ABSENT;
        String participationStatus = attributes.getParticipationStatus() ? PARTICIPATE : NO_PARTICIPATE;
        presence.setText(presenceStatus);
        participation.setText(participationStatus);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentCard)) {
            return false;
        }

        // state check
        SessionStudentCard card = (SessionStudentCard) other;
        return card.equals(other)
                && card.name.equals(((SessionStudentCard) other).name)
                && card.presence.equals(((SessionStudentCard) other).presence)
                && card.participation.equals(((SessionStudentCard) other).participation);
    }
}

