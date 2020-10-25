package atas.ui.statistics;

import static atas.commons.core.statistics.StatisticalValues.STAT_FULL;
import static atas.commons.core.statistics.StatisticalValues.STAT_NULL;

import atas.model.statistics.Statistics;
import javafx.scene.control.Label;


public class StatisticsLabel extends Label {

    protected static final String STYLE_NULL_BACKGROUND = "-fx-background-color: #e6e6e6;";
    protected static final String STYLE_NULL_TEXT = "-fx-text-fill: black;";

    protected static final String STYLE_FULL_BACKGROUND = "-fx-background-color: green;";
    protected static final String STYLE_FULL_TEXT = "-fx-text-fill: white;";

    protected static final String STYLE_NOTFULL_BACKGROUND = "-fx-background-color: crimson;";
    protected static final String STYLE_NOTFULL_TEXT = "-fx-text-fill: white;";

    /**
     * Creates an {@code StatisticsLabel} with the given text to display.
     */
    public StatisticsLabel(Statistics statistics) {
        super(statistics.getDataAsPercentage());

        setBackground(statistics);
    }

    /**
     * Sets background color depending on the state of the current statistics.
     */
    public void setBackground(Statistics statistics) {
        int value = statistics.getStatValue();
        if (value == STAT_NULL) {
            this.setStyle(STYLE_NULL_TEXT + STYLE_NULL_BACKGROUND);
        } else if (value == STAT_FULL) {
            this.setStyle(STYLE_FULL_TEXT + STYLE_FULL_BACKGROUND);
        } else {
            this.setStyle(STYLE_NOTFULL_TEXT + STYLE_NOTFULL_BACKGROUND);
        }
    }
}
