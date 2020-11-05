package atas.model.session;

import java.util.Optional;

import atas.commons.core.index.Index;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an attribute list
 */
public interface ReadOnlyAttributesList {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Attributes> getAttributesList();

    /**
     * Returns an unmodifiable view of the current session index.
     */
    Optional<Index> getCurrentSessionIndex();

}
