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
    public static final String NOT_IN_SESSION = "You are currently not in any session!\n"
            + "Type \"enterses …\" to enter one!";

    private static final String FXML = "SessionStudentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StudentListPanel.class);

    @FXML
    private ListView<Attributes> sessionStudentListView;

    @FXML
    private VBox labelView;

    @FXML
    private Label displayMessage;

    /**
     * Creates a {@code SessionStudentListPanel} with no {@code Session}.
     */
    public SessionStudentListPanel() {
        super(FXML);
        showNotInSession();
    }

    /**
     * Changes the view to show a message declaring that user is not in a session.
     */
    public void showNotInSession() {
        displayMessage.setText(NOT_IN_SESSION);
        sessionStudentListView.setVisible(false);
        labelView.setVisible(true);
    }

    /**
     * Changes the view to show the attribute list of a session.
     *
     * @param attributesList ObservableList of attributes to be displayed.
     */
    public void showSessionStudentList(ObservableList<Attributes> attributesList) {
        sessionStudentListView.setItems(attributesList);
        sessionStudentListView.setCellFactory(listView -> new SessionStudentListViewCell());

        if (attributesList.isEmpty()) {
            showEmptyAttributesState();
        } else {
            showAttributesList();
        }
        addAttributesListChangeListener(attributesList);
    }

    /**
     * Changes the view to show the attributes list of a session.
     */
    private void showAttributesList() {
        sessionStudentListView.setVisible(true);
        labelView.setVisible(false);
    }

    /**
     * Changes the view to show a message declaring that there is no students in this session.
     */
    private void showEmptyAttributesState() {
        displayMessage.setText(NO_SESSION_STUDENTS_MESSAGE);
        sessionStudentListView.setVisible(false);
        labelView.setVisible(true);
    }

    /**
     * Adds a listener to listen if the attribute list becomes empty.
     *
     * @param sessionStudentList Observable list of attributes to be listened to.
     */
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
