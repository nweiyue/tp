package atas.ui;

import java.util.logging.Logger;

import atas.commons.core.LogsCenter;
import atas.model.attendance.Attributes;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

public class SessionStudentListPanel extends UiPart<Region> {
    private static final String FXML = "SessionStudentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @javafx.fxml.FXML
    private ListView<Attributes> sessionStudentListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public SessionStudentListPanel(ObservableList<Attributes> attributesList) {
        super(FXML);
        sessionStudentListView.setItems(attributesList);
        sessionStudentListView.setCellFactory(listView -> new SessionStudentListPanel.SessionStudentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class SessionStudentListViewCell extends ListCell<Attributes> {
        @Override
        protected void updateItem(Attributes attributes, boolean empty) {
            super.updateItem(attributes, empty);

            if (empty || attributes == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SessionStudentCard(attributes, getIndex() + 1).getRoot());
            }
        }
    }
}
