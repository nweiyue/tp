package atas.ui;

import atas.commons.core.index.Index;

/**
 * Existing tabs with an Index associated to them.
 * Index numbering starts from 0, beginning with tabs on the left of the GUI.
 */
public enum Tab {
    STUDENTS(Index.fromZeroBased(0)),
    SESSIONS(Index.fromZeroBased(1)),
    CURRENT(Index.fromZeroBased(2)),
    MEMO(Index.fromZeroBased(3));

    /** Index to represent the order of the tabs */
    private final Index index;
    /**
     * Class constructor.
     *
     * @param index Index of the tab.
     */
    Tab(Index index) {
        this.index = index;
    }

    /**
     * Retrieves the Index of the enum class.
     *
     * @return Index of the tab.
     */
    public Index getIndex() {
        return this.index;
    }

    /**
     * Checks if the given tab is valid.
     *
     * @return boolean value to indicate validity of tab.
     */
    public boolean isValid() {
        return this.equals(Tab.STUDENTS)
                || this.equals(Tab.SESSIONS)
                || this.equals(Tab.CURRENT)
                || this.equals(Tab.MEMO);
    }

    /**
     * Returns a String to be displayed to users.
     *
     * @return String value of the tab.
     */
    public String toDisplayName() {
        if (this.equals(Tab.CURRENT)) {
            return "current session";
        } else {
            return this.toString().toLowerCase();
        }
    }
}
