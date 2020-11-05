package atas.model.session;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import atas.commons.core.index.Index;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the attribute-list level
 */
public class AttributesList implements ReadOnlyAttributesList {

    protected final ObservableList<Attributes> attributes;

    /* Tracks the session name and index of the session entered */
    protected Optional<String> currentSessionName;
    protected Optional<Index> currentSessionIndex;

    /**
     * Creates a VersionedAttributesList with an empty initial state.
     */
    public AttributesList() {
        attributes = FXCollections.observableArrayList();
        currentSessionName = Optional.empty();
        currentSessionIndex = Optional.empty();
    }

    /**
     * Replaces the contents of the attributes list with {@code attributes}.
     */
    public AttributesList(ReadOnlyAttributesList newState) {
        this();
        resetData(newState);
    }

    /**
     * Resets the current attribute list to a new set of attribute list.
     *
     * @param newData The new set of attribute list.
     */
    public void resetData(ReadOnlyAttributesList newData) {
        requireNonNull(newData);
        this.attributes.setAll(newData.getAttributesList());
    }

    /**
     * Resets the current attribute list to a new set of attribute list according to {@code newData}.
     */
    public void resetData(List<Attributes> newData) {
        requireNonNull(newData);
        this.attributes.setAll(newData);
    }

    /**
     * Resets the current attribute list to an empty list.
     */
    public void resetData() {
        this.attributes.setAll(new ArrayList<>());
    }

    @Override
    public ObservableList<Attributes> getAttributesList() {
        return attributes;
    }

}
