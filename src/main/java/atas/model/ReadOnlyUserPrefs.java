package atas.model;

import java.nio.file.Path;

import atas.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getStudentListFilePath();

    Path getSessionListFilePath();

    Path getMemoFilePath();
}
