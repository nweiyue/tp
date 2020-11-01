package atas.ui;

import java.util.logging.Logger;

import atas.commons.core.LogsCenter;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;

/**
 * Controller for a help page
 */
public class HelpWindow extends UiPart<Stage> {
    public static final String HELP_MESSAGE = "Here is the list of commands you can try with ATAS:\n";
    public static final String USERGUIDE_URL = "https://ay2021s1-cs2103t-w16-4.github.io/tp/UserGuide.html";
    public static final String LINK_MESSAGE = "Link to the full user guide: " + USERGUIDE_URL;

    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
    private static final String FXML = "HelpWindow.fxml";

    private final CommandSummary helpCmd =
            new CommandSummary("Get help", "help");
    private final CommandSummary switchCmd =
            new CommandSummary("Switch between tabs", "switch TAB_NAME");
    private final CommandSummary rngCmd =
            new CommandSummary("Generate the name of a randomly selected student", "rng");
    private final CommandSummary undoCmd =
            new CommandSummary("Undo a command", "undo");
    private final CommandSummary redoCmd =
            new CommandSummary("Redo a command", "redo");
    private final CommandSummary exitCmd =
            new CommandSummary("Exit ATAS", "exit");

    public final ObservableList<CommandSummary> generalCmds = FXCollections
            .observableArrayList(helpCmd, switchCmd, rngCmd, undoCmd, redoCmd, exitCmd);

    private final CommandSummary addStuCmd =
            new CommandSummary("Add a student",
                    "addstu n/NAME m/MATRICULATION_NUMBER e/NUS_EMAIL_ADDRESS [t/TAG]…\u200B");
    private final CommandSummary listStuCmd =
            new CommandSummary("List all students", "liststu");
    private final CommandSummary findStuCmd =
            new CommandSummary("Find student(s)", "findstu KEYWORD [MORE_KEYWORDS]");
    private final CommandSummary editStuCmd =
            new CommandSummary("Edit a student's particulars",
                    "editstu INDEX [n/NAME] [m/MATRICULATION_NUMBER] [e/NUS_EMAIL_ADDRESS] [t/TAG]…\u200B");
    private final CommandSummary deleteStuCmd =
            new CommandSummary("Delete a student", "deletestu INDEX");
    private final CommandSummary clearStuCmd =
            new CommandSummary("Clear the student list", "clearstu");

    public final ObservableList<CommandSummary> studentsCmds = FXCollections
            .observableArrayList(addStuCmd, listStuCmd, findStuCmd, editStuCmd, deleteStuCmd, clearStuCmd);

    private final CommandSummary addSesCmd =
            new CommandSummary("Add a session", "addses s/SESSION_NAME d/SESSION_DATE");
    private final CommandSummary clearSesCmd =
            new CommandSummary("Clear the session list", "clearses");
    private final CommandSummary deleteSesCmd =
            new CommandSummary("Delete a session", "deleteses INDEX");
    private final CommandSummary editSesCmd =
            new CommandSummary("Edit a session", "editses INDEX [s/SESSION_NAME] [d/SESSION_DATE]");
    private final CommandSummary enterSesCmd =
            new CommandSummary("Enter a session", "enterses INDEX");

    public final ObservableList<CommandSummary> sessionsCmds = FXCollections
            .observableArrayList(addSesCmd, clearSesCmd, deleteSesCmd, editSesCmd, enterSesCmd);

    private final CommandSummary participateCmd =
            new CommandSummary("Toggle the participation status of student(s)", "participate INDEX_RANGE");
    private final CommandSummary presenceCmd =
            new CommandSummary("Toggle the presence status of student(s)", "presence INDEX_RANGE");

    public final ObservableList<CommandSummary> currentSessionCmds = FXCollections
            .observableArrayList(participateCmd, presenceCmd);

    private final CommandSummary addNoteCmd =
            new CommandSummary("Add a note", "addnote NOTE");
    private final CommandSummary saveMemoCmd =
            new CommandSummary("Save memo", "Keyboard shortcut: \"Ctrl + s\" or \"Cmd + s\" for MacOs");

    public final ObservableList<CommandSummary> memoCmds = FXCollections
            .observableArrayList(addNoteCmd, saveMemoCmd);

    @FXML
    private Button copyButton;

    @FXML
    private Label linkMessage;

    @FXML
    private Label helpMessage;

    @FXML
    private TableView<CommandSummary> generalCommandTable;

    @FXML
    private TableView<CommandSummary> studentsCommandTable;

    @FXML
    private TableView<CommandSummary> sessionsCommandTable;

    @FXML
    private TableView<CommandSummary> currentSessionCommandTable;

    @FXML
    private TableView<CommandSummary> memoCommandTable;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public HelpWindow(Stage root) {
        super(FXML, root);
        initHelpWindow();
    }

    /**
     * Creates a new HelpWindow.
     */
    public HelpWindow() {
        this(new Stage());
    }

    /**
     * Initialise the HelpWindow by setting messages and data in tables
     */
    private void initHelpWindow() {
        linkMessage.setText(LINK_MESSAGE);
        helpMessage.setText(HELP_MESSAGE);
        generalCommandTable.setItems(generalCmds);
        studentsCommandTable.setItems(studentsCmds);
        sessionsCommandTable.setItems(sessionsCmds);
        currentSessionCommandTable.setItems(currentSessionCmds);
        memoCommandTable.setItems(memoCmds);
        resizeTables();
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USERGUIDE_URL);
        clipboard.setContent(url);
    }

    /**
     * Resizes table height to fit only the number of data in the table.
     *
     * @params table Table to resize.
     */
    private void resizeTableHeight(TableView<CommandSummary> table) {
        table.setFixedCellSize(25);
        table.prefHeightProperty().bind(table.fixedCellSizeProperty()
                .multiply(Bindings.size(table.getItems()).add(1.9)));
        table.minHeightProperty().bind(table.prefHeightProperty());
        table.maxHeightProperty().bind(table.prefHeightProperty());
    }

    /**
     * Resizes all the tables in HelpWindow.
     */
    private void resizeTables() {
        resizeTableHeight(generalCommandTable);
        resizeTableHeight(studentsCommandTable);
        resizeTableHeight(sessionsCommandTable);
        resizeTableHeight(currentSessionCommandTable);
        resizeTableHeight(memoCommandTable);
    }

}
