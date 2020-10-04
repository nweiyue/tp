package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attendance.Attributes;
import seedu.address.model.attendance.Participation;
import seedu.address.model.attendance.Presence;


public class JsonAdaptedAttributeTest {

    private static final String VALID_INDEX = "1";
    private static final String VALID_PRESENCE = "true";
    private static final String VALID_PARTICIPATION = "true";

    private static final String INVALID_INDEX = "a";
    private static final String INVALID_PRESENCE = "b";
    private static final String INVALID_PARTICIPATION = "c";


    @Test
    public void toModelType_validAttributeDetails_returnsAttribute() throws Exception {
        JsonAdaptedAttributes attr = new JsonAdaptedAttributes(VALID_INDEX, VALID_PRESENCE, VALID_PARTICIPATION);

        Attributes expectedAttributes = new Attributes().togglePresence().toggleParticipation();
        assertEquals(expectedAttributes, attr.toModelType());
    }

    @Test
    public void toModelType_invalidPresence_throwsIllegalValueException() {
        JsonAdaptedAttributes attr = new JsonAdaptedAttributes(VALID_INDEX, INVALID_PRESENCE, VALID_PARTICIPATION);
        String expectedMessage = Presence.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attr::toModelType);
    }

    @Test
    public void toModelType_nullPresence_throwsIllegalValueException() {
        JsonAdaptedAttributes attr = new JsonAdaptedAttributes(VALID_INDEX, null, VALID_PARTICIPATION);
        String expectedMessage = Presence.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attr::toModelType);
    }

    @Test
    public void toModelType_invalidParticipation_throwsIllegalValueException() {
        JsonAdaptedAttributes attr = new JsonAdaptedAttributes(VALID_INDEX, VALID_PRESENCE, INVALID_PARTICIPATION);
        String expectedMessage = Participation.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attr::toModelType);
    }

    @Test
    public void toModelType_nullParticipation_throwsIllegalValueException() {
        JsonAdaptedAttributes attr = new JsonAdaptedAttributes(VALID_INDEX, VALID_PRESENCE, null);
        String expectedMessage = Participation.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, attr::toModelType);
    }
}
