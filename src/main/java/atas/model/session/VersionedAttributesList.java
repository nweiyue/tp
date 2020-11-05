package atas.model.session;

import java.util.ArrayList;
import java.util.List;

import atas.model.VersionedEntity;
import atas.model.exceptions.UnableToRedoException;
import atas.model.exceptions.UnableToUndoException;

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

    /**
     * Creates a VersionedAttributesList with {@code newData} initialized.
     */
    public VersionedAttributesList(ReadOnlyAttributesList newData) {
        super(newData);
        attributeStateList = new ArrayList<>();
        attributeStateList.add(new AttributesList());
        currentStatePointer = 0;
    }

    @Override
    public void commit() {
        removeStatesAfterCurrentPointer();
        this.attributeStateList.add(new AttributesList(this, getCurrentSessionIndex()));
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

    public List<AttributesList> getAttributeStateList() {
        return attributeStateList;
    }

    public int getCurrentStatePointer() {
        return currentStatePointer;
    }

    public void setCurrentStatePointer(int newPointer) {
        currentStatePointer = newPointer;
    }

    private void removeStatesAfterCurrentPointer() {
        List<AttributesList> subList = attributeStateList.subList(currentStatePointer + 1, attributeStateList.size());
        subList.clear();
    }

}
