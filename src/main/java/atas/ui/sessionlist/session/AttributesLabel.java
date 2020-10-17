package atas.ui.sessionlist.session;

import javafx.scene.control.Label;

/**
 * Represents a control that is either in a positive or negative state.
 */
public class AttributesLabel extends Label {

    protected static final String STYLE_POSITIVE = "-fx-background-color: green";
    protected static final String STYLE_NEGATIVE = "-fx-background-color: crimson";

    protected boolean isPositive;

    /**
     * Creates an {@code AttributesLabel} with the given text to display.
     */
    public AttributesLabel(String text, boolean isPositive) {
        super(text);
        this.isPositive = isPositive;
        setBackground();
    }

    /**
     * Sets background color depending on <code>isPositive</code>.
     *
     * If <code>true</code>, sets background to green. Otherwise, sets background to red.
     */
    public void setBackground() {
        if (isPositive) {
            this.setStyle(STYLE_POSITIVE);
        } else {
            this.setStyle(STYLE_NEGATIVE);
        }
    }

}
