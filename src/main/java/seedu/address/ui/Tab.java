package seedu.address.ui;

/**
 * Existing tabs.
 */
public enum Tab {
    CLASSES(0),
    ATTENDANCE(1);

    /** Integer to represent the order of the tabs */
    private final int index;

    /**
     * Class constructor.
     *
     * @param index index of the tab.
     */
    Tab(int index) {
        this.index = index;
    }

    /**
     * Retrieves the index of the enum class.
     *
     * @return index of the tab.
     */
    public int getIndex() {
        return this.index;
    }
}
