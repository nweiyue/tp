package atas.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import atas.commons.exceptions.IllegalValueException;
import atas.model.session.ReadOnlySessionList;
import atas.model.session.Session;
import atas.model.session.SessionList;

/**
 * An Immutable SessionList that is serializable to JSON format.
 */
@JsonRootName(value = "sessionlist")
class JsonSerializableSessionList {

    public static final String MESSAGE_DUPLICATE_SESSION = "Session list contains duplicate session(s).";

    private final List<JsonAdaptedSession> sessions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableSessionList} with the given sessions.
     */
    @JsonCreator
    public JsonSerializableSessionList(@JsonProperty("sessions") List<JsonAdaptedSession> sessions) {
        this.sessions.addAll(sessions);
    }

    /**
     * Converts a given {@code ReadOnlySessionList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableSessionList}.
     */
    public JsonSerializableSessionList(ReadOnlySessionList source) {
        sessions.addAll(source.getSessions().stream().map(JsonAdaptedSession::new).collect(Collectors.toList()));
    }

    /**
     * Converts this session list into the model's {@code SessionList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SessionList toModelType() throws IllegalValueException {
        SessionList sessionList = new SessionList();

        for (JsonAdaptedSession jas : sessions) {
            Session session = jas.toModelType();
            if (sessionList.contains(session)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_SESSION);
            }
            sessionList.addSession(session);
        }
        return sessionList;
    }

}
