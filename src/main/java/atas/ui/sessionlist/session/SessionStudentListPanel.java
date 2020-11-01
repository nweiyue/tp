package atas.ui.sessionlist.session;

import java.util.logging.Logger;

import atas.commons.core.LogsCenter;
import atas.model.session.Attributes;
import atas.ui.UiPart;
import atas.ui.studentlist.StudentListPanel;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class SessionStudentListPanel extends UiPart<Region> {
    public static final String NO_SESSION_STUDENTS_MESSAGE = "You currently have no students in this session!\n"
            + "Type \"addstu …\" to start adding one!";
    private static final String FXML = "SessionStudentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StudentListPanel.class);

    @FXML
    private ListView<Attributes> sessionStudentListView;

    @FXML
    private VBox emptySessionStudentListView;

    @FXML
    private Label emptySessionStudentListMessage;

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public SessionStudentListPanel(ObservableList<Attributes> attributesList) {
        super(FXML);
        sessionStudentListView.setItems(attributesList);
        sessionStudentListView.setCellFactory(listView -> new SessionStudentListViewCell());
        emptySessionStudentListMessage.setText(NO_SESSION_STUDENTS_MESSAGE);

        if (attributesList.isEmpty()) {
            showEmptyAttributesState();
        } else {
            showAttributesList();
        }
        addAttributesListChangeListener(attributesList);
    }

    private void showAttributesList() {
        sessionStudentListView.setVisible(true);
        emptySessionStudentListView.setVisible(false);
    }

    private void showEmptyAttributesState() {
        sessionStudentListView.setVisible(false);
        emptySessionStudentListView.setVisible(true);
    }

    private void addAttributesListChangeListener(ObservableList<Attributes> sessionStudentList) {
        sessionStudentListView.getItems().addListener(new ListChangeListener<Attributes>() {
            @Override
            public void onChanged(ListChangeListener.Change change) {
                if (sessionStudentList.isEmpty()) {
                    showEmptyAttributesState();
                } else {
                    showAttributesList();
                }
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
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
