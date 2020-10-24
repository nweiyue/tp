package atas.ui.sessionlist.session;

import atas.model.session.Attributes;
import atas.ui.UiPart;
import atas.ui.studentlist.StudentCard;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class SessionStudentCard extends UiPart<Region> {

    private static final String FXML = "SessionStudentListCard.fxml";
    private static final String PRESENT = "Present";
    private static final String ABSENT = "Absent";
    private static final String PARTICIPATED = "Participated";
    private static final String NOT_PARTICIPATED = "Not participated";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Attributes attributes;

    @FXML
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
    private FlowPane attributesPane;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public SessionStudentCard(Attributes attributes, int displayedIndex) {
        super(FXML);
        this.attributes = attributes;
        id.setText(displayedIndex + ". ");
        name.setText(attributes.getName());

        attributesPane.getChildren().add(getPresenceLabel(attributes));
        attributesPane.getChildren().add(getParticipationLabel(attributes));
    }

    private Label getPresenceLabel(Attributes attributes) {
        boolean presenceStatus = attributes.getPresenceStatus();
        String text = presenceStatus ? PRESENT : ABSENT;
        return new PresenceLabel(text, presenceStatus);
    }

    private Label getParticipationLabel(Attributes attributes) {
        boolean participationStatus = attributes.getParticipationStatus();
        String text = participationStatus ? PARTICIPATED : NOT_PARTICIPATED;
        return new ParticipationLabel(text, participationStatus);
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

