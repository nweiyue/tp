package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attendance.Attributes;
import seedu.address.model.attendance.Participation;
import seedu.address.model.attendance.Presence;
import seedu.address.model.tag.Tag;

import java.util.Map;


/**
 * Jackson-friendly version of {@link Attributes}.
 */
public class JsonAdaptedAttribute {

    private final String attributeIndex;
    private final String presence;
    private final String participation;

    /**
     * Constructs a {@code JsonAdaptedAttribute} with the given {@code attributeIndex},
     * {@code presence} and {@code presence}.
     */
    @JsonCreator
    public JsonAdaptedAttribute(String attributeIndex, String presence, String participation) {
        this.attributeIndex = attributeIndex;
        this.presence = presence;
        this.participation = participation;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedAttribute(int index, Map<Integer, Attributes> source) {
        this.attributeIndex = Integer.toString(index);
        Attributes attributes = source.get(index);
        this.presence = Boolean.toString(attributes.getPresenceStatus());
        this.participation = Boolean.toString(attributes.getParticipationStatus());
    }

    @JsonValue
    public String getAttributeIndex() {
        return this.attributeIndex;
    }

    @JsonValue
    public String getPresence() {
        return this.presence;
    }

    @JsonValue
    public String getParticipation() {
        return this.participation;
    }

    /**
     * Converts this Jackson-friendly adapted attribute object into the model's {@code Attribute} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attribute.
     */
    public Attributes toModelType() throws IllegalValueException {
        Presence presence = new Presence(Boolean.parseBoolean(this.presence));
        Participation participation = new Participation(Boolean.parseBoolean(this.participation));

        return new Attributes(presence, participation);
    }
}
