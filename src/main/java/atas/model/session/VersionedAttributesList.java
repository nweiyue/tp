package atas.model.session;

import static atas.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import atas.commons.core.index.Index;
import atas.model.VersionedEntity;
import atas.model.exceptions.UnableToRedoException;
import atas.model.exceptions.UnableToUndoException;
import javafx.collections.ObservableList;

public class VersionedAttributesList extends AttributesList implements VersionedEntity {

    private final List<AttributesList> attributeStateList;
    private int currentStatePointer;

    /**
     * Creates a VersionedAttributesList with an empty initial state.
     */
    public VersionedAttributesList() {
        super();
        attributeStateList = new ArrayList<>();
        attributeStateList.add(new AttributesList());
        currentStatePointer = 0;
    }

    @Override
    public void commit() {
        removeStatesAfterCurrentPointer();
        this.attributeStateList.add(new AttributesList(this, this.currentSessionIndex));
        currentStatePointer++;
    }

    @Override
    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    @Override
    public void undo() {
        if (!canUndo()) {
            throw new UnableToUndoException();
        }
        resetData(attributeStateList.get(--currentStatePointer));
    }

    @Override
    public boolean canRedo() {
        return currentStatePointer < this.attributeStateList.size() - 1;
    }

    @Override
    public void redo() {
        if (!canRedo()) {
            throw new UnableToRedoException();
        }
        resetData(attributeStateList.get(++currentStatePointer));
    }

    public ObservableList<Attributes> getAttributesList() {
        return attributes;
    }

    public void setCurrentAttributeList(Index index, List<Attributes> attributeList) {
        requireAllNonNull(index, attributeList);
        attributes.setAll(attributeList);
        currentSessionIndex = Optional.ofNullable(index);
    }


    private void removeStatesAfterCurrentPointer() {
        List<AttributesList> subList = attributeStateList.subList(currentStatePointer + 1, attributeStateList.size());
        subList.clear();
    }

}
