package seedu.address.ui;

import seedu.address.commons.core.index.Index;

/**
 * Existing tabs with an Index associated to them.
 * Index numbering starts from 0, beginning with tabs on the left of the GUI.
 */
public enum Tab {
    CLASSES(Index.fromZeroBased(0)),
    ATTENDANCE(Index.fromZeroBased(1));

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
}
