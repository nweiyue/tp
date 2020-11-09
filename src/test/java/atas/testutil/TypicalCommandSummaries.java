package atas.testutil;

import atas.ui.CommandSummary;

/**
 * A utility class containing a list of {@code CommandSummary} objects to be used in tests.
 */
public class TypicalCommandSummaries {
    public static final CommandSummary COMMAND_SUMMARY_ONE = new CommandSummary(
            "actionOne", "commandFormatOne");
    public static final CommandSummary COMMAND_SUMMARY_TWO = new CommandSummary(
            "actionTwo", "commandFormatTwo");
}
