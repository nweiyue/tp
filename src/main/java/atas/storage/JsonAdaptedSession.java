package atas.storage;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import atas.commons.exceptions.IllegalValueException;
import atas.model.attendance.Attributes;
import atas.model.attendance.Session;
import atas.model.attendance.SessionDate;
import atas.model.attendance.SessionName;
import atas.model.person.Name;

/**
 * Jackson-friendly version of {@link Session}.
 */
public class JsonAdaptedSession {


    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Session's %s field is missing!";

    private final String sessionName;
    private final String sessionDate;
    private final List<JsonAdaptedAttributes> attributesList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedSession(@JsonProperty("sessionName") String sessionName,
                             @JsonProperty("sessionDate") String sessionDate,
                             @JsonProperty("attributesList") List<JsonAdaptedAttributes> attributesList) {
        this.sessionName = sessionName;
        this.sessionDate = sessionDate;
        this.attributesList.addAll(attributesList);
    }

    /**
     * Converts a given {@code Session} into this class for Jackson use.
     */
    public JsonAdaptedSession(Session source) {
        sessionName = source.getSessionName().value;
        sessionDate = source.getSessionDate().toString();
        for (Integer index: source.getStudentList().keySet()) {
            try {
                JsonAdaptedAttributes jsonAdaptedAttributes = new JsonAdaptedAttributes(
                        index, source.getStudentList(), source.returnStudentNameStringByIndex(index)
                );
                attributesList.add(jsonAdaptedAttributes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Session} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted session.
     */
    public Session toModelType() throws IllegalValueException {
        final Map<Integer, Attributes> studentList = new HashMap<>();

        for (JsonAdaptedAttributes attr : attributesList) {
            Attributes attributes = new Attributes(new Name(attr.getName()));

            if (Boolean.parseBoolean(attr.getPresence())) {
                attributes = attributes.togglePresence();
            }
            if (Boolean.parseBoolean(attr.getParticipation())) {
                attributes = attributes.toggleParticipation();
            }


            studentList.put(Integer.parseInt(attr.getAttributeIndex()), attributes);
        }

        if (sessionName == null) {
            throw new IllegalValueException(SessionName.MESSAGE_CONSTRAINTS);
        }
        if (!SessionName.isValidSessionName(sessionName)) {
            throw new IllegalValueException(SessionName.MESSAGE_CONSTRAINTS);
        }
        final SessionName name = new SessionName(sessionName);

        if (sessionDate == null) {
            throw new IllegalValueException(SessionDate.MESSAGE_CONSTRAINTS);
        }
        if (!SessionDate.isValidSessionDate(sessionDate)) {
            throw new IllegalValueException(SessionDate.MESSAGE_CONSTRAINTS);
        }

        SessionDate date;
        try {
            date = new SessionDate(sessionDate);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(SessionDate.MESSAGE_CONSTRAINTS);
        }


        return new Session(name, date, studentList);
    }

}
