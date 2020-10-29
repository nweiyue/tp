package atas.model.session;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import atas.model.VersionedEntity;
import atas.model.exceptions.UnableToRedoException;
import atas.model.exceptions.UnableToUndoException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class VersionedAttributesList implements VersionedEntity {

    private final ObservableList<Attributes> currentAttributeList;
    private final List<List<Attributes>> attributeStateList;
    private int currentStatePointer;
    /* Tracks all the session names of the sessions entered */
    private Optional<String> currentSessionName;

    /**
     * Creates a VersionedStudentList with an empty initial state.
     */
    public VersionedAttributesList() {
        currentAttributeList = FXCollections.observableArrayList();
        attributeStateList = new ArrayList<>();
        attributeStateList.add(new ArrayList<>());
        currentStatePointer = 0;
        currentSessionName = Optional.empty();
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
        requireNonNull(name);
        requireNonNull(attributeList);
        this.currentAttributeList.setAll(attributeList);
        // If no sessions were entered or user enters a different session
        if (currentSessionName.isEmpty() || !currentSessionName.get().equals(name)) {
            saveInitialCommitAfterEnterSession(name);
        }
    }

    /**
     * Creates an initial commit when entering a different session.
     *
     * @param name Session name.
     */
    private void saveInitialCommitAfterEnterSession(String name) {
        requireNonNull(name);
        currentSessionName = Optional.of(name);
        commit();
    }

    public void resetData(List<Attributes> newData) {
        requireNonNull(newData);
        this.currentAttributeList.setAll(newData);
    }

    private void removeStatesAfterCurrentPointer() {
        List<List<Attributes>> subList = attributeStateList.subList(currentStatePointer + 1, attributeStateList.size());
        subList.clear();
    }

}
