package atas.ui.sessionlist.session;

/**
 * Represents a {@code Participation} label representing either of the states: has participated or has not yet
 * participated.
 */
public class ParticipationLabel extends AttributesLabel {

    /**
     * Creates a {@code ParticipationLabel} with the given text to display.
     */
    public ParticipationLabel(String text, boolean isPositive) {
        super(text, isPositive);
    }

}
