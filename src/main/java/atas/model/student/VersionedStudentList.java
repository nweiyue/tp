package atas.model.student;

import java.util.ArrayList;
import java.util.List;

import atas.model.VersionedEntity;
import atas.model.exceptions.UnableToRedoException;
import atas.model.exceptions.UnableToUndoException;

public class VersionedStudentList extends StudentList implements VersionedEntity {

    private final List<ReadOnlyStudentList> studentStateList;
    private int currentStatePointer;

    /**
     * Creates a VersionedStudentList using the students in the {@code initialState}.
     */
    public VersionedStudentList(ReadOnlyStudentList initialState) {
        super(initialState);

        studentStateList = new ArrayList<>();
        studentStateList.add(new StudentList(initialState));
        currentStatePointer = 0;
    }

    public List<ReadOnlyStudentList> getStudentStateList() {
        return studentStateList;
    }

    @Override
    public void commit() {
        removeStatesAfterCurrentPointer();
        this.studentStateList.add(new StudentList(this));
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
        resetData(studentStateList.get(--currentStatePointer));
    }

    @Override
    public boolean canRedo() {
        return currentStatePointer < this.studentStateList.size() - 1;
    }

    @Override
    public void redo() {
        if (!canRedo()) {
            throw new UnableToRedoException();
        }
        resetData(studentStateList.get(++currentStatePointer));
    }

    public int getCurrentStatePointer() {
        return currentStatePointer;
    }

    public void setCurrentStatePointer(int newPointer) {
        currentStatePointer = newPointer;
    }

    private void removeStatesAfterCurrentPointer() {
        this.studentStateList.subList(currentStatePointer + 1, studentStateList.size()).clear();
    }

}
