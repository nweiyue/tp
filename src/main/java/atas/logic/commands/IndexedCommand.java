package atas.logic.commands;

import atas.commons.core.index.Index;

/**
 * Represents a type of Command that requires an {@code Index}.
 */
public interface IndexedCommand {
    /**
     * Returns the target index of the command.
     *
     * @return The target index of the command.
     */
    Index getTargetIndex();
}
