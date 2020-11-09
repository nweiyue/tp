package atas.logic;

import java.nio.file.Path;

import atas.commons.core.GuiSettings;
import atas.commons.core.index.Index;
import atas.logic.commands.CommandResult;
import atas.logic.commands.exceptions.CommandException;
import atas.logic.parser.exceptions.ParseException;
import atas.model.Model;
import atas.model.session.Attributes;
import atas.model.session.Session;
import atas.model.student.ReadOnlyStudentList;
import atas.model.student.Student;
import javafx.collections.ObservableList;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the StudentList.
     *
     * @see Model#getStudentList()
     */
    ReadOnlyStudentList getStudentList();

    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the filtered list of sessions */
    ObservableList<Session> getFilteredSessionList();

    /** Returns an unmodifiable view of the filtered list of student attributes */
    ObservableList<Attributes> getFilteredAttributesList();

    /** Sets currentSession to be false */
    void disableCurrentSession();

    /** Sets currentSession to be false */
    void enableCurrentSession();

    /**
     * Returns the user prefs' student list file path.
     */
    Path getStudentListFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the id of the current session.
     *
     * @return Id of the current session.
     */
    Index getSessionId();

    /**
     * Returns the Memo content.
     *
     * @return Text contained in Memo.
     */
    String getMemoContent();

    /**
     * Returns the name and date of the current entered session.
     */
    String getLeftSessionDetails();

    /**
     * Returns the stats of the current entered session.
     */
    String getRightSessionDetails();

    /**
     * Saves content into the Memo.
     *
     * @param memoContent The String content that should be saved into the Memo.
     * @throws CommandException If file to save content in does not exist and cannot be created.
     */
    void saveMemoContent(String memoContent) throws CommandException;

    /**
     * Refresh statistics of model.
     */
    void refreshStatistics();
}
