package atas.model.session.exceptions;

import atas.commons.core.index.Index;

/**
 * Signals that the operation will result in entering the same session.
 */
public class SameSessionException extends RuntimeException {

    private Index sessionIndex;

    public SameSessionException(Index sessionIndex) {
        this.sessionIndex = sessionIndex;
    }

    public Index getSessionIndex() {
        return sessionIndex;
    }

}
