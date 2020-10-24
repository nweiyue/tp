package atas.storage;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import atas.commons.exceptions.IllegalValueException;
import atas.model.session.Attributes;
import atas.model.session.Session;
import atas.model.session.SessionDate;
import atas.model.session.SessionName;
import atas.model.student.Name;

/**
 * Jackson-friendly version of {@link Session}.
 */
public class JsonAdaptedSession {


    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Session's %s field is missing!";

    private final String sessionName;
    private final String sessionDate;
    private final List<JsonAdaptedAttributes> attributesList = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
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
        for (int i = 0; i < source.getAttributeList().size(); i++) {
            try {
                JsonAdaptedAttributes jsonAdaptedAttributes = new JsonAdaptedAttributes(
                        i, source.getAttributeList(), source.returnStudentNameStringByIndex(i));
                attributesList.add(jsonAdaptedAttributes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Session} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted session.
     */
    public Session toModelType() throws IllegalValueException {
        final List<Attributes> studentList = new ArrayList<>();

        for (JsonAdaptedAttributes attr : attributesList) {
            Attributes attributes = new Attributes();
            if (attr.getName() != null) {
                attributes = new Attributes(new Name(attr.getName()));
            }
            if (Boolean.parseBoolean(attr.getPresence())) {
                attributes = attributes.togglePresence();
            }
            if (Boolean.parseBoolean(attr.getParticipation())) {
                attributes = attributes.toggleParticipation();
            }

            studentList.add(Integer.parseInt(attr.getAttributeIndex()), attributes);
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
