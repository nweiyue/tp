package atas.ui.sessionlist;

import java.util.logging.Logger;

import atas.commons.core.LogsCenter;
import atas.model.session.Session;
import atas.ui.UiPart;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SessionListPanel extends UiPart<Region> {
    public static final String NO_SESSIONS_MESSAGE = "You currently have no sessions!\n"
            + "Type \"addses …\" to start adding one!";
    private static final String FXML = "SessionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(SessionListPanel.class);

    @FXML
    private ListView<Session> sessionListView;

    @FXML
    private VBox emptySessionListView;

    @FXML
    private Label emptySessionListMessage;

    /**
     * Creates a {@code SessionListPanel} with the given {@code ObservableList}.
     */
    public SessionListPanel(ObservableList<Session> sessionList) {
        super(FXML);
        sessionListView.setItems(sessionList);
        sessionListView.setCellFactory(listView -> new SessionListPanel.SessionListViewCell());
        emptySessionListMessage.setText(NO_SESSIONS_MESSAGE);

        if (sessionList.isEmpty()) {
            showEmptySessionState();
        } else {
            showSessionList();
        }
        addSessionListChangeListener(sessionList);
    }

    private void showSessionList() {
        sessionListView.setVisible(true);
        emptySessionListView.setVisible(false);
    }

    private void showEmptySessionState() {
        sessionListView.setVisible(false);
        emptySessionListView.setVisible(true);
    }

    private void addSessionListChangeListener(ObservableList<Session> sessionList) {
        sessionListView.getItems().addListener(new ListChangeListener<Session>() {
            @Override
            public void onChanged(ListChangeListener.Change change) {
                if (sessionList.isEmpty()) {
                    showEmptySessionState();
                } else {
                    showSessionList();
                }
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Session} using a {@code SessionCard}.
     */
    class SessionListViewCell extends ListCell<Session> {
        @Override
        protected void updateItem(Session session, boolean empty) {
            super.updateItem(session, empty);

            if (empty || session == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SessionCard(session, getIndex() + 1).getRoot());
            }
        }
    }
}
