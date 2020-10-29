package atas.model.session;

import java.util.ArrayList;
import java.util.List;

import atas.model.VersionedEntity;
import atas.model.exceptions.UnableToRedoException;
import atas.model.exceptions.UnableToUndoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class VersionedAttributesList implements VersionedEntity {

    private final ObservableList<Attributes> currentAttributeList;
    private final List<List<Attributes>> attributeStateList;
    private int currentStatePointer;
    private final List<String> tracker;

    /**
     * Creates a VersionedStudentList with an empty initial state.
     */
    public VersionedAttributesList() {
        currentAttributeList = FXCollections.observableArrayList();
        attributeStateList = new ArrayList<>();
        attributeStateList.add(new ArrayList<>());
        currentStatePointer = 0;
        tracker = new ArrayList<>();
    }

    public List<List<Attributes>> getAttributeStateList() {
        return attributeStateList;
    }

    @Override
    public void commit() {
        removeStatesAfterCurrentPointer();
        this.attributeStateList.add(new ArrayList<>(currentAttributeList));
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

    public ObservableList<Attributes> getCurrentAttributeList() {
        return currentAttributeList;
    }

    public void setCurrentAttributeList(String name, List<Attributes> attributeList) {
        this.currentAttributeList.setAll(attributeList);
        if (tracker.isEmpty() || !tracker.get(tracker.size() - 1).equals(name)) {
            saveInitialCommitAfterEnterSession(name);
        }
    }

    private void saveInitialCommitAfterEnterSession(String name) {
        tracker.add(name);
        commit();
    }

    public void resetData(List<Attributes> newData) {
        this.currentAttributeList.setAll(newData);
    }

    private void removeStatesAfterCurrentPointer() {
        List<List<Attributes>> subList = attributeStateList.subList(currentStatePointer + 1, attributeStateList.size());
        subList.clear();
    }

}
