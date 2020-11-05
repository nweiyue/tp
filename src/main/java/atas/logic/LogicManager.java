package atas.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import atas.commons.core.GuiSettings;
import atas.commons.core.LogsCenter;
import atas.commons.core.index.Index;
import atas.logic.commands.Command;
import atas.logic.commands.CommandResult;
import atas.logic.commands.exceptions.CommandException;
import atas.logic.parser.AtasParser;
import atas.logic.parser.exceptions.ParseException;
import atas.model.Model;
import atas.model.session.Attributes;
import atas.model.session.Session;
import atas.model.student.ReadOnlyStudentList;
import atas.model.student.Student;
import atas.storage.Storage;
import javafx.collections.ObservableList;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final AtasParser atasParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        atasParser = new AtasParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        CommandResult commandResult;
        Command command = atasParser.parseCommand(commandText);
        try {
            commandResult = command.execute(model);
        } catch (CommandException e) {
            // if e is related to invalid indexes, atasParser will need to refresh internal state.
            atasParser.refreshInternalState(e);
            throw e;
        }

        try {
            storage.saveSessionList(model.getSessionList());
            storage.saveStudentList(model.getStudentList());
            storage.saveMemo(model.getMemo());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyStudentList getStudentList() {
        return model.getStudentList();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public ObservableList<Session> getFilteredSessionList() {
        return model.getFilteredSessionList();
    }

    @Override
    public ObservableList<Attributes> getFilteredAttributesList() {
        return model.getCurrentAttributesList();
    }

    @Override
    public Path getStudentListFilePath() {
        return model.getStudentListFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public void disableCurrentSession() {
        model.setCurrentSessionFalse();
    }

    @Override
    public void enableCurrentSession() {
        model.setCurrentSessionTrue();
    }

    @Override
    public Index getSessionId() {
        return model.getSessionId();
    }

    @Override
    public String getMemoContent() {
        return model.getMemoContent();
    }

    @Override
    public String getLeftSessionDetails() {
        return model.getLeftSessionDetails();
    }

    @Override
    public String getRightSessionDetails() {
        return model.getRightSessionDetails();
    }

    @Override
    public void saveMemoContent(String content) throws CommandException {
        try {
            model.saveMemoContent(content);
            storage.saveMemo(model.getMemo());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }
    }

    @Override
    public void refreshStatistics() {
        model.refreshStatistics();
    }
}
