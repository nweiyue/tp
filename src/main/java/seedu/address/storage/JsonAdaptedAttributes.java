package seedu.address.storage;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attendance.Attributes;
import seedu.address.model.attendance.Participation;
import seedu.address.model.attendance.Presence;

/**
 * Jackson-friendly version of {@link Attributes}.
 */
public class JsonAdaptedAttributes {

    private final String attributeIndex;
    private final String presence;
    private final String participation;

    /**
     * Constructs a {@code JsonAdaptedAttributes} with the given {@code attributeIndex},
     * {@code presence} and {@code presence}.
     */
    @JsonCreator
    public JsonAdaptedAttributes(@JsonProperty("attributeIndex") String attributeIndex,
                                 @JsonProperty("presence") String presence,
                                 @JsonProperty("participation") String participation) {
        this.attributeIndex = attributeIndex;
        this.presence = presence;
        this.participation = participation;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedAttributes(int index, Map<Integer, Attributes> source) {
        this.attributeIndex = Integer.toString(index);
        Attributes attributes = source.get(index);
        this.presence = Boolean.toString(attributes.getPresenceStatus());
        this.participation = Boolean.toString(attributes.getParticipationStatus());
    }


    public String getAttributeIndex() {
        return this.attributeIndex;
    }

    public String getPresence() {
        return this.presence;
    }

    public String getParticipation() {
        return this.participation;
    }

    /**
     * Converts this Jackson-friendly adapted attribute object into the model's {@code Attribute} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attribute.
     */
    public Attributes toModelType() throws IllegalValueException {
        if (this.presence == null || !this.presence.equals("true") && !this.presence.equals("false")) {
            throw new IllegalValueException(Presence.MESSAGE_CONSTRAINTS);
        }
        if (this.participation == null || !this.participation.equals("true") && !this.participation.equals("false")) {
            throw new IllegalValueException(Participation.MESSAGE_CONSTRAINTS);
        }

        Presence presence = new Presence(Boolean.parseBoolean(this.presence));
        Participation participation = new Participation(Boolean.parseBoolean(this.participation));

        return new Attributes(presence, participation);
    }
}
