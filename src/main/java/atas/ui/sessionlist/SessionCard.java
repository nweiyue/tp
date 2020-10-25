package atas.ui.sessionlist;

import atas.model.session.Session;
import atas.ui.UiPart;
import atas.ui.statistics.StatisticsLabel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class SessionCard extends UiPart<Region> {

    private static final String FXML = "SessionListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Session session;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label date;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane stats;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public SessionCard(Session session, int displayedIndex) {
        super(FXML);
        this.session = session;
        id.setText(displayedIndex + ". ");
        name.setText(session.getSessionName().toString());
        date.setText(session.getSessionDate().toString());
        stats.getChildren().add(getPresenceLabelStatisticLabel(session));
        stats.getChildren().add(getParticipationStatisticLabel(session));
    }

    private Label getPresenceLabelStatisticLabel(Session session) {
        return new StatisticsLabel(session.getSessionStats().getPresenceStatistics());
    }

    private Label getParticipationStatisticLabel(Session session) {
        return new StatisticsLabel(session.getSessionStats().getParticipationStatistics());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SessionCard)) {
            return false;
        }

        // state check
        SessionCard card = (SessionCard) other;
        return id.getText().equals(card.id.getText())
                && session.equals(card.session);
    }
}
