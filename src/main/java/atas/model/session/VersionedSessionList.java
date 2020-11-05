package atas.model.session;

import java.util.ArrayList;
import java.util.List;

import atas.model.VersionedEntity;
import atas.model.exceptions.UnableToRedoException;
import atas.model.exceptions.UnableToUndoException;

public class VersionedSessionList extends SessionList implements VersionedEntity {

    private final List<ReadOnlySessionList> sessionStateList;
    private int currentStatePointer;

    /**
     * Creates a VersionedStudentList using the students in the {@code initialState}.
     */
    public VersionedSessionList(ReadOnlySessionList initialState) {
        super(initialState);

        sessionStateList = new ArrayList<>();
        sessionStateList.add(new SessionList(initialState));
        currentStatePointer = 0;
    }

    public List<ReadOnlySessionList> getSessionStateList() {
        return sessionStateList;
    }

    @Override
    public void commit() {
        removeStatesAfterCurrentPointer();
        this.sessionStateList.add(new SessionList(this.getCopy()));
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
        resetData(sessionStateList.get(--currentStatePointer));
    }

    @Override
    public boolean canRedo() {
        return currentStatePointer < this.sessionStateList.size() - 1;
    }

    @Override
    public void redo() {
        if (!canRedo()) {
            throw new UnableToRedoException();
        }
        resetData(sessionStateList.get(++currentStatePointer));
    }

    public int getCurrentStatePointer() {
        return currentStatePointer;
    }

    public void setCurrentStatePointer(int newPointer) {
        currentStatePointer = newPointer;
    }

    private void removeStatesAfterCurrentPointer() {
        this.sessionStateList.subList(currentStatePointer + 1, sessionStateList.size()).clear();
    }

}
