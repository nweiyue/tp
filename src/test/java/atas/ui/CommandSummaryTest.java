package atas.ui;

import static atas.testutil.TypicalCommandSummaries.COMMAND_SUMMARY_ONE;
import static atas.testutil.TypicalCommandSummaries.COMMAND_SUMMARY_TWO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class CommandSummaryTest {

    @Test
    public void testGetAction() {
        // same actions -> equals
        assertEquals(COMMAND_SUMMARY_ONE.getAction(), "actionOne");
        assertEquals(COMMAND_SUMMARY_TWO.getAction(), "actionTwo");

        // different actions -> not equals
        assertNotEquals(COMMAND_SUMMARY_ONE.getAction(), "actionTwo");
        assertNotEquals(COMMAND_SUMMARY_TWO.getAction(), "actionOne");
    }

    @Test
    public void testSetAction() {
        CommandSummary commandSummary = new CommandSummary("action", "commandFormat");
        commandSummary.setAction("newAction");

        // same actions -> equals
        assertEquals(commandSummary.getAction(), "newAction");
        // different actions -> not equals
        assertNotEquals(commandSummary.getAction(), "action");
    }

    @Test
    public void testGetCommandFormat() {
        // same commandFormat -> equals
        assertEquals(COMMAND_SUMMARY_ONE.getCommandFormat(), "commandFormatOne");
        assertEquals(COMMAND_SUMMARY_TWO.getCommandFormat(), "commandFormatTwo");

        // different commandFormat -> not equals
        assertNotEquals(COMMAND_SUMMARY_ONE.getCommandFormat(), "commandFormatTwo");
        assertNotEquals(COMMAND_SUMMARY_TWO.getCommandFormat(), "commandFormatOne");
    }

    @Test
    public void testSetCommandFormat() {
        CommandSummary commandSummary = new CommandSummary("action", "commandFormat");
        commandSummary.setCommandFormat("newCommandFormat");

        // same commandFormat -> equals
        assertEquals(commandSummary.getCommandFormat(), "newCommandFormat");
        // different commandFormat -> not equals
        assertNotEquals(commandSummary.getCommandFormat(), "commandFormat");
    }
}
