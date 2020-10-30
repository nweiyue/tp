package atas.ui;

import java.util.logging.Logger;

import atas.commons.core.LogsCenter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * Controller for the start display under the ATAS tab
 */
public class StartDisplay extends UiPart<Region> {

    public static final String GETTING_STARTED_DISPLAY = "Welcome to ATAS, your all-in-one in-class companion!\n"
            + "This area is where you will see the important information under each tab.\n"
            + "On the left of this box are the tabs.\n"
            + "Each tab will display the relevant information once you go to that tab.\n"
            + "To see an overview of the supported commands, type \"help\" in the command box!";

    private static final String FXML = "StartDisplay.fxml";

    @FXML
    private Label gettingStartedDisplay;

    /**
     * Creates a new HelpWindow.
     *
     */
    public StartDisplay() {
        super(FXML);
        gettingStartedDisplay.setText(GETTING_STARTED_DISPLAY);
    }

}
