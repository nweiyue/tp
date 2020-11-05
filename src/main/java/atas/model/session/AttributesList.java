package atas.model.session;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the attribute-list level
 */
public class AttributesList implements ReadOnlyAttributesList {

    protected final ObservableList<Attributes> attributes;

    /**
     * Creates a VersionedAttributesList with an empty initial state.
     */
    public AttributesList() {
        attributes = FXCollections.observableArrayList();
    }

    @Override
    public ObservableList<Attributes> getAttributesList() {
        return attributes;
    }

}
