package atas.ui.studentlist;

import java.util.logging.Logger;

import atas.commons.core.LogsCenter;
import atas.model.student.Student;
import atas.ui.UiPart;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Panel containing the list of students.
 */
public class StudentListPanel extends UiPart<Region> {
    public static final String NO_STUDENTS_MESSAGE = "You currently have no students!\n"
            + "Type \"addstu …\" to start adding one!";
    private static final String FXML = "StudentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(StudentListPanel.class);

    @FXML
    private ListView<Student> studentListView;

    @FXML
    private VBox emptyStudentListView;

    @FXML
    private Label emptyStudentListMessage;

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public StudentListPanel(ObservableList<Student> studentList) {
        super(FXML);
        studentListView.setItems(studentList);
        studentListView.setCellFactory(listView -> new StudentListViewCell());
        emptyStudentListMessage.setText(NO_STUDENTS_MESSAGE);

        if (studentList.isEmpty()) {
            showEmptyStudentState();
        } else {
            showStudentList();
        }
        addStudentListChangeListener(studentList);
    }

    private void showStudentList() {
        studentListView.setVisible(true);
        emptyStudentListView.setVisible(false);
    }

    private void showEmptyStudentState() {
        studentListView.setVisible(false);
        emptyStudentListView.setVisible(true);
    }

    private void addStudentListChangeListener(ObservableList<Student> studentList) {
        studentListView.getItems().addListener(new ListChangeListener<Student>() {
            @Override
            public void onChanged(ListChangeListener.Change change) {
                if (studentList.isEmpty()) {
                    showEmptyStudentState();
                } else {
                    showStudentList();
                }
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    class StudentListViewCell extends ListCell<Student> {
        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new StudentCard(student, getIndex() + 1).getRoot());
            }
        }
    }

}
